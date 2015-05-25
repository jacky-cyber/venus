package com.dhf.venus.connect.impl;

import org.jgroups.Address;
import org.jgroups.stack.AddressGenerator;
import org.jgroups.util.ExtendedUUID;

/**
 * @author kim 2015年5月25日
 */
abstract public class GroupAddressGenerator implements AddressGenerator {

	@Override
	public Address generateAddress() {
		ExtendedUUID uuid = ExtendedUUID.randomUUID();
		return this.condition() ? uuid.setFlag((byte) 0) : uuid;
	}

	abstract public boolean condition();
}
