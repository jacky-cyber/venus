package com.dhf.venus.client.action;

import com.dhf.venus.client.Request;
import com.dhf.venus.client.Setters;
import com.dhf.venus.client.timeout.Timeout;
import com.dhf.venus.connect.Pusher;
import com.dhf.venus.event.Event;
import com.dhf.venus.event.EventFactory;

/**
 * @author kim 2015年5月19日
 */
public class DefaultRequest implements Request {

	private final Pusher pusher;

	private final Timeout timeout;

	private final EventFactory factory;

	public DefaultRequest(Pusher pusher, Timeout timeout, EventFactory factory) {
		super();
		this.pusher = pusher;
		this.timeout = timeout;
		this.factory = factory;
	}

	public void request(Setters setters) {
		if (!setters.empty()) {
			Event event = this.factory.generate(Event.ACTION_PULL).data(setters.setters());
			this.pusher.push(event);
			this.timeout.start(event.req());
		}
	}
}
