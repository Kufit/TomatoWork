package com.huang.tomatowork;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.hjm.bottomtabbar.BottomTabBar;
import com.huang.adapter.PotatoAdapter;
import com.huang.bean.Work;
import com.huang.view.MonitorScrollView;

import java.util.ArrayList;
import java.util.List;

public class PotatoActivity extends AppCompatActivity implements MonitorScrollView.OnScrollChangedListener {

    private Context mContext;
    private RelativeLayout llContent;
    private Toolbar mToolbarTb;
    private MonitorScrollView svTranslu;
    private ImageView ivPic;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potato);
        initView();
        initListener();
        mContext = this;
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        Work work = new Work();
        work.setTitle("学英语");
        Work work1 = new Work();
        work1.setTitle("写课程设计");
        Work work2 = new Work();
        work2.setTitle("练口语");

        List<Work> works = new ArrayList<>();
        works.add(work);
        works.add(work1);
        works.add(work2);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        PotatoAdapter adapter = new PotatoAdapter(works);
        recyclerView.setAdapter(adapter);

        BottomTabBar mBottomTabBar = (BottomTabBar) findViewById(R.id.bottom_tab_bar);

        mBottomTabBar
                .init(getSupportFragmentManager())//初始化方法，必须第一个调用；传入参数为V4包下的FragmentManager
                .setImgSize(50, 50)//设置ICON图片的尺寸
                .setFontSize(8)//设置文字的尺寸
                .setTabPadding(4, 6, 10)//设置ICON图片与上部分割线的间隔、图片与文字的间隔、文字与底部的间隔
                .setChangeColor(Color.DKGRAY, Color.RED)//设置选中的颜色、未选中的颜色
               .addTabItem("第一项", R.mipmap.ic_launcher, BlankFragment.class)//设置文字、一张图片、fragment
//                .addTabItem("第二项", R.mipmap.erweima, R.mipmap.ic_launcher, TwoFragment.class)//设置文字、两张图片、fragment
                .isShowDivider(false)//设置是否显示分割线
                .setTabBarBackgroundColor(Color.WHITE)//设置底部导航栏颜色
                .setTabBarBackgroundResource(R.mipmap.ic_launcher)//设置底部导航栏的背景图片【与设置底部导航栏颜色方法不能同时使用，否则会覆盖掉前边设置的颜色】
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name, View view) {
                        Log.i("TGA", "位置：" + position + "      选项卡的文字内容：" + name);
                    }//添加选项卡切换监听
                    //@Override
//                    public void onTabChange(int position, String name) {
//                        //这里不用说，你们也都看的懂了
//                        //暂时就返回了这俩参数，如果还有什么用的比较多的参数，欢迎留言告诉我，我继续添加上
//                        Log.i("TGA", "位置：" + position + "      选项卡的文字内容：" + name);
//                    }
                })
                .setCurrentTab(0);//设置当前选中的Tab，从0开始


    }

    private void initView() {

        llContent = (RelativeLayout) findViewById(R.id.ll_content);
        svTranslu = (MonitorScrollView) findViewById(R.id.sv_translu);
        //ivPic = (ImageView) findViewById(R.id.iv_pic);
        mToolbarTb = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTb.setTitle("");
        mToolbarTb.getBackground().setAlpha(0);  //先设置透明
        setSupportActionBar(mToolbarTb);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //设为 false
//           actionBar.setDisplayShowTitleEnabled(false);  //是否隐藏标题
            actionBar.setDisplayHomeAsUpEnabled(true);     //是否显示返回按钮
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
        }

        //实现透明状态栏效果  并且toolbar 需要设置  android:fitsSystemWindows="true"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        //实现透明状态栏效果  并且toolbar 需要设置  android:fitsSystemWindows="true"
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
//            layoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | layoutParams.flags);
//        }

//        for (int i = 0;i<20;i++){
//            TextView textView = new TextView(mContext);
//            textView.setText("第" + i+"个");
//            textView.setGravity(Gravity.CENTER);
//            LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,dp2px(60));
//            llContent.addView(textView,params);
//        }
    }

    private void initListener() {
        svTranslu.setOnScrollChangedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Toolbar上的左上角的返回箭头的键值为Android.R.id.home  不是R.id.home
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //super.onCreateOptionsMenu(menu);
        //getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, sp,
                getResources().getDisplayMetrics());
    }

    @Override
    public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
        float height = ivPic.getHeight();  //获取图片的高度
        if (oldt < height) {
            int i = Float.valueOf(oldt / height * 255).intValue();    //i 有可能小于 0
            mToolbarTb.getBackground().setAlpha(Math.max(i, 0));   // 0~255 透明度
        } else {
            mToolbarTb.getBackground().setAlpha(255);
        }
    }
}
