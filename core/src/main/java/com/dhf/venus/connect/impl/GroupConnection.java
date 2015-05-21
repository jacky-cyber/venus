package com.dhf.venus.connect.impl;

import java.util.Properties;

import org.jgroups.JChannel;
import org.jgroups.Receiver;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.Resource;

import com.dhf.venus.event.Event;

/**
 * @author kim 2015年5月19日
 */
public class GroupConnection implements FactoryBean<JChannel> {

	private final static String FORCE_IPV4 = "java.net.preferIPv4Stack";

	private final JChannel channel;

	private final String cluster;

	public GroupConnection(String cluster, Resource config, Receiver receiver, Properties properties) throws Exception {
		super();
		System.setProperty(GroupConnection.FORCE_IPV4, Boolean.TRUE.toString());
		this.cluster = cluster;
		this.channel = new JChannel(config.getInputStream());
		this.channel.setReceiver(receiver);
		this.channel.setName(properties.getProperty(Event.CLIENT));
	}

	public void init() throws Exception {
		this.channel.connect(this.cluster);
	}

	public void destory() {
		this.channel.disconnect();
	}

	@Override
	public JChannel getObject() throws Exception {
		return this.channel;
	}

	@Override
	public Class<JChannel> getObjectType() {
		return JChannel.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
