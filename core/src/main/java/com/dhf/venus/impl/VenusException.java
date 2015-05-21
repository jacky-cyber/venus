package com.dhf.venus.impl;

/**
 * @author kim 2015年5月12日
 */
public class VenusException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VenusException(String e) {
		super(e);
	}

	public VenusException(Exception e) {
		super(e);
	}
}
