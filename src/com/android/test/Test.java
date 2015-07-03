package com.android.test;

import com.android.messagequeue.Handler;
import com.android.messagequeue.Looper;
import com.android.messagequeue.Message;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		HandlerThread threaad = new HandlerThread();
		threaad.start();
		while (true) {
			Thread.sleep(1000);
			threaad.send();
		}
	}
}

class HandlerThread extends Thread {
	MyHandler handler;
int count;
	@Override
	public void run() {
		Looper.prepare();
		handler = new MyHandler(Looper.myLooper());
		Looper.loop();
		super.run();
	}

	public Looper get() {
		return Looper.myLooper();
	}

	public void send() {
		handler.sendEmptyMessage(count++);
	}
}

class MyHandler extends Handler {
	public MyHandler(Looper looper) {
		super(looper);
	}

	@Override
	public void handleMessage(Message msg) {
		System.out.println("threaad");
		super.handleMessage(msg);
	}
}
