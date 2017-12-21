package wangbo.bawei.com.wangbo20171113.presenter;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wangbo.bawei.com.wangbo20171113.callback.CallBack;
import wangbo.bawei.com.wangbo20171113.callback.INewsView;
import wangbo.bawei.com.wangbo20171113.entity.NewsBean;
import wangbo.bawei.com.wangbo20171113.entity.NewsotherBean;
import wangbo.bawei.com.wangbo20171113.http.HttpUtils;

/**
 * author:Created by Wangbo on 2017/11/15.
 */

public class NewsPresenter {
    private Context context;
    private INewsView inv;

    public NewsPresenter(Context context,INewsView inv){
        this.context=context;
        this.inv=inv;
    }
    public void getNews(String str,int i){
        Map<String,String> map = new HashMap<>();
        map.put("source","android");
        map.put("keywords",str+"");
        map.put("page",i+"");
        HttpUtils.getInstance().get("http://120.27.23.105/product/searchProducts", map, new CallBack() {
            @Override
            public void onSuccess(String tag, Object o) {
                NewsBean bean = (NewsBean) o;
                List<NewsBean.DataBean> data = bean.getData();
                List<NewsotherBean> list = new ArrayList<NewsotherBean>();
                for (int i=0;i<data.size();i++){
                    String images = data.get(i).getImages();
                    String[] split = images.split("!");
                    list.add(new NewsotherBean(split[0],data.get(i).getTitle(),data.get(i).getPrice()+""));
                }
                Log.i("hhh",list.size()+"");
                inv.success(tag,list);


            }

            @Override
            public void onFailed(String tag, Exception e) {
                inv.filed(tag,e);
            }
        },"news",NewsBean.class);

    }
    public void datachView(){
        if (inv!=null){
            inv=null;
        }
    }



}
