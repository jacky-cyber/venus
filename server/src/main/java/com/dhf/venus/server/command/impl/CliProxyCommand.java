package com.dhf.venus.server.command.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dhf.venus.server.command.Command;

/**
 * @author kim 2015年5月21日
 */
public class CliProxyCommand implements Command {

	private final static Log LOGGER = LogFactory.getLog(CliProxyCommand.class);

	private final BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

	private final Thread thread = new Thread(new CliRunnable());

	private final Command command;

	public CliProxyCommand(Command command) throws IOException {
		super();
		this.command = command;
	}

	public void init() {
		this.thread.start();
	}

	public void destory() {
		this.thread.interrupt();
	}

	@Override
	public void command(String command) {
		this.command.command(command);
	}

	private class CliRunnable implements Runnable {

		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					CliProxyCommand.this.command(CliProxyCommand.this.console.readLine());
				} catch (IOException e) {
					CliProxyCommand.LOGGER.info(e.getMessage(), e);
				}
			}
		}
	}
}
