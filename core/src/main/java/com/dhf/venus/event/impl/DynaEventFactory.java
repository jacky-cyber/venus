package com.dhf.venus.event.impl;

import java.util.Properties;
import java.util.UUID;

import com.dhf.venus.event.Event;
import com.dhf.venus.event.EventFactory;

/**
 * @author kim 2015年5月19日
 */
public class DynaEventFactory implements EventFactory {

	private final Properties properties;

	public DynaEventFactory(Properties properties) {
		super();
		this.properties = properties;
	}

	public Event generate() {
		return this.generate(null);
	}

	public Event generate(String action) {
		return new DynaEvent().req(UUID.randomUUID().toString()).client(this.properties.get(Event.CLIENT).toString()).env(this.properties.get(Event.CLIENT_ENV).toString()).action(action);
	}
}
