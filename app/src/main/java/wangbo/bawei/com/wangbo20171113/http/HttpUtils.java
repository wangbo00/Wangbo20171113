package wangbo.bawei.com.wangbo20171113.http;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import wangbo.bawei.com.wangbo20171113.callback.CallBack;

/**
 * author:Created by Wangbo on 2017/11/15.
 */

public class HttpUtils {
    private static volatile HttpUtils instance;
    private static Handler handler = new Handler();

    private HttpUtils(){

    }
    public static HttpUtils getInstance(){
        if (null==instance){
            synchronized (HttpUtils.class){
                if (instance==null){
                    instance=new HttpUtils();
                }
            }

        }
        return instance;
    }
    public void get(String url, Map<String,String> map, final CallBack callBack
            , final String tag, final Class cls){
        if(TextUtils.isEmpty(url)){
            return;
        }
        StringBuffer sb=new StringBuffer();
        sb.append(url);
        if(url.contains("?")){
            if(url.indexOf("?")==url.length()-1){

            }else {
                sb.append("&");
            }
        }else {
            sb.append("?");
        }
        for(Map.Entry<String,String> entry:map.entrySet()){
            sb.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        if(sb.indexOf("&")!=-1){
            sb.deleteCharAt(sb.lastIndexOf("&"));
        }
        OkHttpClient client=new OkHttpClient
                .Builder()
                .addInterceptor(new Logger())
                .build();
        Request request=new Request.Builder()
                .get()
                .url(sb.toString())
                .build();
        Log.e("hhh",sb.toString());
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                callBack.onFailed(tag,e);
            }


            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.e("hhh",string.toString() );
                final Object o = Gsonutils.getInstance().fromJson(string, cls);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(tag,o);
                    }
                }) ;
            }
        });

    }
}

