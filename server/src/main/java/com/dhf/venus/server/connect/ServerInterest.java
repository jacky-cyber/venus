package com.dhf.venus.server.connect;

import com.dhf.venus.connect.impl.DefaultInterest;
import com.dhf.venus.event.Event;

/**
 * @author kim 2015年5月19日
 */
public class ServerInterest extends DefaultInterest {

	private final String server;

	public ServerInterest(String server) {
		this.server = server;
	}

	@Override
	public void interest(Event event) {
		if (!this.server.equalsIgnoreCase(event.client())) {
			super.interest(event);
		}
	}
}
