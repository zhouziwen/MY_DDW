package com.example.bbacr.ddw.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nianner Wang on 2017/9/23.
 * wytaper495@qq.com
 * 类注释：一些公共的方法
 */

public class CommonUtils {

    /**
     * 获取assets文件的字符串
     */
    public static String getFromAssets(String fileName, Context context){
        try {
            InputStreamReader inputReader = new InputStreamReader( context.getResources().getAssets().open(fileName) );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";
            String Result="";
            while((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 手机号号段校验，
     第1位：1；
     第2位：{3、4、5、6、7、8}任意数字；
     第3—11位：0—9任意数字
     * @param value
     * @return
     */
    public static boolean isTelPhoneNumber(String value) {
        if (value != null && value.length() == 11) {
            Pattern pattern = Pattern.compile("^1[3|4|5|6|7|8][0-9]\\d{8}$");
            Matcher matcher = pattern.matcher(value);
            return matcher.matches();
        }
        return false;
    }
}
