package com.dhf.venus.connect.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;

import com.dhf.venus.connect.Interest;
import com.dhf.venus.convert.Convert;
import com.dhf.venus.event.Event;

/**
 * @author kim 2015年5月12日
 */
public class GroupReceiver extends ReceiverAdapter {

	private final static Log LOGGER = LogFactory.getLog(GroupReceiver.class);

	private final Interest interest;

	private final Convert convert;

	public GroupReceiver(Convert convert, Interest interest) throws Exception {
		super();
		this.interest = interest;
		this.convert = convert;
	}

	public void viewAccepted(View view) {
		GroupReceiver.LOGGER.info(view);
	}

	public void receive(Message message) {
		LOGGER.info("Venus receive message " + message);
		Event event = this.convert.object(message.getBuffer()).from(message.getSrc());
		LOGGER.info("Venus receive event " + event.dump());
		this.interest.interest(event);
	}
}
