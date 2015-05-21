package com.dhf.venus.server.command.impl;

import java.util.List;

import com.dhf.venus.server.command.Command;

/**
 * @author kim 2015年5月21日
 */
public class ChainedCommand implements Command {

	private final List<Command> commands;

	public ChainedCommand(List<Command> commands) {
		super();
		this.commands = commands;
	}

	@Override
	public void command(String command) {
		for (Command each : this.commands) {
			each.command(command);
		}
	}
}
