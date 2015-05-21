package com.dhf.venus.client.action;

import com.dhf.venus.client.Context;
import com.dhf.venus.client.Refresh;
import com.dhf.venus.client.timeout.Timeout;
import com.dhf.venus.connect.Pusher;
import com.dhf.venus.event.EventFactory;

/**
 * @author kim 2015年5月20日
 */
public class DefaultRefresh extends DefaultRequest implements Refresh {

	private final Context context;

	public DefaultRefresh(Pusher pusher, Timeout timeout, Context context, EventFactory factory) {
		super(pusher, timeout, factory);
		this.context = context;
	}

	public void refresh() {
		super.request(this.context.writers());
	}
}
