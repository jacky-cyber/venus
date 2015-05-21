package com.dhf.venus.client.process;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.util.StringUtils;

import com.dhf.venus.client.Register;

/**
 * @author kim 2015年5月20日
 */
public class RegisterProcessor extends InstantiationAwareBeanPostProcessorAdapter {

	private final Register register;

	public RegisterProcessor(Register register) {
		super();
		this.register = register;
	}

	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		this.register.register(bean);
		return true;
	}

	public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
		for (PropertyValue pv : pvs.getPropertyValues()) {
			this.register(bean, pv);
		}
		return super.postProcessPropertyValues(pvs, pds, bean, beanName);
	}

	private void register(Object bean, PropertyValue pv) {
		if (pv.getValue().getClass().isAssignableFrom(TypedStringValue.class)) {
			String property = TypedStringValue.class.cast(pv.getValue()).getValue();
			if (property.startsWith("${") && property.endsWith("}")) {
				this.register.register(bean, StringUtils.capitalize(pv.getName()), property.replaceFirst("\\$\\{", "").replaceFirst("\\}", ""));
			}
		}
	}
}
