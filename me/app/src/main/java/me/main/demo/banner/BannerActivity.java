package me.main.demo.banner;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;

import baselib.activity.BaseUINavigateActivity;
import baselib.widget.Banner;
import baselib.widget.banner.BannerConfig;
import baselib.widget.banner.Transformer;
import me.main.R;
import me.main.resource.LoadResource;

/**
 * 轮播图Demo
 * 作者：wjh on 2017/11/28 17:23
 */
public class BannerActivity extends BaseUINavigateActivity {
    private Banner banner;

    @Override
    public void initView() {
        banner = findViewById(R.id.widgetBanner);
    }

    @Override
    public void initText() {
    }

    @Override
    public void initListener() {
    }

    @Override
    public void initData() {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(LoadResource.images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.ScaleInOut);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(LoadResource.titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void onIntentEvent(@NonNull Intent intent) {

    }

    @Override
    public int getViewId() {
        return R.layout.activity_demo_banner;
    }

    @Override
    public int getMenuRes() {
        return 0;
    }

    @Override
    public void onCreateMenu(Menu menu) {

    }

    @Override
    public boolean onMenuItemSelected(MenuItem item) {
        return false;
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }
}
