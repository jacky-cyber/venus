package com.dhf.venus.client.timeout;

/**
 * @author kim 2015年5月21日
 */
public interface Timeout {

	public void start(String uuid);

	public void close(String uuid);
}
