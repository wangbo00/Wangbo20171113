package wangbo.bawei.com.wangbo20171113.callback;

import java.util.List;

import wangbo.bawei.com.wangbo20171113.entity.NewsotherBean;

/**
 * Created by wangbo on 2017/11/13.
 */

public interface INewsView {
    void success(String tag, List<NewsotherBean> data);
    void filed(String tag, Exception e);
}
