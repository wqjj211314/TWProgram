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
