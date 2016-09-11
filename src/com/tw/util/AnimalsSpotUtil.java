package com.tw.util;

import java.util.HashMap;
import java.util.Map;

import com.tw.entity.Animal;
import com.tw.entity.AnimalsSpot;
/**
 * 有关AnimalsSpot操作的工具类
 * @author WangQiang
 *
 */
public class AnimalsSpotUtil {
	
	/**
	 * 通过数据段进行构建对象
	 * @param strs 数据段(例:e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2016/09/02 22:30:46\ncat1 10 9)
	 * @param preAnimalsSpot 前一个数据段对象
	 * @return 数据段对象
	 */
	public static AnimalsSpot getStrToObjectAnimalsSpot(String spotData,AnimalsSpot preAnimalsSpot){
		//将段落进行分割
		String[] strs=spotData.split("\n");
		if(strs.length<2)
			InvalidFormatWarning.invalidExit();
		String sid=strs[0];//段落的全局id
		String sdate=strs[1];//日期
		
		//对日期的有效性和正确性进行验证
		boolean convertSuccess=DateUtil.isValidDate(sdate,preAnimalsSpot);
		if(!convertSuccess){
			System.out.println("Conflict found at "+sid);
			System.exit(0);
		}
			
		Map<String,Animal> animals=new HashMap<String,Animal>();
		for (int j = 2; j < strs.length; j++) {
			//构建Animal对象
			Animal animal=AnimalUtil.getStrToObjectAnimal(strs[j],preAnimalsSpot);
			//数据出现错误
			if(animal==null){
				System.out.println("Conflict found at "+sid);
				System.exit(0);
			}
			animals.put(animal.getId(), animal); 
		}
		AnimalsSpot as=new AnimalsSpot(sid, sdate, animals);
		return as;
	}
	

}
