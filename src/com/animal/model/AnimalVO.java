package com.animal.model;

public class AnimalVO {
	
	private Integer id;
	private String name;
	private Integer age;
	private Double weight;
	private byte[] picture;
	private Integer areano;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public Integer getAreano() {
		return areano;
	}
	public void setAreano(Integer areano) {
		this.areano = areano;
	}

}
