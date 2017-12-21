package wangbo.bawei.com.wangbo20171113.callback;

/**
 * Created by wangbo on 2017/11/13.
 */

public interface CallBack {
    void onSuccess(String tag, Object o);
    void onFailed(String tag, Exception e);
}
