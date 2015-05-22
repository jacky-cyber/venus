package com.dhf.venus.server.command.convert;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dhf.venus.event.Event;
import com.dhf.venus.server.command.CommandConvert;

/**
 * @author kim 2015年5月22日
 */
public class ChainedPushConvert implements CommandConvert {

	private final static Log LOGGER = LogFactory.getLog(ChainedPushConvert.class);

	private final List<CommandConvert> converts;

	public ChainedPushConvert(List<CommandConvert> converts) {
		super();
		this.converts = converts;
	}

	@Override
	public Event convert(String command) {
		for (CommandConvert convert : this.converts) {
			Event event = convert.convert(command);
			if (event != null) {
				ChainedPushConvert.LOGGER.debug("Event after convert: " + convert);
				return event;
			}
		}
		return null;
	}
}
