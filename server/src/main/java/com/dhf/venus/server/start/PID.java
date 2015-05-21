package com.dhf.venus.server.start;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

/**
 * @author kim 2015年5月21日
 */
public class PID {

	private final static String PATH = "PID";

	private final Properties properties;

	public PID(Properties properties) {
		super();
		this.properties = properties;
	}

	public void pid() throws IOException {
		FileUtils.write(new File(this.properties.get(PID.PATH).toString()), ManagementFactory.getRuntimeMXBean().getName().replaceFirst("@.*", ""));
	}
}
