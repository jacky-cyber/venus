package com.dhf.venus.client.connect;

import com.dhf.venus.client.update.Updated;
import com.dhf.venus.connect.Listener;
import com.dhf.venus.event.Event;

/**
 * @author kim 2015年5月19日
 */
public class UpdateListener implements Listener {

	private final Updated updated;

	public UpdateListener(Updated updated) {
		super();
		this.updated = updated;
	}

	public void listen(Event event) {
		this.updated.update(event);
	}
}
