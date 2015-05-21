package com.dhf.venus.connect.impl;

import java.util.Properties;

import org.jgroups.JChannel;

import com.dhf.venus.event.Event;

/**
 * @author kim 2015年5月19日
 */
public class GroupConnection {

	private final JChannel channel;

	private final String cluster;

	public GroupConnection(String cluster, JChannel channel, Properties properties) throws Exception {
		super();
		this.cluster = cluster;
		this.channel = channel;
		this.channel.setName(properties.getProperty(Event.CLIENT));
	}

	public void init() throws Exception {
		this.channel.connect(this.cluster);
	}

	public void destory() {
		this.channel.disconnect();
	}
}
