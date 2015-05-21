package com.dhf.venus.server.connect;

import java.util.List;

import org.jgroups.Address;

import com.dhf.venus.connect.Pusher;
import com.dhf.venus.connect.impl.ReplyListener;
import com.dhf.venus.event.Event;
import com.dhf.venus.event.EventFactory;
import com.dhf.venus.server.access.Access;
import com.dhf.venus.server.access.Configs;

/**
 * @author kim 2015年5月19日
 */
public class PushConfigsListener extends ReplyListener {

	private final EventFactory factory;

	private final Access access;

	public PushConfigsListener(Pusher pusher, Access access, EventFactory factory) {
		super(pusher);
		this.access = access;
		this.factory = factory;
	}

	@SuppressWarnings("unchecked")
	public void listen(Event event) {
		Configs configs = this.access.read(event.env(), event.req(), event.data(List.class));
		if(configs.valid()){
			super.pusher.push(event.from(Address.class), this.factory.generate(Event.ACTION_PULL).env(configs.env()).data(configs.configs()).req(event.req()));
		}
	}
}