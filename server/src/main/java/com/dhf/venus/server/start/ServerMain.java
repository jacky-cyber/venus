package com.dhf.venus.server.start;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author kim 2015年5月12日
 */
public class ServerMain {

	public static void main(String[] args) throws InterruptedException {
		final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:spring-venus.xml" });
		Runtime.getRuntime().addShutdownHook(new Thread(new Shutdown(context)));
		synchronized (context) {
			context.wait();
		}
		context.destroy();
	}

	private static class Shutdown implements Runnable {

		private final ClassPathXmlApplicationContext context;

		public Shutdown(ClassPathXmlApplicationContext context) {
			super();
			this.context = context;
		}

		@Override
		public void run() {
			synchronized (this.context) {
				this.context.notifyAll();
			}
		}
	}
}
