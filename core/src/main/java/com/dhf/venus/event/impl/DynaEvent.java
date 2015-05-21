package com.dhf.venus.event.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.dhf.venus.event.Event;

/**
 * @author kim 2015年5月19日
 */
public class DynaEvent implements Event {

	private final Map<String, Object> attributes = new HashMap<String, Object>();

	public String req() {
		return this.getAsString(Event.CLIENT_REQ);
	}

	public Event req(String req) {
		return this.put(Event.CLIENT_REQ, req);
	}

	public String env() {
		return this.getAsString(Event.CLIENT_ENV);
	}

	public Event env(String env) {
		return this.put(Event.CLIENT_ENV, env);
	}

	public <T> T from(Class<T> clazz) {
		return clazz.cast(this.get(Event.CLIENT_FROM));
	}

	public Event from(Object from) {
		return this.put(Event.CLIENT_FROM, from);
	}

	public String client() {
		return this.getAsString(Event.CLIENT);
	}

	public Event client(String client) {
		return this.put(Event.CLIENT, client);
	}

	public String action() {
		return this.getAsString(Event.ACTION);
	}

	public Event action(String from) {
		return this.put(Event.ACTION, from);
	}

	private Object get(String key) {
		return this.attributes.get(key);
	}

	private String getAsString(String key) {
		Object value = this.get(key);
		return value != null ? value.toString() : null;
	}

	private Event put(String key, Object value) {
		this.attributes.put(key, value);
		return this;
	}

	public Event data(Object data) {
		this.attributes.put(Event.ACTION_DATA, data);
		return this;
	}

	public <T> T data(Class<T> clazz) {
		return clazz.cast(this.attributes.get(Event.ACTION_DATA));
	}

	public Map<String, Object> dump() {
		return Collections.unmodifiableMap(this.attributes);
	}

	public Event restore(Map<String, Object> dump) {
		this.attributes.putAll(dump);
		return this;
	}
}
