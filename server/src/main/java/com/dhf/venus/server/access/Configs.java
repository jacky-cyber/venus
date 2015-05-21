package com.dhf.venus.server.access;

import java.util.Map;

/**
 * @author kim 2015年5月20日
 */
public interface Configs {
	
	public String env();

	public boolean valid();
	
	public Map<String, Object> configs();
}
