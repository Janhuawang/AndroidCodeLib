package me.main.demo.recycle;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import baselib.activity.BaseUINavigateActivity;
import baselib.adapter.RecyclerAction;
import me.main.R;

/**
 * 作者：wjh on 2017/12/21 19:56
 */

public class RecycleTestActivity extends BaseUINavigateActivity {

    private RecyclerView recycler_view;
    private RecycleTestAdapter recycleTestAdapter;

    @Override
    public void initView() {
        recycler_view = findViewById(R.id.recycler_view);
    }

    @Override
    public void initText() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycleTestAdapter = new RecycleTestAdapter(this, null);
        recycleTestAdapter.setLoadMoreAction(new RecyclerAction() {
            @Override
            public void onAction() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recycleTestAdapter.addData(getRecordVirtualData());
                    }
                }, 5000);

            }
        });

        recycler_view.setAdapter(recycleTestAdapter);
        recycleTestAdapter.setData(getRecordVirtualData());
    }

    @Override
    public void onIntentEvent(@NonNull Intent intent) {

    }

    @Override
    public int getViewId() {
        return R.layout.activity_demo_recycle;
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

    public Consumption[] getRecordVirtualData() {
        return new Consumption[]{
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼")
        };
    }
}
