package com.woojin.commercial.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
	
	
	public static boolean nioFileMove(String inFileName, String outFileName) {
        Path source = Paths.get(inFileName);
        Path target = Paths.get(outFileName);
        
        if (source == null) {
            throw new IllegalArgumentException("source must be specified");
        }
        if (target == null) {
            throw new IllegalArgumentException("target must be specified");
        }
        
        if (!Files.exists(source, new LinkOption[] {})) {
            throw new IllegalArgumentException("Source file doesn't exist: " + source.toString());
        }
        
        try {
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);    // 파일 이동
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        
        if(Files.exists(target, new LinkOption[]{})){
            // System.out.println("File Moved");
            return true;
        } else {
            System.out.println("File Move Failed");
            return false;
        }
    }  





	
}
