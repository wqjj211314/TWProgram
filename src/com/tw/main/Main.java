package com.tw.main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.tw.entity.Animal;
import com.tw.entity.AnimalsSpot;
import com.tw.util.AnimalsSpotUtil;
import com.tw.util.DataFormatUtil;
import com.tw.util.InvalidFormatWarning;

public class Main {
	
	/**
	 * main主方法，测试
	 * 测试数据
	 * historyData="e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2016/09/02 22:30:46\ncat1 10 9\n\n351055db-33e6-4f9b-bfe1-16f1ac446ac1\n2016/09/02 22:30:52\ncat1 10 9 2 -1\ncat2 2 3\n\ndcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n2016/09/02 22:31:02\ncat1 12 8 3 4";
	 * 对应于(格式)：
	 * e4e87cb2-8e9a-4749-abb6-26c59344dfee
	 * 2016/09/02 22:30:46
	 * cat1 10 9
	 * 
	 * 351055db-33e6-4f9b-bfe1-16f1ac446ac1
	 * 2016/09/02 22:30:52
	 * cat1 10 9 2 -1
	 * cat2 2 3
	 * 
	 * dcfa0c7a-5855-4ed2-bc8c-4accae8bd155
	 * 2016/09/02 22:31:02
	 * cat1 12 8 3 4
	 * 
	 * id="dcfa0c7a-5855-4ed2-bc8c-4accae8bd155";
	 * @param args
	 */
	public static void main(String[] args) {
		
		String historyData="e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2016/09/02 22:30:46\ncat1 10 9\n\n351055db-33e6-4f9b-bfe1-16f1ac446ac1\n2016/09/02 22:30:52\ncat1 10 9 2 -1\ncat2 2 3\n\ndcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n2016/09/02 22:31:02\ncat1 12 8 3 4";
		String id="dcfa0c7a-5855-4ed2-bc8c-4accae8bd155";
		System.out.println(getSnapShot(historyData,id));
	}
	/**
	 * 返回全局id对应的Animal集合字符串
	 * @param historyData 输入数据
	 * @param id 全局id
	 * @return 全局id对应的Animal集合字符串
	 */
	public static String getSnapShot(String historyData,String id){
		//首先对输入参数,输入数据格式进行初步验证
		if(historyData.equals("")||historyData==null||id.equals("")||id==null)
			InvalidFormatWarning.invalidExit();
		if(!DataFormatUtil.judgeStyle(historyData)) InvalidFormatWarning.invalidExit();
		
		
		/**
		 * 首先对整个输入数据进行分割，分成每个数据段
		 * e4e87cb2-8e9a-4749-abb6-26c59344dfee
		 * 2016/09/02 22:30:46
		 * cat1 10 9
		 */
		String[] aspot=historyData.split("\n\n");
		//每个数据段，使用map集合存储
		Map<String,AnimalsSpot> animalsSpotMap=new HashMap<String,AnimalsSpot>();
		//前一数据段对象
		AnimalsSpot preAnimalsSpot=null;
		
		for (int i = 0; i < aspot.length; i++) {
			//对每个段落构建对象AnimalsSpot,包含全局id，日期，Animal集合
			AnimalsSpot as=AnimalsSpotUtil.getStrToObjectAnimalsSpot(aspot[i],preAnimalsSpot);
			//将段落内的Animal集合进行聚合一下
			if(preAnimalsSpot!=null){
				preAnimalsSpot.getAnimals().putAll(as.getAnimals());
				as.setAnimals(preAnimalsSpot.getAnimals());
			}	
			
			preAnimalsSpot=as;
			animalsSpotMap.put(as.getId(), as);
		}
		return getAnimalsById(id, animalsSpotMap);
		
	}
	/**
	 * 根据集合查找id的对象
	 * @param id 查询的全局id
	 * @param animalsSpotMap 数据段集合
	 * @return id对应的Animal集合组成的输出字符串
	 */
	public static String getAnimalsById(String id,Map<String,AnimalsSpot> animalsSpotMap){
		Map<String,Animal> animals=null;
		if(animalsSpotMap.containsKey(id))
			animals=animalsSpotMap.get(id).getAnimals();
		else
			return "";
		Iterator it=animals.entrySet().iterator();
		StringBuffer sb=new StringBuffer();
		//Animal[] animalsArray=new Animal[animals.];
		Map<Integer,Animal> map=new HashMap<Integer,Animal>();
		while(it.hasNext()){
			Map.Entry<String,Animal> entry=(Entry<String, Animal>) it.next();
			Animal value=entry.getValue();
			int index=value.getNum();
			map.put(index, value);//重新构建集合
			
		}
		//排序，按照升序进行遍历
		Object[] key=map.keySet().toArray();  
        Arrays.sort(key);  
        for(int i =0;i<key.length;i++)   {  
                Animal value=map.get(key[i]);
    			sb.append(value.getId()+" "+value.getX()+" "+value.getY()+"\n");
        }   
		
		return sb.toString();
	}

}
