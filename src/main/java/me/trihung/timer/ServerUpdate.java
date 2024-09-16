package me.trihung.timer;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ServerUpdate implements ServletContextListener{

	private static ScheduledExecutorService scheduler;
	public static OTPTimer otpTimer = new OTPTimer();;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		/*
		 * scheduler = Executors.newSingleThreadScheduledExecutor();
		 * 
		 * scheduler.scheduleAtFixedRate(otpTimer, 0, 1, TimeUnit.SECONDS);
		 */
		System.out.println("event");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
//		// TODO Auto-generated method stub
//		scheduler.shutdown();
	}

}
