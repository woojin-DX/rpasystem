/*
 * Copyright (c) 2019.
 *   ------------------------------------------------------------------------------
 *   @Project       : RPA Web Project
 *   @Source        : FTPUtil
 *   @Description   :
 *   @Author        : GACHINOEL
 *   @Version       : v1.0
 *   Copyright(c) 2019 WOOJIN All rights reserved
 *   ------------------------------------------------------------------------------
 *                    변         경         사         항
 *   ------------------------------------------------------------------------------
 *      DATE           AUTHOR                       DESCRIPTION
 *   ---------------  ----------    ------------------------------------------------
 *   2019.10.28       gachinoel     신규생성
 *   ------------------------------------------------------------------------------
 */

package com.woojin.commercial.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;

import java.io.*;
import java.net.SocketException;

public class FTPUtil {
    private FTPClient client = null;

    /**
     * 서버와 연결에 필요한 값들을 가져와 초기화 시킴
     *
     * @parameter host
     *            서버 주소
     * @parameter userName
     *            접속에 사용될 아이디
     * @parameter password
     *            비밀번호
     * @parameter port
     *            포트번호
     */
    public void init(String host, String userName, String password, int port) {
        client = new FTPClient();
        //client.setControlEncoding("euc-kr"); // 한글 encoding....
        client.setControlEncoding("UTF-8"); // 한글 encoding....

        FTPClientConfig config = new FTPClientConfig();
        client.configure(config);
        try {
            client.connect(host, port);
            client.login(userName, password);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 하나의 파일을 업로드 한다.
     *
     * @parameter dir
     *            저장시킬 주소(서버)
     * @parameter file
     *            저장할 파일
     */
    public void upload(String dir, File file) {

        InputStream in = null;

        try {
            in = new FileInputStream(file);
            client.storeFile(dir + file.getName(), in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 하나의 파일을 업로드 한다.
     *
     * @parameter dir
     *            저장시킬 주소(서버)
     * @parameter file
     *            저장할 파일
     */
    public void uploadStream(String sFullFilePath, String sFileNm, InputStream in) {

        try {
            client.enterLocalPassiveMode(); // Passive Mode 접속일때
            client.setFileType(FTP.BINARY_FILE_TYPE); // 업로드 파일 타입 셋팅

            String[] sFullFilePathArray = sFullFilePath.split("/");
            String sFullFilePathAdd = "";
            String sFullFilePathpRE = "";

            int iFullFilePathArray = sFullFilePathArray.length;

            for(int i=0;i < iFullFilePathArray;i++){
                sFullFilePathAdd = sFullFilePathArray[i] + "/";

                if(!client.changeWorkingDirectory(sFullFilePathAdd)){ // /data/upload/file/20150208/noname/ 이 없으면
                    client.changeWorkingDirectory(sFullFilePathpRE);
                    client.makeDirectory(sFullFilePathArray[i]); // mkdir 20140106

                    sFullFilePathpRE = sFullFilePathAdd;
                }else{

                }
            }
            /*
            if(!client.changeWorkingDirectory(sFullFilePath)){ // /data/upload/20140106이 없으면
                boolean a = client.makeDirectory(sFullFilePath); // mkdir 20140106
                System.out.println("success=====>"+a);
            }
            */
            System.out.println("sFullFilePath=====>"+sFullFilePath);
            client.storeFile(sFullFilePath + sFileNm, in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 하나의 파일을 다운로드 한다.
     *
     * @parameter dir
     *            저장할 경로(서버)
     * @parameter downloadFileName
     *            다운로드할 파일
     * @parameter path
     *            저장될 공간
     */
    public void download(String dir, String downloadFileName, String path) {

        FileOutputStream out = null;
        InputStream in = null;
        dir += downloadFileName;
        try {
            in = client.retrieveFileStream(dir);
            out = new FileOutputStream(new File(path));
            int i;
            while ((i = in.read()) != -1) {
                out.write(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    };

    /**
     * 하나의 파일을 다운로드 한다.
     *
     * @parameter dir
     *            저장할 경로(서버)
     * @parameter downloadFileName
     *            다운로드할 파일
     * @parameter path
     *            저장될 공간
     */
    public InputStream downloadStream(String dir, String downloadFileName) {

        InputStream in = null;
        dir += downloadFileName;
        try {

            client.enterLocalPassiveMode(); // Passive Mode 접속일때
            client.setFileType(FTP.BINARY_FILE_TYPE); // 업로드 파일 타입 셋팅

            in = client.retrieveFileStream(dir);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

        return in;
    };

    /**
     * 서버와의 연결을 끊는다.
     */
    public void disconnection() {
        try {
            if (client != null && client.isConnected()) {
                client.logout();
                client.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 하나의 파일을 업로드 한다.
     *
     * @parameter dir
     *            저장시킬 주소(서버)
     * @parameter file
     *            저장할 파일
     */
    public void makeDirecory(String sFullFilePath) {

        try {
            client.enterLocalPassiveMode(); // Passive Mode 접속일때
            client.setFileType(FTP.BINARY_FILE_TYPE); // 업로드 파일 타입 셋팅

            String[] sFullFilePathArray = sFullFilePath.split("/");
            String sFullFilePathAdd = "";
            String sFullFilePathpRE = "";

            int iFullFilePathArray = sFullFilePathArray.length;

            for(int i=0;i < iFullFilePathArray;i++){
                sFullFilePathAdd = sFullFilePathArray[i] + "/";

                if(!client.changeWorkingDirectory(sFullFilePathAdd)){ // /data/upload/file/20150208/noname/ 이 없으면
                    client.changeWorkingDirectory(sFullFilePathpRE);
                    client.makeDirectory(sFullFilePathArray[i]); // mkdir 20140106
                    client.sendSiteCommand("chmod " + "755 -R " + sFullFilePathArray[i]);

                    sFullFilePathpRE = sFullFilePathAdd;
                }else{

                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
