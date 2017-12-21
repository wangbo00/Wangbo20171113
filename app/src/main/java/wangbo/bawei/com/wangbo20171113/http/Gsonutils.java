package wangbo.bawei.com.wangbo20171113.http;

import com.google.gson.Gson;

/**
 * author:Created by Wangbo on 2017/11/15.
 */

public class Gsonutils {
    private static volatile Gson instance;

    public Gsonutils(){

    }
    public static Gson getInstance(){
        if (instance==null){
            instance=new Gson();
        }
        return instance;
    }
}
