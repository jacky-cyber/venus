package com.dhf.venus.client.connect;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dhf.venus.connect.impl.DefaultInterest;
import com.dhf.venus.event.Event;

/**
 * @author kim 2015年5月19日
 */
public class ClientInterest extends DefaultInterest {

	private final static Log LOGGER = LogFactory.getLog(ClientInterest.class);

	private final String server;

	private final String env;

	public ClientInterest(String server, Properties properties) {
		this.server = server;
		this.env = properties.getProperty(Event.CLIENT_ENV);
	}

	@Override
	public void interest(Event event) {
		ClientInterest.LOGGER.info("Interest condtion: " + event.client() + "(" + this.server.equalsIgnoreCase(event.client()) + ") " + event.env() + "(" + this.env.equalsIgnoreCase(event.env()) + ")");
		if (this.server.equalsIgnoreCase(event.client()) && this.env.equalsIgnoreCase(event.env())) {
			super.interest(event);
		}
	}
}
