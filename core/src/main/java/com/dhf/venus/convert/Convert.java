package com.dhf.venus.convert;

import com.dhf.venus.event.Event;

/**
 * @author kim 2015年5月12日
 */
public interface Convert {

	public byte[] bytes(Object object);

	public Event object(byte[] bytes);
}
