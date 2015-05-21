package com.dhf.venus.server.command.impl;

import com.dhf.venus.connect.Pusher;
import com.dhf.venus.server.command.Command;
import com.dhf.venus.server.command.CommandConvert;

/**
 * @author kim 2015年5月21日
 */
public class BroadcastCommand implements Command {

	private final CommandConvert convert;

	private final Pusher pusher;

	public BroadcastCommand(CommandConvert convert, Pusher pusher) {
		super();
		this.convert = convert;
		this.pusher = pusher;
	}

	@Override
	public void command(String command) {
		this.pusher.push(this.convert.convert(command));
	}
}
