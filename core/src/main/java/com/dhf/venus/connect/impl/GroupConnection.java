package com.dhf.venus.connect.impl;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jgroups.JChannel;
import org.jgroups.Receiver;
import org.jgroups.protocols.TCPGOSSIP;
import org.jgroups.stack.AddressGenerator;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import com.dhf.venus.event.Event;

/**
 * @author kim 2015年5月19日
 */
public class GroupConnection implements FactoryBean<JChannel> {

	private final static Log LOGGER = LogFactory.getLog(GroupConnection.class);

	private final static String FORCE_IPV4 = "java.net.preferIPv4Stack";

	private final static String CLIENT_GOSSIP = "CLIENT_GOSSIP";

	private final JChannel channel;

	private final String cluster;

	public GroupConnection(String cluster, Resource config, Receiver receiver, AddressGenerator generator, Properties properties) throws Exception {
		super();
		System.setProperty(GroupConnection.FORCE_IPV4, Boolean.TRUE.toString());
		this.cluster = cluster;
		this.channel = new JChannel(config.getInputStream());
		this.channel.setReceiver(receiver);
		this.channel.addAddressGenerator(generator);
		this.channel.setName(properties.getProperty(Event.CLIENT) + "_" + InetAddress.getLocalHost().getHostName());
		this.gossip(properties.getProperty(GroupConnection.CLIENT_GOSSIP));
	}

	private void gossip(String hosts) {
		TCPGOSSIP gossip = TCPGOSSIP.class.cast(this.channel.getProtocolStack().findProtocol(TCPGOSSIP.class));
		if (gossip != null && gossip.getInitialHosts().isEmpty() && !StringUtils.isEmpty(hosts)) {
			gossip.setInitialHosts(this.address(hosts));
		}
	}

	private List<InetSocketAddress> address(String hosts) {
		List<InetSocketAddress> addresses = new ArrayList<InetSocketAddress>();
		for (String gossip : hosts.split(";")) {
			String[] host_port = gossip.split(":");
			GroupConnection.LOGGER.debug("Host: " + Arrays.toString(host_port));
			addresses.add(InetSocketAddress.createUnresolved(host_port[0], Integer.valueOf(host_port[1])));
		}
		return addresses;
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
