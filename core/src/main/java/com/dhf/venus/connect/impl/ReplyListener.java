package com.dhf.venus.connect.impl;

import com.dhf.venus.connect.Listener;
import com.dhf.venus.connect.Pusher;

/**
 * @author kim 2015年5月19日
 */
public abstract class ReplyListener implements Listener {

	protected Pusher pusher;

	public ReplyListener(Pusher pusher) {
		super();
		this.pusher = pusher;
	}
}
