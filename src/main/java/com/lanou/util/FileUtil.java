package com.lanou.util;

import java.io.File;
import java.io.IOException;

public class FileUtil {
	public static final String PATH=System.getProperty("user.dir") +"/src/com/lanou/day16/resource/";
	public static final String PATH1=System.getProperty("user.dir") +"/src/com/lanou/day17/resource/";
	public static final String PATH2=System.getProperty("user.dir") +"/src/com/lanou/day17/photo/";
	public static final String PATH3=System.getProperty("user.dir");
	public static boolean createFiles(String fileName) {
		//如果确定了方法的返回值类型，并且方法中有分支情况，必须保证每个分支都有对应的返回值，否则会报错
		//要么就在外层即分支的外面统一的返回
		int index1 =fileName.indexOf(".");
		if (index1==-1) {
			//当前传入的是一个文件夹
			File file = new File(fileName);
			if (file.mkdirs()) {
				return true;
				
			}else {
				return false;
			}
			
		}else {
			//是文件的情况
			File file =new File(fileName);
			
			//如果发现文件不存在就创建好对应的文件夹和文件
			if (!file.exists()) {
				//能不能根据当期文件的路径先创建好文件结构然后再在这个文件结构下创建好我们对应的文件
				//但如果直接将我们的全路径传进来连我们的文件都被当做是一个文件夹创建出来了
				//1.文件夹结构不全
				//2.文件夹结构健全只是没有这个文件而已
				//找到我们最后一个/所在的位置根据这个/来截取我们的文件字符串，得到该文件的文件结构
				int index = fileName.lastIndexOf("/");
				//System.out.println(index);
				String dirs =fileName.substring(0, index);
				//System.out.println("文件路径如下:"+dirs);
				//根据该文件结构创建对应的file对象
				File dirFile=new File(dirs);
				//判断对象是否存在
				if (!dirFile.exists()) {
					//如果不存在就表明文件结构不全，通过mkdirs补全
					dirFile.mkdirs();
				}
				//当代码执行到这一行的时候，文件结构肯定是好的了
				//只要在创建出对应的文件就ok了.
				try {
					//结果导向
					if (file.createNewFile()) {
						return true;
					}else {
						return false;
					}
					
				}catch (IOException e) {
					
					e.printStackTrace();
				}
				System.out.println("操作完成");
			}
			return true;
		}
		
		
	}
}
