package com.dhf.venus.client.connect;

import com.dhf.venus.connect.impl.GroupAddressGenerator;

/**
 * @author kim 2015年5月25日
 */
public class ClientAddressGenerator extends GroupAddressGenerator {

	@Override
	public boolean condition() {
		return false;
	}
}
