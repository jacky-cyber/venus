package com.dhf.venus.convert.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

import com.dhf.venus.convert.Convert;
import com.dhf.venus.event.Event;
import com.dhf.venus.event.EventFactory;
import com.dhf.venus.impl.VenusException;

/**
 * @author kim 2015年5月12日
 */
public class DefaultConvert implements Convert {

	private final static Log LOGGER = LogFactory.getLog(DefaultConvert.class);

	private final static ObjectMapper MAPPER = new ObjectMapper();

	private final EventFactory factory;

	public DefaultConvert(EventFactory factory) {
		super();
		this.factory = factory;
	}

	public byte[] bytes(Object object) {
		try {
			return MAPPER.writeValueAsBytes(object);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new VenusException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Event object(byte[] bytes) {
		try {
			return this.factory.generate().restore(MAPPER.readValue(bytes, Map.class));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new VenusException(e);
		}
	}
}
