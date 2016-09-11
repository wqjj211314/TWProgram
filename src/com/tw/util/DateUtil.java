package com.tw.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tw.entity.AnimalsSpot;

public class DateUtil {
	/**
	 * 验证date是否是一个有效正确的时间字符串
	 * 1.date必须能转换成一个有效的Date
	 * 2.date对应的时间必须晚于preAnimalsSpot(前一时间)所对应的时间
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
