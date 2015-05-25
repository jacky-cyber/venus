package com.dhf.venus.event;

import java.util.Map;

/**
 * @author kim 2015年5月18日
 */
public interface Event {

	public final static String CLIENT = "CLIENT";

	public final static String CLIENT_ENV = "CLIENT_ENV";
	
	public final static String CLIENT_REQ = "CLIENT_REQ";

	public final static String CLIENT_FROM = "CLIENT_FROM";

	public final static String ACTION = "ACTION";
	
	public final static String ACTION_ACK = "ACTION_ACK";

	public final static String ACTION_PULL = "ACTION_PULL";

	public final static String ACTION_PUSH = "ACTION_PUSH";

	public final static String ACTION_DATA = "ACTION_DATA";

	public String req();

	public Event req(String req);

	public String env();

	public Event env(String env);

	public Event from(Object from);
	
	public <T> T from(Class<T> clazz);

	public String client();

	public Event client(String client);
	
	public String action();

	public Event action(String action);

	public Event data(Object data);

	public <T> T data(Class<T> clazz);

	public Map<String, Object> dump();

	public Event restore(Map<String, Object> dump);
}
