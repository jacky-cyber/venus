package com.dhf.venus.server.connect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dhf.venus.connect.impl.DefaultInterest;
import com.dhf.venus.event.Event;

/**
 * @author kim 2015年5月19日
 */
public class ServerInterest extends DefaultInterest {

	private final static Log LOGGER = LogFactory.getLog(ServerInterest.class);

	private final String server;

	public ServerInterest(String server) {
		this.server = server;
	}

	@Override
	public void interest(Event event) {
		ServerInterest.LOGGER.info("Interest condtion: " + event.client() + "(" + !this.server.equalsIgnoreCase(event.client()) + ")");
		if (!this.server.equalsIgnoreCase(event.client())) {
			super.interest(event);
		}
	}
}
