package com.woojin.commercial.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
	public	static  void fileMove(String filepath,String copypath, String fileName) 
	{
		File folder1 = new File(filepath+ fileName);
		
		File folder2 = new File(copypath + fileName);
		
		copy(folder1, folder2);
		
		delete(folder1);
	}

	public static void copy(File sourceF, File targetF) 
	{
		if (sourceF.isFile())
		{
			FileInputStream fis = null;
			FileOutputStream fos = null;
			try {
				fis = new FileInputStream(sourceF);
				fos = new FileOutputStream(targetF);
				
				byte[] b = new byte[10004096];
				int cnt = 0;
				while ((cnt = fis.read(b)) != -1) 
				{
					fos.write(b, 0, cnt);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fis.close();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

	public	static  void delete(File sourceF) 
	{
		try 
		{
			if (sourceF.delete()) {
				System.out.println("파일삭제 y" + sourceF.toString());
			} else {
				System.out.println("파일삭제 n" +  sourceF.toString());
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
