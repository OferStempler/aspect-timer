package ofer.stempler.model;

import org.springframework.util.StopWatch;

public class RequestStopWatch extends StopWatch implements ElapsedTimeTimer{
	
	 private final long myStartTime;

	    public RequestStopWatch() {
	        super();

	        this.myStartTime = System.currentTimeMillis();
	    }

	    @Override
	    public long getElapsedTimeMillis() {
	        return System.currentTimeMillis() - myStartTime;
	    }
}
