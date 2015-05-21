package com.dhf.venus.connect.impl;

import java.util.List;

import com.dhf.venus.connect.Listener;
import com.dhf.venus.event.Event;

/**
 * @author kim 2015年5月19日
 */
public class ChainedListener implements Listener {

	private final List<Listener> listeners;

	public ChainedListener(List<Listener> listeners) {
		super();
		this.listeners = listeners;
	}

	@Override
	public void listen(Event event) {
		for (Listener listener : this.listeners) {
			listener.listen(event);
		}
	}
}
