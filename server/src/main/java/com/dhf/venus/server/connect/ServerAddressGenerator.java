package com.dhf.venus.server.connect;

import com.dhf.venus.connect.impl.GroupAddressGenerator;

/**
 * @author kim 2015年5月25日
 */
public class ServerAddressGenerator extends GroupAddressGenerator {

	@Override
	public boolean condition() {
		return true;
	}
}
