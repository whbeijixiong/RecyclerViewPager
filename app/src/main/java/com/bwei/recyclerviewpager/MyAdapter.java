package com.bwei.recyclerviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.recyclerviewpager.bean.Items;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * 作    者 ： 文欢
 * 时    间 ： 2017/2/23.
 * 描    述 ：
 * 修改时间 ：
 */

public class MyAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<Items> items;

    public MyAdapter(Context context, ArrayList<Items> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final int i = position % items.size();
        View view = View.inflate(context,R.layout.item_adapter,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.img);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(new ImageLoaderConfiguration.Builder(context).build());
        imageLoader.displayImage(items.get(i).path, imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(items.get(i).title);
        container.addView(view);
        return view;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return  view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
