package com.dhf.venus.client.update.impl;

import com.dhf.venus.client.timeout.Timeout;
import com.dhf.venus.client.update.Updated;
import com.dhf.venus.event.Event;

/**
 * @author kim 2015年5月21日
 */
public class TimeoutUpdated implements Updated {

	private final Timeout timeout;

	public TimeoutUpdated(Timeout timeout) {
		super();
		this.timeout = timeout;
	}

	@Override
	public void update(Event event) {
		this.timeout.close(event.req());
	}
}
