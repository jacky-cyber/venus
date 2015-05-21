package com.dhf.venus.client.process;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.dhf.venus.client.Context;
import com.dhf.venus.client.Request;

/**
 * @author kim 2015年5月19日
 */
public class RequestProcessor implements ApplicationListener<ContextRefreshedEvent> {

	private final Context context;

	private final Request request;

	public RequestProcessor(Context context, Request request) {
		super();
		this.context = context;
		this.request = request;
	}

	public void onApplicationEvent(ContextRefreshedEvent event) {
		this.request.request(this.context.writers());
	}
}
