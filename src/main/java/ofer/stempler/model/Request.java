package ofer.stempler.model;

import java.util.Map;
import java.util.TreeMap;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Request {

	private String name;

	   /** Capture any method timings that occur during transaction processing. */
    private Map<String, Long> methodTimings = new TreeMap<String, Long>();
    
    private ElapsedTimeTimer timer;
    
//    public static final ThreadLocal<Request> THREAD_LOCAL = new ThreadLocal<>();

	public Request() {
		super();
	}
	
	 public void addMethodTiming(String classAndMethodName, long executionTimeMillis) {
	        Long value = methodTimings.get(classAndMethodName);
	        if (value != null) {
	            executionTimeMillis += value;
	        }

	        methodTimings.put(classAndMethodName, executionTimeMillis);
	    }
}
