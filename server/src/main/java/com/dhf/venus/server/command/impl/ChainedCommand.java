package com.dhf.venus.server.command.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dhf.venus.server.command.Command;

/**
 * @author kim 2015年5月21日
 */
public class ChainedCommand implements Command {

	private final static Log LOGGER = LogFactory.getLog(ChainedCommand.class);

	private final List<Command> commands;

	public ChainedCommand(List<Command> commands) {
		super();
		this.commands = commands;
	}

	@Override
	public void command(String command) {
		for (Command each : this.commands) {
			try {
				each.command(command);
			} catch (Exception e) {
				ChainedCommand.LOGGER.info(e.getMessage(), e);
			}
		}
	}
}
