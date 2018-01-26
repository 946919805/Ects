package com.gzdc.ecar.Until;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/2.
 */
public class Helper {
    /**
     * 提示消息
     */
    public static void showMsg(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 提示消息
     *
     * @Title: showMsg
     */
    public static void showMsg(Context ctx, int msg) {
        Toast.makeText(ctx, ctx.getString(msg), Toast.LENGTH_SHORT).show();
    }
  public static String toBase64(String  path) throws IOException {



        String strBase64 = null;
        InputStream in = new FileInputStream(path);
        byte[] bytes = new byte[1024];
        bytes = new byte[in.available()];
        in.read(bytes);
        strBase64 = android.util.Base64.encodeToString(bytes, android.util.Base64.DEFAULT); //将字节流数组转换为字符串
        in.close();
      return strBase64;

  }
    public static String getday(String day){
        String mouths = null;
        String days = null;
        String[] str =day.split("-");
        if(Integer.parseInt(str[1])<10){
            mouths="0"+str[1];
        }else {
            mouths=str[1];
        }
        if(Integer.parseInt(str[2])<10){
            days="0"+str[2];
        }else {
            days=str[2];
        }
        return str[0]+"-"+mouths+"-"+days;
    }

}
