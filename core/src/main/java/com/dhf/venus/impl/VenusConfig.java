package com.dhf.venus.impl;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import org.springframework.core.io.Resource;

/**
 * @author kim 2015年5月19日
 */
public class VenusConfig extends Properties {

	private final static long serialVersionUID = 1L;

	public VenusConfig(Resource resource) {
		try (Reader reader = new InputStreamReader(resource.getInputStream())) {
			super.load(reader);
		} catch (Exception e) {
			new VenusException(e);
		}
	}

	public String getProperty(String key) {
		String value = super.getProperty(key);
		return value != null ? value : System.getProperty(key);
	}
}
