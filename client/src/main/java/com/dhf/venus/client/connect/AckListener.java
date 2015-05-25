package com.dhf.venus.client.connect;

import org.jgroups.Address;

import com.dhf.venus.connect.Pusher;
import com.dhf.venus.connect.impl.ReplyListener;
import com.dhf.venus.event.Event;
import com.dhf.venus.event.EventFactory;

/**
 * @author kim 2015年5月22日
 */
public class AckListener extends ReplyListener {

	private final EventFactory factory;

	public AckListener(Pusher pusher, EventFactory factory) {
		super(pusher);
		this.factory = factory;
	}

	@Override
	public void listen(Event event) {
		super.pusher.push(event.from(Address.class), this.factory.generate(Event.ACTION_ACK).req(event.req()));
	}
}
