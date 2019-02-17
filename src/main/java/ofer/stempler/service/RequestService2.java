package ofer.stempler.service;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import ofer.stempler.aspect.MethodTiming;
import ofer.stempler.model.Request;

@Service
public class RequestService2 {

	@MethodTiming
	public Request handleRequest(Request request) throws InterruptedException {
		
		for (int i = 0; i < 100; i++) {
			Thread.sleep(10);
			
		}
		return request;
	}
	
	
}
