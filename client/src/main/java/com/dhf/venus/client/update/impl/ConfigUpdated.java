package com.dhf.venus.client.update.impl;

import java.util.Map;

import com.dhf.venus.client.Context;
import com.dhf.venus.client.update.Updated;
import com.dhf.venus.event.Event;

/**
 * @author kim 2015年5月21日
 */
public class ConfigUpdated implements Updated {
	
	private final Context context;

	public ConfigUpdated(Context context) {
		this.context = context;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Event event) {
		this.context.writers().set(event.data(Map.class));
	}
}
