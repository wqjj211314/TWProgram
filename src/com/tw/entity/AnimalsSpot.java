package com.tw.entity;

import java.util.HashMap;

import java.util.Map;

/**
 * 每个时刻的对象
 * 包含全局id，日期，Animal集合
 * @author WangQiang
 *
 */
public class AnimalsSpot {
	private String id;
	private String date;
	private Map<String,Animal> animals=new HashMap<String,Animal>();
	
	public AnimalsSpot() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AnimalsSpot(String id, String date, Map<String, Animal> animals) {
		super();
		this.id = id;
		this.date = date;
		this.animals = animals;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the animals
	 */
	public Map<String, Animal> getAnimals() {
		return animals;
	}
	/**
	 * @param animals the animals to set
	 */
	public void setAnimals(Map<String, Animal> animals) {
		this.animals = animals;
	}
	

}
