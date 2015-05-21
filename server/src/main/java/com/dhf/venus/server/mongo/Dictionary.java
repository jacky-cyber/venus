package com.dhf.venus.server.mongo;

/**
 * 配置取值器
 * 
 * @author kim 2013-11-15
 */
public interface Dictionary {

	/**
	 * 数据库名称
	 */
	public final static String D_NAME = "D_NAME";

	/**
	 * 数据集合名称
	 */
	public final static String C_NAME = "C_NAME";

	public final static String FIELD_ID = "_id";

	public final static String FIELD_ENV = "env";
	
	public final static String FIELD_REQ = "req";
	
	public final static String FIELD_UUID = "uuid";
	
	public final static String FIELD_TIME = "time";
	
	public final static String FIELD_HOST = "host";

	public final static String FIELD_NAME = "name";

	public final static String FIELD_VALUE = "value";
	
	public Object get(String key);
}
