package com.dhf.venus.connect;

import com.dhf.venus.event.Event;

/**
 * @author kim 2015年5月19日
 */
public interface Pusher {

	public void push(Event event);

	public void push(Object dest, Event event);
}
