package com.bwei.recyclerviewpager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.recyclerviewpager.bean.Bean3;
import com.bwei.recyclerviewpager.bean.Bean4;

import java.util.ArrayList;

/**
 * 作    者 ： 文欢
 * 时    间 ： 2017/2/24.
 * 描    述 ：
 * 修改时间 ：
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Bean3> items2;
    private Bean4 bean4;

    public MyRecyclerAdapter(Context context, ArrayList<Bean3> items2, Bean4 bean4) {
        this.context = context;
        this.items2 = items2;
        this.bean4 = bean4;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView

        View view = View.inflate(parent.getContext(), R.layout.item_recycler, null);

        // 创建一个ViewHolder

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_re.setText(bean4.body);
        holder.tv_title.setText(bean4.subject);
        App.imageLoader.displayImage("http://img.dxycdn.com/avatars/120/" + items2.get(position).infoAvatar, holder.img_title);
//        App.imageLoader.displayImage("http://res.dxycdn.com/upload/" + bean4.url, holder.img_re);

//            holder.img_re.setVisibility(View.INVISIBLE);



    }

    @Override
    public int getItemCount() {
        return items2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_re;
        private ImageView img_re;
        private TextView tv_title;
        private ImageView img_title;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_re = (TextView) itemView.findViewById(R.id.tv_re);
            img_re = (ImageView) itemView.findViewById(R.id.img_re);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            img_title = (ImageView) itemView.findViewById(R.id.img_title);

        }
    }
}
