package com.pratz.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppExecutor {
	
	private static ExecutorService executorService;
	
	private static final int THREAD_POOL_SIZE = 5;

	public static ExecutorService getExecutorService(){
		if(executorService==null){
			executorService =  Executors.newFixedThreadPool(THREAD_POOL_SIZE);	
		}
		return executorService;
	}

}
