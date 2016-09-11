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
