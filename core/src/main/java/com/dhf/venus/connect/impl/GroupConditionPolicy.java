package com.dhf.venus.connect.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jgroups.Address;
import org.jgroups.protocols.pbcast.GMS.DefaultMembershipPolicy;
import org.jgroups.util.ExtendedUUID;

/**
 * @author kim 2015年5月25日
 */
public class GroupConditionPolicy extends DefaultMembershipPolicy {

	private final AddressComparator comparator = new AddressComparator();

	@Override
	public List<Address> getNewMembership(Collection<Address> current_members, Collection<Address> joiners, Collection<Address> leavers, Collection<Address> suspects) {
		List<Address> address = super.getNewMembership(current_members, joiners, leavers, suspects);
		Collections.sort(address, this.comparator);
		return address;
	}

	@Override
	public List<Address> getNewMembership(Collection<Collection<Address>> subviews) {
		List<Address> address = super.getNewMembership(subviews);
		Collections.sort(address, this.comparator);
		return address;
	}

	public class AddressComparator implements Comparator<Address> {
		@Override
		public int compare(Address o1, Address o2) {
			ExtendedUUID address1 = ExtendedUUID.class.cast(o1);
			ExtendedUUID address2 = ExtendedUUID.class.cast(o1);
			return address1.isFlagSet((byte)0) ? -1 : address1.compareTo(address2);
		}
	}
}
