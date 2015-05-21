package com.dhf.venus.connect.impl;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.Message;

import com.dhf.venus.connect.Pusher;
import com.dhf.venus.convert.Convert;
import com.dhf.venus.event.Event;
import com.dhf.venus.impl.VenusException;

/**
 * @author kim 2015年5月19日
 */
public class GroupPusher implements Pusher {

	private final Convert convert;

	private final JChannel channel;

	public GroupPusher(Convert convert, JChannel channel) {
		super();
		this.convert = convert;
		this.channel = channel;
	}

	@Override
	public void push(Event event) {
		this.push(null, event);
	}

	public void push(Object dest, Event event) {
		try {
			this.channel.send(new Message(Address.class.cast(dest), this.convert.bytes(event.dump())));
		} catch (Exception e) {
			throw new VenusException(e);
		}
	}
}
