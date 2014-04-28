package com.framework.util.dom;

import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;

/**
 * Created by LIUYONG on 14-2-13.
 */
@Component
public class DomHandler {

    public String getContent(String strUrl)
    // 一个public方法，返回字符串，错误则返回"error open url"
    {
        try{

            URL url = new URL(strUrl);
            BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));
            String s = "";
            StringBuffer sb = new StringBuffer("");
            while((s = br.readLine()) != null)
            {
                sb.append(s + "\r\n");
            }
            br.close();
            return sb.toString();
        }
        catch(Exception e){
            return "error open url" + strUrl;

        }
    }

    public String getSubStringFromStrToStr(String str, String start, String end) {
        return str.substring(str.indexOf(start), str.indexOf(end) + end.length());
    }

    public String getSubStringFromStr(String str, String start) {
        return str.substring(str.indexOf(start));
    }

    public String getSubStringToStr(String str, String end) {
        return str.substring(0, str.indexOf(end) + end.length());
    }

}
