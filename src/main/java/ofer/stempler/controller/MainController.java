package ofer.stempler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ofer.stempler.model.Request;
import ofer.stempler.model.RequestStopWatch;
import ofer.stempler.service.RequestService;

@Controller
public class MainController {

	@Autowired
	RequestService service;

	@RequestMapping(value = "/request/")
	public @ResponseBody Request start(@RequestBody Request request) throws InterruptedException {
		
		
		
		RequestStopWatch watch = new RequestStopWatch();
		watch.start();
//		Request.THREAD_LOCAL.set(request);
		request.setTimer(watch);
		
		//Do your services
		request = service.handleRequest(request);		
		 watch.stop();
//		 Request.THREAD_LOCAL.remove();
		return request;
		
	}
}
