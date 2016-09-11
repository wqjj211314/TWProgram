[Thoughtworks2016校园招聘作业](https://github.com/wqjj211314/TWProgram)
===

背景介绍
--
动物学家研究动物群体行为的一种方式是将信号发射器安装到动物的身上，然后追踪动物在一定区域内的活动路线。科学家不但希望了解动物的活动路线，而且希望了解任意一个指定的时间点上所有动物的分布。
题目简介
--
假设现在有多个动物在指定的区域内活动。为了节省存储空间，科学家并没有选择存储每一个时刻的动物分布快照，而是存储当前时刻与上一个时刻的变化。假设每一个动物的位置有x,y两个整数坐标确定，则我们规定一下的存储格式：

 - 第一行是一个代表当前时刻的全局唯一的Id(非空字符串，不包含空格，可假设它一定是全局唯一的)
 - 第二行是当前的时刻。格式为YYYY/mm/dd hh:MM:ss（如2016/12/01 23:02:05）
 - 从第三行开始是各种动物的坐标变化，一个动物占用一行。这种变化有两种形式：
	 - 第一种形式:{动物的Id}{x坐标}{y坐标}。这种形式说明这种动物第一次出现在这个区域；
	 - 第二种形式：{动物的Id}{上一时刻的x坐标}{上一时刻的y坐标}{x坐标变化量}{y坐标变化量} 。这种形式说明该公务在之前已经位于这个区域里了，其上一个时刻的x坐标和y坐标用于进行校验。而x,y坐标的变化为整数，整数表示增加，负数表示减少。
 - 所有的数据全部存储在一个字符串historyData中。

项目
--

 - 运行环境
 -  总体概览
 - 项目测试
	 - main方法测试
	 - 单元测试
	  

运行环境
--

> Windows7+Eclipse+jdk8+JUnit4

总体概览
--

 - TWProgram
	 -  src
		 - com.tw.entity
			  -  Animal.java
			  - AnimalsSpot.java
		 - com.tw.main
			 - Main.java 
		 - com.tw.util
			 - AnimalsSpotUtil.java
			 - AnimalsSpotUtilTest.java
			 - AnimalUtil.java
			 - AnimalUtilTest.java
			 - DataFormatUtil.java
			 - DataFormatUtilTest.java
			 - DateUtil.java
			 - DateUtilTest.java
			 - InvalidFormatWarning.java 
		 





| java类        | 类方法/类属性         | 功能  |
| ------------- |:----------------------:|     -----:|
|  Animal.java  |String id |动物id|
|     		| int x|  动物x坐标|
|		| int y     |    动物y坐标 |
|		| int num     |    动物序号 |
| AnimalsSpot.java  |String id|时刻的全局id|
|     		| String date| 时刻日期|
| 		|Map animals |    动物Animal集合 |



- AnimalsSpotUtil.java（输入数据段操作类）
- AnimalsSpotUtilTest.java（输入数据段操作测试类）
- AnimalUtil.java（动物Animal操作类）  
- AnimalUtilTest.java（动物Animal操作测试类） 
-  DataFormatUtil.java（输入格式验证操作类）  
- DataFormatUtilTest.java（输入格式验证操作测试类）
- DateUtil.java （日期有效性正确性操作类） 
- DateUtilTest.java（日期有效性正确性操作测试类） 
- InvalidFormatWarning.java （用于警告）

**项目测试**
==
main方法测试
--
针对范例输入证明项目正确性
	运行com.tw.main package下Main.java进行验证即可
	

```
package com.tw.main;

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
		//String historyData="e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2016/09/02 22:30:46\ncat1 10 9\n\n351055db-33e6-4f9b-bfe1-16f1ac446ac1\n2016/09/02 22:30:52\ncat1 10 9 2 -1\ncat2 2 3\n\ndcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n2016/09/02 22:31:02\ncat1 12 8 3 4\n\ndcfa0c7a-5855-4ed2-bc8c-4accae8bd234\n2016/09/02 22:32:02\ncat1 15 12 3 4\ncat3 10 2";
		//String id="dcfa0c7a-5855-4ed2-bc8c-4accae8bd234";
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
		Map<String,Animal> animals=animalsSpotMap.get(id).getAnimals();
		Iterator it=animals.entrySet().iterator();
		StringBuffer sb=new StringBuffer();
		//Animal[] animalsArray=new Animal[animals.];
		Map<Integer,Animal> map=new HashMap<Integer,Animal>();
		while(it.hasNext()){
			Map.Entry<String,Animal> entry=(Entry<String, Animal>) it.next();
			Animal value=entry.getValue();
			int index=value.getNum();
			map.put(index, value);
			
		}
		Iterator mapit=map.entrySet().iterator();
		while(mapit.hasNext()){
			Map.Entry<Integer,Animal> entry=(Entry<Integer, Animal>) mapit.next();
			Animal value=entry.getValue();
			sb.append(value.getId()+" "+value.getX()+" "+value.getY()+"\n");
		}
		return sb.toString();
	}

	

}

```



运行结果：

 cat1 15 12
cat2 2 3

**说明**  

> 输入字符串**historyData**必须使用 **\n** 来表示换行，每个数据段之间需要再换行 **\n**

例如：

e4e87cb2-8e9a-4749-abb6-26c59344dfee
2016/09/02 22:30:46
cat1 10 9

351055db-33e6-4f9b-bfe1-16f1ac446ac1
2016/09/02 22:30:52
cat1 10 9 2 -1
cat2 2 3
转换为字符串：

    e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2016/09/02 22:30:46\ncat1 10 9\n\n351055db-33e6-4f9b-bfe1-16f1ac446ac1\n2016/09/02 22:30:52\ncat1 10 9 2 -1\ncat2 2 3
利用\n串联

单元测试
--

 - **主功能测试**

```
package com.tw.main;

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
			map.put(index, value);
			
		}
		Iterator mapit=map.entrySet().iterator();
		while(mapit.hasNext()){
			Map.Entry<Integer,Animal> entry=(Entry<Integer, Animal>) mapit.next();
			Animal value=entry.getValue();
			sb.append(value.getId()+" "+value.getX()+" "+value.getY()+"\n");
		}
		return sb.toString();
	}

	

}

```

```
package com.tw.main;

import org.junit.Test;

public class MainTest {

	@Test
	public void testGetSnapShot() {
		String historyData1="e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2016/09/02 22:30:46\ncat1 10 9\n\n351055db-33e6-4f9b-bfe1-16f1ac446ac1\n2016/09/02 22:30:52\ncat1 10 9 2 -1\ncat2 2 3\n\ndcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n2016/09/02 22:31:02\ncat1 12 8 3 4";
		String id1="dcfa0c7a-5855-4ed2-bc8c-4accae8bd155";
		System.out.println(Main.getSnapShot(historyData1,id1));
		String historyData2="e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2016/09/02 22:30:46\ncat1 10 9\n\n351055db-33e6-4f9b-bfe1-16f1ac446ac1\n2016/09/02 22:30:52\ncat1 10 9 2 -1\ncat2 2 3\n\ndcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n2016/09/02 22:31:02\ncat1 12 8 3 4\n\ndcfa0c7a-5855-4ed2-bc8c-4accae8bd234\n2016/09/02 22:32:02\ncat1 15 12 3 4\ncat3 10 2";
		String id2="dcfa0c7a-5855-4ed2-bc8c-4accae8bd234";
		System.out.println(Main.getSnapShot(historyData2, id2));
		String historyData3="e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2016/09/02 22:30:46\ncat1 10 9\n\n351055db-33e6-4f9b-bfe1-16f1ac446ac1\n2016/09/02 22:30:52\ncat1 10 9 2 -1\ncat2 2 3\n\ndcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n2016/09/02 22:31:02\ncat1 12 8 3 4\n\ndcfa0c7a-5855-4ed2-bc8c-4accae8bd234\n2016/09/02 22:32:02\ncat1 15 12 3 4\ncat3 10 2";
		String id3="dcfa0c7a-5855-4ed2-bc8c-4accae8bd";//没有这个id
		System.out.println("###"+Main.getSnapShot(historyData3, id3)+"###");
		String historyData4="sehtoifgoijdsfvowijf";//Invalid format.
		String id4="dcfa0c7a-5855-4ed2-bc8c-4accae8bd234";
		System.out.println(Main.getSnapShot(historyData4, id4));
		
	}

}

```


 - **输入数据格式验证（测试）**


```
package com.tw.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataFormatUtil {
	//对输入数据格式进行判断
	public static boolean judgeStyle(String historyData){
		Pattern pattern = Pattern.compile("^(([A-Za-z0-9]-*)+\\n([0-9]{4}/[0-9]{2}/[0-9]{2}(\\s)[0-9]{2}:[0-9]{2}:[0-9]{2}(\\n))((\\S)+((\\s)(-?\\d+)(\\s)(-?\\d+)){1,2}(\\n){0,1})*(\\n){0,1})+$");
		Matcher matcher = pattern.matcher(historyData);
		boolean b= matcher.matches();
		return b;
	}
}
```
测试：

```
package com.tw.util;

import org.junit.Test;

public class DataFormatUtilTest {

	@Test
	public void testJudgeStyle() {
		//true
		String historyData1="e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2016/09/02 22:30:46\ncat1 10 9\n\n351055db-33e6-4f9b-bfe1-16f1ac446ac1\n2016/09/02 22:30:52\ncat1 10 9 2 -1\ncat2 2 3\n\ndcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n2016/09/02 22:31:02\ncat1 12 8 3 4";
		//2016/09/0222:31:02,格式不正确
		String historyData2="e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2016/09/02 22:30:46\ncat1 10 9\n\n351055db-33e6-4f9b-bfe1-16f1ac446ac1\n2016/09/02 22:30:52\ncat1 10 9 2 -1\ncat2 2 3\n\ndcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n2016/09/0222:31:02\ncat1 12 8 3 4";
		//cat1 12 8 3格式不正确
		String historyData3="e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2016/09/02 22:30:46\ncat1 10 9\n\n351055db-33e6-4f9b-bfe1-16f1ac446ac1\n2016/09/02 22:30:52\ncat1 10 9 2 -1\ncat2 2 3\n\ndcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n2016/09/02 22:31:02\ncat1 12 8 3";
		boolean f1=DataFormatUtil.judgeStyle(historyData1);//true
		boolean f2=DataFormatUtil.judgeStyle(historyData2);//false
		boolean f3=DataFormatUtil.judgeStyle(historyData3);//false
		if(f1&&!f2&&!f3){
			System.out.println("测试成功！");
		}else{
			System.out.println("测试失败！");
		}
	}
}

```

 

 - **日期时间的有效性正确性测试**




```
package com.tw.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tw.entity.AnimalsSpot;

public class DateUtil {
	/**
	 * 验证date是否是一个有效正确的时间字符串
	 * 1.date必须能转换成一个有效的Date
	 * 2.date对应的时间必须晚于preAnimalsSpot(前一数据段)所对应的时间
	 * @param date String类型的日期Date数据
	 * @param preAnimalsSpot 前一个数据段对象
	 * @return
	 */
	public static boolean isValidDate(String date,AnimalsSpot preAnimalsSpot){
	    boolean convertSuccess=true;
	    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    Date date1=null;
	    Date date2=null;
	    try {
	        format.setLenient(false);//验证有效性
	        date1=format.parse(date);
	    } catch (ParseException e) {
	        convertSuccess=false;
	    } 
	    if(preAnimalsSpot!=null&&convertSuccess){
	    	try {
				date2=format.parse(preAnimalsSpot.getDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
	    	if(date1.getTime()-date2.getTime()<=0){//验证正确性
	    		convertSuccess=false;
	    	}
	    }
	   
	    return convertSuccess;
	}

}

```

 
 
```
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

```
 

 - **通过数据段构建对象**

```
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

```


```
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

```

 - **构建Animal**

```
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

```



```
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

```



