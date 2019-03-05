package demo;

import java.util.ArrayList;
import java.util.List;

import Thread.RunnableUser;
import get_excel.exel;
import get_excel.userNo;
import jxl.read.biff.BiffException;

public class Run extends Thread{
	
	public static void main(String[] args) throws BiffException, Exception {
		long startTime = System.currentTimeMillis();    //获取开始时间
		//获取补考名单中学号
		exel exel = new exel();
		List<userNo> userNo = exel.get();
		
		List<String> majorNo = new ArrayList<String>() ;
		String MajorNO;
		for(int i=1;i<userNo.size(); ++i) {
			MajorNO = userNo.get(i).getUserno();
			MajorNO = String.valueOf(Long.parseLong(MajorNO)/1000%100000);
			majorNo.add(MajorNO);
		}
		//去除重复的majorNO
		for (int i = 0; i < majorNo.size()-1 ; i++) {
            for (int j = majorNo.size() - 1; j > i; j--) {
                if (majorNo.get(j).equals(majorNo.get(i))) {
                	majorNo.remove(j);
                }
            }
		}
		//2015级线程
		RunnableUser downPhotos1 = new RunnableUser(majorNo, "2015");
		downPhotos1.start();
		//2016
		RunnableUser downPhotos2 = new RunnableUser(majorNo, "2016");
		downPhotos2.start();
		//2017
		RunnableUser downPhotos3 = new RunnableUser(majorNo, "2017");
		downPhotos3.start();
		//2018
		RunnableUser downPhotos4 = new RunnableUser(majorNo, "2018");
		downPhotos4.start();
		
		long endTime = System.currentTimeMillis();    //获取结束时间
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
	}
	
}
