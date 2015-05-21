package com.dhf.venus.connect.impl;

import java.util.Map;

import com.dhf.venus.connect.Interest;
import com.dhf.venus.connect.Listener;
import com.dhf.venus.event.Event;

/**
 * @author kim 2015年5月19日
 */
public class DefaultInterest implements Interest {

	private Map<String, Listener> listeners;

	public void setListeners(Map<String, Listener> listeners) {
		this.listeners = listeners;
	}

	@Override
	public void interest(Event event) {
		Listener listener = this.listeners.get(event.action());
		if (listener != null) {
			listener.listen(event);
		}
	}
}
