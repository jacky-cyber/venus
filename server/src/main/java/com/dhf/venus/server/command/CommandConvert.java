package com.dhf.venus.server.command;

import com.dhf.venus.event.Event;

/**
 * @author kim 2015年5月21日
 */
public interface CommandConvert {

	public Event convert(String command);
}
