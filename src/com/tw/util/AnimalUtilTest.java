package com.tw.util;



import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.tw.entity.Animal;
import com.tw.entity.AnimalsSpot;

public class AnimalUtilTest {


	@Test
	public void testGetStrToObjectAnimal() {
		Map<String,Animal> animals=new HashMap<String,Animal>();
		Animal animal=new Animal("cat1",10,9,1);
		animals.put("cat1", animal);
		AnimalsSpot preAnimalsSpot=new AnimalsSpot("351055db-33e6-4f9b-bfe1-16f1ac446ac1", "2016/09/02 22:30:52", animals);
		
		Animal animalObject1=AnimalUtil.getStrToObjectAnimal("cat1 10 9 2 -1", preAnimalsSpot);//返回Animal对象
		Animal animalObject2=AnimalUtil.getStrToObjectAnimal("cat1 10 9", null);//返回Animal对象
		Animal animalObject3=AnimalUtil.getStrToObjectAnimal("cat1 12 8 3 4", null);//null
		if(animalObject1.getX()==12&&animalObject1.getY()==8&&animalObject2.getX()==10&&animalObject2.getY()==9&&animalObject3==null){
			System.out.println("测试成功！");
		}
	}

	@Test
	public void testJudgeAnimalPosition() {
		
		Map<String,Animal> animals=new HashMap<String,Animal>();
		Animal animal=new Animal("cat1",10,9,1);
		animals.put("cat1", animal);
		AnimalsSpot preAnimalsSpot=new AnimalsSpot("351055db-33e6-4f9b-bfe1-16f1ac446ac1", "2016/09/02 22:30:52", animals);
		
		boolean f1=AnimalUtil.judgeAnimalPosition("cat1", 10, 9, preAnimalsSpot);//true
		boolean f2=AnimalUtil.judgeAnimalPosition("cat1", 9, 8, preAnimalsSpot);//false
		if(f1&&!f2){
			System.out.println("测试成功！");
		}
	}

	@Test
	public void testJudgeAnimalApperance() {
		
		//构建一个preAnimalsSpot，cat1 10 9
		Map<String,Animal> animals=new HashMap<String,Animal>();
		Animal animal=new Animal("cat1",10,9,1);
		animals.put("cat1", animal);
		AnimalsSpot preAnimalsSpot=new AnimalsSpot("351055db-33e6-4f9b-bfe1-16f1ac446ac1", "2016/09/02 22:30:52", animals);
		
		boolean f1=AnimalUtil.judgeAnimalApperance(5,"cat1",preAnimalsSpot);//true,cat1之前必须出现
		boolean f2=AnimalUtil.judgeAnimalApperance(5,"cat2",preAnimalsSpot);//false，cat2之前必须出现
		boolean f3=AnimalUtil.judgeAnimalApperance(3, "cat1", preAnimalsSpot);//false,cat1之前不能出现
		boolean f4=AnimalUtil.judgeAnimalApperance(3, "cat2", preAnimalsSpot);//true，cat2之前不能出现
		boolean f5=AnimalUtil.judgeAnimalApperance(5,"cat2",null);//false，cat2第一次出现
		boolean f6=AnimalUtil.judgeAnimalApperance(3, "cat1", null);//true,cat1第一次出现
		if(f1&&!f2&&!f3&&f4&&!f5&&f6){
			System.out.println("测试成功！");
		}
	}

	@Test
	public void testGetNumOfAnimal() {
		String id="cat2";
		int num=AnimalUtil.getNumOfAnimal(id);
		System.out.println(num);
	}

}
