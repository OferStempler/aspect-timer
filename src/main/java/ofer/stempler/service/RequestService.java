package ofer.stempler.service;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ofer.stempler.aspect.MethodTiming;
import ofer.stempler.model.Request;

@Service
public class RequestService {

	
	@Autowired
	RequestService2 service2;
	
	@MethodTiming
	public Request handleRequest(Request request) throws InterruptedException {
		
		for (int i = 0; i < 100; i++) {
			Thread.sleep(10);
			
		}
		service2.handleRequest(request);
		return request;
	}
	
	@MethodTiming
	private void count() throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			Thread.sleep(10);
			
		}
	}
}
