package com.mind.entity;


public class MyData {
    public MyData() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MyData [name=" + name + ", age=" + age + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public MyData(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	private String name;
    private int age;
}