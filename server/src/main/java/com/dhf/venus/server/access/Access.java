package com.dhf.venus.server.access;

import java.util.List;

/**
 * @author kim 2015年5月20日
 */
public interface Access {

	public Configs write(String key, String value, String env);

	public Configs read(String env, String req, List<String> params);
}
