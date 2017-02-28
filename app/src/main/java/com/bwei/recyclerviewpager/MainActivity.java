package com.bwei.recyclerviewpager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwei.recyclerviewpager.bean.Bean;
import com.bwei.recyclerviewpager.bean.Bean2;
import com.bwei.recyclerviewpager.bean.Bean3;
import com.bwei.recyclerviewpager.bean.Bean4;
import com.bwei.recyclerviewpager.bean.Items;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ImageView> clist;
    String url1 = "http://i.dxy.cn/snsapi/event/count/list/all";
    String url2 = "http://i.dxy.cn/snsapi/home/feeds/list/all?sid=4df0360f-2a20-4198-beb8-4dc5660c4f08&u=zhetianyishou&s=10&mc=0000000049029dcaffffffff99d603a9&token=TGT-13165-buaw5fHpqLlefw9bSOB0oF41fobaV4rMZmK-50&hardName=iToolsAVM_T0008098S&ac=4124c5f1-2029-4fda-b06f-a87ac5ad8d11&bv=2013&vc=6.0.6&tid=c25e673d-e82a-4e46-bd4e-c1e86d497126&vs=4.4.4&ref_tid=54720e1a-7eed-4993-9f51-3d760f3d0b2e";
    private ViewPager viewpager;
    private TextView title;
    private LinearLayout line;
    private ArrayList<Items> items;
    private RecyclerView recyclerview;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            int currentItem = viewpager.getCurrentItem();
            currentItem++;
            viewpager.setCurrentItem(currentItem);
            handler.sendEmptyMessageDelayed(0,2000);

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化界面
        initView();
        //网络请求
        OkHttpUtil okHttpUtil = new OkHttpUtil();
        okHttpUtil.getJson(url1,new HttpData());

        //网络请求
        OkHttpUtil okHttpUtil2 = new OkHttpUtil();
        okHttpUtil2.getJson(url2,new HttpData2());

        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                //遍历图片集合
                for (int i=0;i<items.size();i++){
                    //索引相同即为亮点
                    if (i==position%items.size()){
                        clist.get(i).setImageResource(R.drawable.ago);
                    }else{
                        clist.get(i).setImageResource(R.drawable.later);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        title = (TextView) findViewById(R.id.title);
        line = (LinearLayout) findViewById(R.id.line);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
    }

   class HttpData extends OkHttpUtil.HttpCallBack{

       @Override
       public void onSusscess(String data) {

           Bean bean = App.gson.fromJson(data, Bean.class);
           items = bean.items;
           getData(items);
       }


   }

    private void getData(ArrayList<Items> items) {
        //初始化小圆点
        clist = new ArrayList<>();
        for (int i = 0; i <items.size(); i++){
            ImageView imageCircle = new ImageView(this);
            if (i==0){
                imageCircle.setImageResource(R.drawable.ago);
            }else {
                imageCircle.setImageResource(R.drawable.later);
            }
            //默认圆点高度20
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20,20);
            //设置间距
            params.setMargins(5,0,5,0);
            //放在容器中
            line.addView(imageCircle,params);
            clist.add(imageCircle);

        }

        MyAdapter myAdapter = new MyAdapter(this,items);
        viewpager.setAdapter(myAdapter);

        //設置初始化的界面
        viewpager.setCurrentItem(items.size()*10000000);
        //發送延時操作
        handler.sendEmptyMessageDelayed(0,2000);
    }
    class HttpData2 extends OkHttpUtil.HttpCallBack{


        private Bean4 bean4;

        @Override
        public void onSusscess(String data) {


            Bean2 bean = App.gson.fromJson(data, Bean2.class);
            ArrayList<Bean3> items2 = bean.items;
//            Log.d("123", "onSusscess: "+items2);
            for (int i=0;i<items2.size();i++){
                bean4 = App.gson.fromJson(items2.get(i).content, Bean4.class);
//                Log.d("123", "onSusscess: "+bean4.toString());
            }

            getData2(items2,bean4);
        }


    }
    private void getData2(ArrayList<Bean3> items2,Bean4 bean4) {

        // 创建一个线性布局管理器

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        // 设置布局管理器

        recyclerview.setLayoutManager(layoutManager);

        MyRecyclerAdapter myRecyclerAdapter =  new MyRecyclerAdapter(MainActivity.this,items2,bean4);
        recyclerview.setAdapter(myRecyclerAdapter);
    }


}
