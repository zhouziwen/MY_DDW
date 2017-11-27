package com.example.bbacr.ddw.widget;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by WangYeTong on 2017/5/25.
 * email:wytaper495@qq.com
 */

public class ExecutorFactory {
	private static ExecutorService mService;

	private ExecutorFactory() {
		mService = Executors.newCachedThreadPool();
	}

	//匿名内部类来维护单例
	private static class SingletonHolder {
		private static ExecutorFactory mInstance = new ExecutorFactory();
	}

	public static ExecutorFactory instance() {
		return SingletonHolder.mInstance;
	}

	public ExecutorService getExecutorService() {
		return mService;
	}
}
