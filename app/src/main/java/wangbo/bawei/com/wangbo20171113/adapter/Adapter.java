package wangbo.bawei.com.wangbo20171113.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import wangbo.bawei.com.wangbo20171113.R;
import wangbo.bawei.com.wangbo20171113.entity.NewsotherBean;

/**
 * Created by 杨文倩 on 2017/11/14.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private List<NewsotherBean> list;
    private int j;

    public Adapter(Context context, List<NewsotherBean> list, int j) {
        this.context = context;
        this.list = list;
        this.j = j;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //自条目布局
        if(j==1){
           View view= View.inflate(context, R.layout.item_01,null);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }else {
            View view = View.inflate(context,R.layout.item_02,null);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg()).into(holder.img);
        holder.textView.setText(list.get(position).getTitle());
        holder.textView2.setText(list.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        private  ImageView img;
        private  TextView textView;
        private  TextView textView2;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.image);
            textView = (TextView) itemView.findViewById(R.id.textView);
            textView2 = (TextView) itemView.findViewById(R.id.textView2);

        }
    }
}
