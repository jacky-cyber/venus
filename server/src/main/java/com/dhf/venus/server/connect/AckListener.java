package com.dhf.venus.server.connect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jgroups.Address;

import com.dhf.venus.connect.Listener;
import com.dhf.venus.event.Event;

/**
 * @author kim 2015年5月22日
 */
public class AckListener implements Listener {

	private final static Log LOGGER = LogFactory.getLog(AckListener.class);

	@Override
	public void listen(Event event) {
		AckListener.LOGGER.info("Ack from " + event.from(Address.class) + " for req " + event.req());
	}
}
