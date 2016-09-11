package com.tw.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tw.entity.Animal;
import com.tw.entity.AnimalsSpot;

/**
 * 有关Animal操作的工具类
 * getStrToObjectAnimal：通过字符串构建Animal对象
 * judgeAnimal：通过Animal的id判断需要构建的对象初始坐标是否有误
 * getNumOfAnimal：通过Animal的id利用正则表达式获取一个编号以便输出排序
 * @author WangQiang
 *
 */
public class AnimalUtil {
	
	/**
	 * 通过字符串构建对象
	 * @param animalStr 数据字符串cat1 10 9  cat1 12 8 3 4
	 * @param preAnimalsSpot
	 * @return 构建的对象
	 */
	public static Animal getStrToObjectAnimal(String animalStr,AnimalsSpot preAnimalsSpot){
			
			String[] animalStrArray=animalStr.split(" ");
			Animal animalObject=new Animal();
			
			String id=animalStrArray[0];
			animalObject.setId(id);
			
			int num=getNumOfAnimal(id);//给Animal指定一个序号
			animalObject.setNum(num);
			
			int length=animalStrArray.length;
			boolean b=judgeAnimalApperance(length,id,preAnimalsSpot);
			if(!b){
				return null;
			}
			if(length==5){
				int preX=Integer.parseInt(animalStrArray[1]);
				int preY=Integer.parseInt(animalStrArray[2]);

				//对Animal坐标进行判断
				boolean f=judgeAnimalPosition(id, preX, preY, preAnimalsSpot);
				
				if(!f){
					return null;
				}
				animalObject.setX(preX+Integer.parseInt(animalStrArray[3]));
				animalObject.setY(preY+Integer.parseInt(animalStrArray[4]));
			}else if(length==3){
				
				animalObject.setX(Integer.parseInt(animalStrArray[1]));
				animalObject.setY(Integer.parseInt(animalStrArray[2]));
			}
			
			return animalObject;
		}
	//对Animal坐标进行判断
	/**
	 * 如果id的Animal原始坐标与preAnimalsSpot不对应，说明数据有误
	 * @param id Aniaml的id
	 * @param preX 原始x坐标
	 * @param preY 原始y坐标
	 * @param preAnimalsSpot 前一个AnimalsSpot对象，存储了对应的Animal集合
	 * @return
	 */
	public static boolean judgeAnimalPosition(String id,int preX,int preY,AnimalsSpot preAnimalsSpot){
		
		if(preAnimalsSpot!=null){
			Map<String,Animal> animals=preAnimalsSpot.getAnimals();
			Animal animal=animals.get(id);
			if(animal==null){
				return false;
			}
			if(animal.getX()==preX&&animal.getY()==preY){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}
	//对Animal的出现进行判断
	/**
	 * 在preAnimalsSpot中判断id的Animal的出现
	 * 1.preAnimalsSpot==null
	 * 		表明Animal都是第一次出现，length==3
	 * 2.preAnimalsSpot!=null
	 * 		如果Animal是第一次出现，length==3，preAnimalsSpot的Animal集合不包含id的Animal
	 * 		如果Animal不是第一次出现，length==5,preAnimalsSpot的Animal集合一定包含id的Animal
	 * @param length 
	 * @param id Animal的id
	 * @param preAnimalsSpot 前一个AnimalsSpot对象，存储了对应的Animal集合
	 * @return
	 */
	public static boolean judgeAnimalApperance(int length,String id,AnimalsSpot preAnimalsSpot){
		if(preAnimalsSpot!=null){
			if(length==5){
				boolean flag=preAnimalsSpot.getAnimals().containsKey(id);
				if(!flag){
					return false;
				}
			}else if(length==3){
				boolean flag=preAnimalsSpot.getAnimals().containsKey(id);
				if(flag){
					return false;
				}
			}
		}else{
			if(length==5)
				return false;
		}
		
		return true;
	}
	//获取Animal序号
	public static int getNumOfAnimal(String id){
		String regEx="[^0-9]";   
		Pattern p = Pattern.compile(regEx);   
		Matcher m = p.matcher(id);   
		String str=m.replaceAll("").trim();
		int num=Integer.parseInt(str);
		return num; 
	}
}
