package com.tw.util;



import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.tw.entity.Animal;
import com.tw.entity.AnimalsSpot;

public class AnimalsSpotUtilTest {

	@Test
	public void testGetStrToObjectAnimalsSpot() {
		//第一组测试数据
		String spotData1="e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2016/09/02 22:30:46\ncat1 10 9";
		AnimalsSpot as1=AnimalsSpotUtil.getStrToObjectAnimalsSpot(spotData1, null);
		
		StringBuffer sb=new StringBuffer();
		sb.append(as1.getId()+"\n"+as1.getDate());
		Map<String,Animal> animals1=as1.getAnimals();
		Iterator it1=animals1.entrySet().iterator();
		while(it1.hasNext()){
			@SuppressWarnings("unchecked")
			Map.Entry<String,Animal> entry=(Entry<String, Animal>) it1.next();
			Animal value=entry.getValue();
			sb.append("\n"+value.getId()+" "+value.getX()+" "+value.getY());
		}
		System.out.println(spotData1);
		System.out.println(sb.toString());
		
		
		//第二组测试数据
		String spotData2="351055db-33e6-4f9b-bfe1-16f1ac446ac1\n2016/09/02 22:30:52\ncat1 10 9 2 -1\ncat2 2 3";
		Map<String,Animal> a=new HashMap<String,Animal>();
		Animal animal=new Animal("cat1",10,9,1);
		a.put("cat1", animal);
		AnimalsSpot preAnimalsSpot2=new AnimalsSpot("e4e87cb2-8e9a-4749-abb6-26c59344dfee", "2016/09/02 22:30:46", a);
		
		
		AnimalsSpot as2=AnimalsSpotUtil.getStrToObjectAnimalsSpot(spotData2, preAnimalsSpot2);
		
		StringBuffer sb2=new StringBuffer();
		sb2.append(as2.getId()+"\n"+as2.getDate());
		
		Map<String,Animal> animals2=as2.getAnimals();
		Iterator it2=animals2.entrySet().iterator();
		while(it2.hasNext()){
			@SuppressWarnings("unchecked")
			Map.Entry<String,Animal> entry=(Entry<String, Animal>) it2.next();
			Animal value=entry.getValue();
			sb2.append("\n"+value.getId()+" "+value.getX()+" "+value.getY());
		}
		System.out.println();
		System.out.println(spotData2);
		System.out.println(sb2.toString());
	}

}
