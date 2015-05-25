package com.dhf.venus.server.command.convert;

import java.util.Map;

import com.dhf.venus.event.Event;
import com.dhf.venus.server.command.CommandConvert;

/**
 * @author kim 2015年5月25日
 */
public class PrefixConvert implements CommandConvert {

	private final Map<String, CommandConvert> converts;

	public PrefixConvert(Map<String, CommandConvert> converts) {
		super();
		this.converts = converts;
	}

	@Override
	public Event convert(String command) {
		String[] params = command.split(" ");
		return this.converts.get(params[0].toUpperCase()).convert(new StringBuffer().append(command.replaceFirst(params[0], "")).toString().trim());
	}
}
