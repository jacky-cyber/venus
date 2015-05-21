package com.dhf.venus.client.update.impl;

import java.util.List;

import com.dhf.venus.client.update.Updated;
import com.dhf.venus.event.Event;

/**
 * @author kim 2015年5月21日
 */
public class ChainedUpdated implements Updated {

	private final List<Updated> updateds;

	public ChainedUpdated(List<Updated> updateds) {
		super();
		this.updateds = updateds;
	}

	@Override
	public void update(Event event) {
		for (Updated updated : this.updateds) {
			updated.update(event);
		}
	}
}
