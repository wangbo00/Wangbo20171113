package wangbo.bawei.com.wangbo20171113;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import wangbo.bawei.com.wangbo20171113.adapter.Adapter;
import wangbo.bawei.com.wangbo20171113.callback.INewsView;
import wangbo.bawei.com.wangbo20171113.entity.NewsotherBean;
import wangbo.bawei.com.wangbo20171113.presenter.NewsPresenter;

public class MainActivity extends AppCompatActivity implements INewsView {

    private ImageView imageview;
    private EditText editText;
    private Button button;
    private XRecyclerView recyclerView;
    private ArrayList<NewsotherBean> list;
    private Adapter adapter;
    private int i=1;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        //设置可以上拉
        //设置可以上拉
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.setLoadingMoreEnabled(true);
        //设置上拉下拉样式
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);


        LinearLayoutManager manager  = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        //添加布局管理器
        recyclerView.setLayoutManager(manager);
        adapter = new Adapter(MainActivity.this,list,1);
        recyclerView.setAdapter(adapter);
        //设置监听
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            //下拉
            @Override
            public void onRefresh() {
                i=1;
                list.clear();
                loaddata(i);
                recyclerView.refreshComplete();
            }
            //上拉
            @Override
            public void onLoadMore() {
                i++;
                loaddata(i);
                recyclerView.loadMoreComplete();
            }
        });
        //Button搜索按钮进行监听
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=1;
                list.clear();
                loaddata(i);
            }
        });
        //切换布局图片的监听
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    GridLayoutManager gridLayoutManager=new GridLayoutManager(MainActivity.this,2,LinearLayoutManager.VERTICAL,false);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    adapter=new Adapter(MainActivity.this,list,2);
                    recyclerView.setAdapter(adapter);
                    flag=false;
                    imageview.setImageResource(R.drawable.lv_icon);
                }else {
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    adapter=new Adapter(MainActivity.this,list,1);
                    recyclerView.setAdapter(adapter);
                    flag=true;
                    imageview.setImageResource(R.drawable.grid_icon);
                }
            }
        });
    }
    //根据edidtext上输入的值判断加载什么数据
    public void loaddata(int i){
        String str = editText.getText().toString().trim();
        if(str.trim().equals("手机")||str.trim().equals("笔记本")){
            NewsPresenter presenter=new NewsPresenter(MainActivity.this,MainActivity.this);
            presenter.getNews(str,i);
        }else {
            Toast.makeText(MainActivity.this,"请输入正确的信息",Toast.LENGTH_SHORT).show();
        }
    }


    private void initview() {
        imageview = (ImageView) findViewById(R.id.imageView);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        recyclerView = (XRecyclerView) findViewById(R.id.xrecyclerview);
        list = new ArrayList<NewsotherBean>();
    }

    @Override
    public void success(String tag, List<NewsotherBean> data) {
        list.addAll(data);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void filed(String tag, Exception e) {

    }
}
