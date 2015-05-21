package com.dhf.venus.server.access.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.dhf.venus.server.access.Access;
import com.dhf.venus.server.access.Configs;
import com.dhf.venus.server.mongo.Dictionary;
import com.dhf.venus.server.mongo.MongoConfig;
import com.dhf.venus.server.mongo.impl.MongoUtils;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * @author kim 2015年5月20日
 */
public class MongoAccess implements Access {

	private final String host;

	private final MongoConfig config;

	private final MongoConfig access;

	public MongoAccess(MongoConfig config, MongoConfig access) throws UnknownHostException {
		super();
		this.config = config;
		this.access = access;
		this.host = InetAddress.getLocalHost().getHostName();
	}

	private boolean lock(String req) {
		String uuid = UUID.randomUUID().toString();
		return uuid.equals(MongoUtils.asString(this.access.collection().findAndModify(BasicDBObjectBuilder.start(Dictionary.FIELD_REQ, req).get(), null, null, false, BasicDBObjectBuilder.start().add("$setOnInsert", BasicDBObjectBuilder.start().add(Dictionary.FIELD_HOST, this.host).add(Dictionary.FIELD_TIME, System.currentTimeMillis()).add(Dictionary.FIELD_REQ, req).add(Dictionary.FIELD_UUID, uuid).get()).get(), true, true), Dictionary.FIELD_UUID));
	}

	public Configs write(String key, String value, String env) {
		this.config.collection().update(BasicDBObjectBuilder.start().add(Dictionary.FIELD_NAME, key).add(Dictionary.FIELD_ENV, env).get(), BasicDBObjectBuilder.start("$set", BasicDBObjectBuilder.start().add(Dictionary.FIELD_NAME, key).add(Dictionary.FIELD_VALUE, value).add(Dictionary.FIELD_ENV, env).add(Dictionary.FIELD_TIME, System.currentTimeMillis()).add(Dictionary.FIELD_HOST, this.host).get()).get(), true, false);
		Map<String, Object> configs = new HashMap<String, Object>();
		configs.put(key, value);
		return new MongoConfigs(env, configs);
	}

	@Override
	public Configs read(String env, String req, List<String> params) {
		Map<String, Object> configs = new HashMap<String, Object>();
		if (this.lock(req)) {
			try (DBCursor cursor = this.config.collection().find(BasicDBObjectBuilder.start().add(Dictionary.FIELD_NAME, BasicDBObjectBuilder.start("$in", params).get()).add(Dictionary.FIELD_ENV, env).get())) {
				while (cursor.hasNext()) {
					DBObject each = cursor.next();
					configs.put(MongoUtils.asString(each, Dictionary.FIELD_NAME), MongoUtils.asString(each, Dictionary.FIELD_VALUE));
				}
			}
		}
		return new MongoConfigs(env, configs);
	}

	private final class MongoConfigs implements Configs {

		private final Map<String, Object> configs;

		private final String env;

		public MongoConfigs(String env, Map<String, Object> configs) {
			super();
			this.configs = configs;
			this.env = env;
		}

		public String env() {
			return this.env;
		}

		@Override
		public boolean valid() {
			return !this.configs.isEmpty();
		}

		@Override
		public Map<String, Object> configs() {
			return this.configs;
		}
	}
}
