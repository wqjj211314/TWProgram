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
