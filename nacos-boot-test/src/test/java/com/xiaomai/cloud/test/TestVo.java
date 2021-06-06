package com.xiaomai.cloud.test;

import java.io.Serializable;
import java.util.Date;

public class TestVo implements Serializable {
	private static final long serialVersionUID = 6146321012406994036L;
	private String testId;
	private String testCode;
	private String testName;
	private Integer age;
	private int telphone;
	private Double height;
	private double weight;
	private Boolean sex;
	private boolean isWorkS;
	private Date birth;

	public TestVo() {
	}

	public TestVo(String testId, String testCode, String testName, Integer age, int telphone, Double height, double weight, Boolean sex, boolean isWorkS, Date birth) {
		this.testId = testId;
		this.testCode = testCode;
		this.testName = testName;
		this.age = age;
		this.telphone = telphone;
		this.height = height;
		this.weight = weight;
		this.sex = sex;
		this.isWorkS = isWorkS;
		this.birth = birth;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getTestCode() {
		return testCode;
	}

	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public int getTelphone() {
		return telphone;
	}

	public void setTelphone(int telphone) {
		this.telphone = telphone;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public boolean isWorkS() {
		return isWorkS;
	}

	public void setWorkS(boolean isWorkS) {
		this.isWorkS = isWorkS;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
}
