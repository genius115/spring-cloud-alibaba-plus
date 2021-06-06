package com.xiaomai.cloud.test;

public class TestDemo2021 {
	
	public static final TestVo testVo = new TestVo();
	
	public static void main(String[] args) {
		for(int i=0;i<20;i++){
			Thread t = new Thread(new MyTask(testVo,i)); 
			t.start();
		}
	}
}

class MyTask implements Runnable{
	private TestVo testVo;
	private int i;
	public MyTask() {
	}
	public MyTask(TestVo testVo, int i) {
		this.testVo = testVo;
		this.i = i;
	}
	public TestVo getTestVo() {
		return testVo;
	}
	public void setTestVo(TestVo testVo) {
		this.testVo = testVo;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}

	@Override
	public void run() {
		testVo.setTestCode("变量值:"+i);
		try {
			Thread.sleep(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getId()+"-"+testVo.getTestCode());	
	}	
}
