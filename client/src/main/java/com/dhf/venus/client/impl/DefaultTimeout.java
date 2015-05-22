package com.dhf.venus.client.impl;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dhf.venus.client.Refresh;
import com.dhf.venus.client.timeout.Timeout;

/**
 * @author kim 2015年5月21日
 */
public class DefaultTimeout implements Runnable, Timeout {

	private final static Log LOGGER = LogFactory.getLog(DefaultTimeout.class);

	private final static String TIMEOUT = "TIMEOUT";

	private final static String TIMEOUT_DEFAULT = "30000";

	private final DelayQueue<Tout> timeouts = new DelayQueue<Tout>();

	private final Set<String> uuid = new HashSet<String>();

	private final Thread thread = new Thread(this);

	private final long timeout;

	public DefaultTimeout(Properties properties) {
		super();
		this.timeout = Long.valueOf(properties.getProperty(DefaultTimeout.TIMEOUT, DefaultTimeout.TIMEOUT_DEFAULT));
	}

	@Override
	public void start(String uuid, Refresh refresh) {
		this.uuid.add(uuid);
		this.timeouts.add(new Tout(uuid, this.timeout, refresh));
	}

	@Override
	public void close(String uuid) {
		this.uuid.remove(uuid);
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				Tout out = this.timeouts.take();
				if (this.uuid.contains(out.uuid())) {
					DefaultTimeout.LOGGER.warn("Request " + out.uuid() + " is timeout");
					this.uuid.remove(out.uuid);
					out.refresh();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void init() {
		this.thread.start();
	}

	public void destory() {
		this.thread.interrupt();
	}

	private class Tout implements Delayed {

		private final String uuid;

		private final long timeout;

		private final Refresh refresh;

		public Tout(String uuid, long timeout, Refresh refresh) {
			super();
			this.uuid = uuid;
			this.refresh = refresh;
			this.timeout = System.currentTimeMillis() + timeout;
		}

		public long getDelay(TimeUnit unit) {
			return unit.convert(this.timeout - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		}

		public int compareTo(Delayed o) {
			return o.getDelay(TimeUnit.MILLISECONDS) >= this.getDelay(TimeUnit.MILLISECONDS) ? 1 : -1;
		}

		public String uuid() {
			return this.uuid;
		}

		public void refresh() {
			this.refresh.refresh();
		}
	}

}
