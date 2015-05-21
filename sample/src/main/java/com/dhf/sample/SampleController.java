package com.dhf.sample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dhf.venus.client.Refresh;
import com.dhf.venus.client.annotation.SetValue;

/**
 * @author kim 2014年7月12日
 */
@Controller
@RequestMapping(value = "/")
public class SampleController {

	private int code;

	private SampleService service;

	private Refresh refresh;

	public SampleController(SampleService service, Refresh refresh) {
		super();
		this.service = service;
		this.refresh = refresh;
	}

	@SetValue(name = "sample.code")
	public void setCode(int code) {
		this.code = code;
	}

	@RequestMapping(value = "/sample1", method = RequestMethod.GET)
	@ResponseBody
	public Response sample1() {
		return new Response();
	}

	@RequestMapping(value = "/sample2", method = RequestMethod.GET)
	@ResponseBody
	public Response sample2() {
		this.refresh.refresh();
		return new Response();
	}

	private class Response {

		public String getName() {
			return SampleController.this.service.name();
		}

		public int getCode() {
			return SampleController.this.code;
		}
	}
}
