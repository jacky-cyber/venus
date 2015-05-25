package com.dhf.venus.server.command.convert;

import com.dhf.venus.event.Event;
import com.dhf.venus.event.EventFactory;
import com.dhf.venus.server.access.Access;
import com.dhf.venus.server.access.Configs;
import com.dhf.venus.server.command.CommandConvert;

/**
 * @author kim 2015年5月21日
 */
public class PushConvert implements CommandConvert {

	private final Access access;

	private final EventFactory factory;

	public PushConvert(Access access, EventFactory factory) {
		super();
		this.access = access;
		this.factory = factory;
	}

	@Override
	public Event convert(String command) {
		String[] params = command.split(" ");
		Configs configs = this.access.write(params[0].trim(), params[1].trim(), params[2].trim());
		return this.factory.generate(Event.ACTION_PUSH).data(configs.configs()).env(configs.env());
	}
}
