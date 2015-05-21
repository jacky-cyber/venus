package com.dhf.venus.client.impl;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dhf.venus.client.Context;
import com.dhf.venus.client.Getter;
import com.dhf.venus.client.Getters;
import com.dhf.venus.client.Register;
import com.dhf.venus.client.Setter;
import com.dhf.venus.client.Setters;
import com.dhf.venus.client.annotation.GetValue;
import com.dhf.venus.client.annotation.SetValue;

/**
 * @author kim 2015年5月20日
 */
public class DefaultContext implements Context, Register {

	private final static Integer PARAM_SIZE = 1;

	private final static String GET = "get{0}";

	private final static String SET = "set{0}";

	private final static Log LOG = LogFactory.getLog(DefaultContext.class);

	private final Set<Getter> getters = new HashSet<Getter>();
	
	private final Set<Setter> setters = new HashSet<Setter>();

	private final Map<String, Getter> getting = new HashMap<String, Getter>();

	private final Map<String, Setter> setting = new HashMap<String, Setter>();

	private final Map<Setter, OgnlExpression> expression = new HashMap<Setter, OgnlExpression>();

	public void register(Object object) {
		for (Method method : object.getClass().getMethods()) {
			if (Modifier.isPublic(method.getModifiers())) {
				this.register(method.getAnnotation(GetValue.class), object, method).register(method.getAnnotation(SetValue.class), object, method);
			}
		}
	}

	private boolean emptyMethod(String name, Method method) {
		return name != null && method != null;
	}

	public void register(Object object, String method, String name) {
		for (Method each : object.getClass().getMethods()) {
			this.registerGetter(name, object, each.getName().equals(MessageFormat.format(DefaultContext.GET, method)) ? each : null);
			this.registerSetter(name, object, each.getName().equals(MessageFormat.format(DefaultContext.SET, method)) ? each : null);
		}
	}

	private void registerGetter(String getter, Object object, Method method) {
		if (this.emptyMethod(getter, method) && !method.getReturnType().toString().equals(void.class.getName())) {
			Getter get = new DefaultReader(getter, object, method);
			this.getters.add(get);
			this.getting.put(getter, get);
		}
	}

	private void registerSetter(String setter, Object object, Method method) {
		if (this.emptyMethod(setter, method) && method.getParameterTypes().length == DefaultContext.PARAM_SIZE) {
			Setter set = new DefaultWriter(setter, object, method);
			this.setters.add(set);
			this.setting.put(set.name(), set);
		}
	}

	private DefaultContext register(GetValue getter, Object object, Method method) {
		this.registerGetter(getter != null ? getter.name() : null, object, method);
		return this;
	}

	private DefaultContext register(SetValue setter, Object object, Method method) {
		this.registerSetter(setter != null ? setter.name() : null, object, method);
		return this;
	}

	public Getters readers() {
		return new DefaultGetters();
	}

	public Setters writers() {
		return new DefaultSetters();
	}

	private class DefaultGetters implements Getters {

		public Getter get(String key) {
			return DefaultContext.this.getting.get(key);
		}

		public boolean empty() {
			return DefaultContext.this.getters.isEmpty();
		}
	}

	private class DefaultSetters implements Setters {

		public void set(Map<String, Object> dump) {
			for (String key : dump.keySet()) {
				Setter setter = DefaultContext.this.setting.get(key);
				if (setter != null) {
					setter.set(dump.get(key));
				}
			}
		}

		public Set<String> setters() {
			return DefaultContext.this.setting.keySet();
		}

		public boolean empty() {
			return DefaultContext.this.setters.isEmpty();
		}
	}

	private class DefaultReader implements Getter {

		private final String name;

		private final Object object;

		private final Method method;

		public DefaultReader(String name, Object object, Method method) {
			super();
			this.name = name;
			this.method = method;
			this.object = object;
		}

		public String name() {
			return this.name;
		}

		public Object get() {
			try {
				return this.method.invoke(this.object, new Object[] {});
			} catch (Exception e) {
				DefaultContext.LOG.debug(e.getMessage(), e);
			}
			return null;
		}
	}

	private final class DefaultWriter implements Setter {

		private final String name;

		private final Object object;

		private final String method;

		public DefaultWriter(String name, Object object, Method method) {
			super();
			this.method = method.getName();
			this.object = object;
			this.name = name;
		}

		public String name() {
			return this.name;
		}

		public void set(Object value) {
			OgnlContext context = new OgnlContext();
			context.put("object", this.object);
			context.put("value", value);
			try {
				OgnlExpression expr = DefaultContext.this.expression.get(this);
				if (expr == null) {
					expr = new OgnlExpression(new StringBuffer().append("#object.").append(this.method).append("(#value)").toString());
					DefaultContext.this.expression.put(this, expr);
				}
				expr.getValue(context, context);
			} catch (Exception e) {
				DefaultContext.LOG.debug(e.getMessage(), e);
			}
		}
	}

	private class OgnlExpression {

		private final Object expression;

		public OgnlExpression(String expressionString) throws OgnlException {
			super();
			this.expression = Ognl.parseExpression(expressionString);
		}

		public Object getValue(OgnlContext context, Object rootObject) throws OgnlException {
			return Ognl.getValue(this.expression, context, rootObject);
		}
	}
}
