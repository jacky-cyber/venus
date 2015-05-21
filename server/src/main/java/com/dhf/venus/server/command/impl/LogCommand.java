package com.dhf.venus.server.command.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dhf.venus.server.command.Command;

/**
 * @author kim 2015年5月21日
 */
public class LogCommand implements Command {

	private final static Log LOGGER = LogFactory.getLog(LogCommand.class);

	@Override
	public void command(String command) {
		LOGGER.debug(command);
	}
}
