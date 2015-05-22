package com.dhf.venus.client.timeout;

import com.dhf.venus.client.Refresh;

/**
 * @author kim 2015年5月21日
 */
public interface Timeout {

	public void start(String uuid, Refresh refresh);

	public void close(String uuid);
}
