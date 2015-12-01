package com.razor.memories.wrappers;

public class LogWrapper {
	
	public static void i(String method, String format, Object ... objects){
		String i = String.format(format, objects);
		i(method,i);
	}
	public static void w(String method, String format, Object ... objects){
		String i = String.format(format, objects);
		w(method,i);
	}
	public static void e(String method, String format, Object ... objects){
		String i = String.format(format, objects);
		e(method,i);
	}
	
	public static void i(String method, String i){
		logToSystem("Information",method,i);
		//Log.info(i);
	}
	public static void w(String method, String i){
		logToSystem("Warning",method,i);
		//Log.warn(i);
	}
	public static void e(String method, String i){
		logToSystem("Error",method,i);
		//Log.warn(i);
	}	
	
	private static void logToSystem(String type, String method, String i){
		String ip = String.format("%s: %s: %s: %s", type, method, Thread.currentThread().getName(),i);
		System.out.println(ip);		
	}	
}

