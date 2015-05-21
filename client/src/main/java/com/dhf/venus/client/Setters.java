package com.dhf.venus.client;

import java.util.Map;
import java.util.Set;

/**
 * @author kim 2015年5月20日
 */
public interface Setters {

	public void set(Map<String, Object> dump);

	public Set<String> setters();

	public boolean empty();
}
