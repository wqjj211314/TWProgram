package com.tw.entity;

/**
 * 动物对象
 * 包含动物id,坐标，序号
 * @author WangQiang
 *
 */
public class Animal {
	private String id;//动物名称
	private int x;//x坐标
	private int y;//y坐标
	private int num;//序号
	public Animal() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	public Animal(String id, int x, int y, int num) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.num = num;
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
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}
	

}
