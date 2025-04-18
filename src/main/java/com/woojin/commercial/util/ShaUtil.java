/*
 * Copyright (c) 2019.
 *   ------------------------------------------------------------------------------
 *   @Project       : RPA Web Project
 *   @Source        : ShaUtil
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class ShaUtil {

    /**
     * 파일암호화에 쓰이는 버퍼 크기 지정
     */
    public static final int kBufferSize = 8092;

    /**
     * 32자리 난수 스트링을 Base64로 인코딩한 키값
     */
    public static String skey = null;

    public static java.security.Key key = null;

    public static final String defaultkey_path = "/homepage/key/"; // 암복호화 key 파일 경로

    public static final String defaultkey = "SecretKey.key"; // 암복호화 key 파일

    //initial vector
    public static byte[] ivBytes = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };

    /**
     * 비밀키 생성메소드
     *
     * @return void
     * @throws Exception
     */
    public static File makekey() throws Exception {
        return makekey(defaultkey);
    }

    public static File makekey(String filename)
            throws Exception {
        File tempfile = new File(defaultkey_path + filename);
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempfile));
        //32자리 난수스트링 발생
        String ranNum = generateSkey(32);
        //난수스트링 키값을 Base64로 인코딩해서 파일에 저장
        skey = Base64.encodeBase64String(ranNum.getBytes("UTF-8"));
        bw.write(skey);
        bw.close();
        return tempfile;
    }

    /**
     * 지정된 비밀키를 가지고 오는 메서드
     *
     * @return Key 비밀키 클래스
     * @exception Exception
     */
    private static java.security.Key getKey() throws Exception {
        if (key != null) {
            return key;
        } else {
            return getKey(defaultkey);
        }
    }

    private static java.security.Key getKey(String filename) throws Exception {
        if (key == null) {

            File fPath  = new File(defaultkey_path);
            if (!fPath.exists()){
                fPath.mkdirs();
            }

            File file = new File(defaultkey_path + filename);
            if (!file.exists()) {
                file = makekey();
            }
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(defaultkey_path + filename));
                String keyValue = "";
                do {
                    keyValue += br.readLine().trim();
                } while(br.readLine() != null);
                skey = keyValue;

                //난수스트링 키값을 Base64로 디코딩
                byte[] skeyBytes = Base64.decodeBase64(skey);
                key = new SecretKeySpec(skeyBytes, "AES");
                br.close();
            } else {
                throw new Exception("암호키객체를 생성할 수 없습니다.");
            }
        }
        return key;
    }

    /**
     * 문자열 대칭 암호화
     *
     * @param ID
     *            비밀키 암호화를 희망하는 문자열
     * @return String 암호화된 ID
     * @exception Exception
     */
    public static String encrypt(String ID) throws Exception {
        if (ID == null || ID.length() == 0)
            return "";
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getKey(), ivSpec);
        String amalgam = ID;

        byte[] inputBytes1 = amalgam.getBytes("UTF8");
        byte[] outputBytes1 = cipher.doFinal(inputBytes1);
        String outputStr1 = Base64.encodeBase64String(outputBytes1);

        return outputStr1;
    }

    /**
     * 문자열 대칭 복호화
     *
     * @param codedID
     *            비밀키 복호화를 희망하는 문자열
     * @return String 복호화된 ID
     * @exception Exception
     */
    public static String decrypt(String codedID) throws Exception {
        if (codedID == null || codedID.length() == 0)
            return "";
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, getKey(), ivSpec);
        byte[] inputBytes1 = Base64.decodeBase64(codedID);
        byte[] outputBytes2 = cipher.doFinal(inputBytes1);

        String strResult = new String(outputBytes2, "UTF8");

        return strResult;
    }

    /**
     * 파일 대칭 암호화
     *
     * @param infile
     *            암호화을 희망하는 파일명
     * @param outfile
     *            암호화된 파일명
     * @exception Exception
     */
    public static void encryptFile(String infile, String outfile)
            throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getKey());

        FileInputStream in = new FileInputStream(infile);
        FileOutputStream fileOut = new FileOutputStream(outfile);

        CipherOutputStream out = new CipherOutputStream(fileOut, cipher);
        byte[] buffer = new byte[kBufferSize];
        int length;
        while ((length = in.read(buffer)) != -1)
            out.write(buffer, 0, length);
        in.close();
        out.close();
    }

    /**
     * 파일 대칭 복호화
     *
     * @param infile
     *            복호화을 희망하는 파일명
     * @param outfile
     *            복호화된 파일명
     * @exception Exception
     */
    public static void decryptFile(String infile, String outfile)
            throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, getKey());

        FileInputStream in = new FileInputStream(infile);
        FileOutputStream fileOut = new FileOutputStream(outfile);

        CipherOutputStream out = new CipherOutputStream(fileOut, cipher);
        byte[] buffer = new byte[kBufferSize];
        int length;
        while ((length = in.read(buffer)) != -1)
            out.write(buffer, 0, length);
        in.close();
        out.close();
    }

    //대칭키 만들기(32byte = 256bits)
    public static String generateSkey(int figures) throws Exception{
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        sb.setLength(0);

        for(int i=0; i<figures; i++){
            sb.append(r.nextInt(9));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        try{
            String sId = "1sdfsefsfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdf11211111111111111111111111111222222222222222222222222224444444444444444455555555555555555555";
            String sEn = encrypt(sId);
            System.out.println("sEn=====>"+sEn);
            System.out.println("sDn=====>"+decrypt(sEn));
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static String getSHA256(String source) {
        String SHA256 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(source.getBytes());

            byte[] byteData = md.digest();

            SHA256 = Hex.encodeHexString(byteData);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            SHA256 = null;
        }
        return SHA256;
    }

    public static String setPasswordCheck(String user_id, String user_pwd) {

        String msg = "";

        if(user_pwd.indexOf("\'") != -1){
            msg = "\' 포함할수없습니다.";
        }
        if(user_pwd.indexOf("\"") != -1){
            msg = "\" 포함할수없습니다.";
        }
        if(user_pwd.indexOf("\\") != -1){
            msg = "\\ 포함할수없습니다.";
        }
        if(user_pwd.indexOf("|") != -1){
            msg = "| 포함할수없습니다.";
        }

        // 영문+숫자+특수문자 조합
        //if (digitCheck(user_pwd) < 3) {
        //	msg = "영문+숫자+특수문자 조합으로 입력해주세요.";
        //}

        //아이디 포함 여부
        //if(user_pwd.indexOf(user_id) != -1){
        //	msg = "패스워드에 아이디가 포함되어있습니다.";
        //}

        //중복된 3자 이상의 문자 또는 숫자 사용불가
        if (checkDuplicate3Character(user_pwd)) {
            msg = "중복된 3자 이상의 문자 사용불가입니다.";
        }

        return msg;
    }

    public static boolean checkDuplicate3Character(String d) {
        int p = d.length();
        byte[] b = d.getBytes();
        for (int i = 0; i < ((p * 2) / 3); i++) {
            int b1 = b[i + 1];
            int b2 = b[i + 2];

            if ((b[i] == b1) && (b[i] == b2)) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

    public static int digitCheck(String passwd) {

        int varDigit = 0;
        int varAlpha = 0;
        int varHex = 0;
        int varSum = 0;
        for (int i = 0; i < passwd.length(); i++) {
            char index = passwd.charAt(i);

            if (index >= '0' && index <= '9') {
                varDigit = 1;
            } else if ( (index >= 'a' && index <= 'z') || (index >= 'A' && index <= 'Z') ) {
                varAlpha = 1;
            } else if (index == '!' || index == '@' || index == '$'
                    || index == '%' || index == '^' || index == '&'
                    || index == '*') {
                varHex = 1;
            }
        }

        varSum = varDigit + varAlpha + varHex;

        return varSum;
    }
}
