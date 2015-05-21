package com.dhf.venus.event;

/**
 * @author kim 2015年5月19日
 */
public interface EventFactory {

	public Event generate();

	public Event generate(String action);
}
