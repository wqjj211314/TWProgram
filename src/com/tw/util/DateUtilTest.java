package com.tw.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.tw.entity.Animal;
import com.tw.entity.AnimalsSpot;

public class DateUtilTest {

	@Test
	public void testIsValidDate() {
		Map<String,Animal> a=new HashMap<String,Animal>();
		Animal animal=new Animal("cat1",10,9,1);
		a.put("cat1", animal);
		AnimalsSpot preAnimalsSpot2=new AnimalsSpot("e4e87cb2-8e9a-4749-abb6-26c59344dfee", "2016/09/02 22:30:46", a);
		boolean f1=DateUtil.isValidDate("2016/09/31 22:30:46", null);//九月没有31日，返回false
		boolean f2=DateUtil.isValidDate("2016/09/02 22:30:45", preAnimalsSpot2);//时间必须晚于preAnimalsSpot2的时间
		boolean f3=DateUtil.isValidDate("2016/09/02 22:30:52", preAnimalsSpot2);//true
		if(!f1&&!f2&&f3){
			System.out.println("测试成功！");
		}
	}

}
