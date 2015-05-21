package com.dhf.venus.server.command.convert;

import com.dhf.venus.event.Event;
import com.dhf.venus.event.EventFactory;
import com.dhf.venus.server.access.Access;
import com.dhf.venus.server.command.CommandConvert;

/**
 * @author kim 2015年5月21日
 */
public class EventPushConvert implements CommandConvert {

	private final Access access;

	private final EventFactory factory;

	public EventPushConvert(Access access, EventFactory factory) {
		super();
		this.access = access;
		this.factory = factory;
	}

	@Override
	public Event convert(String command) {
		String[] configs = command.split(" ");
		return this.factory.generate(Event.ACTION_PULL).data(this.access.write(configs[0], configs[1], configs[2]).configs());
	}
}
