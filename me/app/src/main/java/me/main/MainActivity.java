package me.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.spinytech.macore.MaApplication;
import com.spinytech.macore.router.LocalRouter;
import com.spinytech.macore.router.RouterRequest;
import com.spinytech.macore.router.RouterResponse;

import baselib.activity.BaseUINavigateActivity;
import me.main.adapter.SampleAdapter;
import me.main.demo.banner.BannerActivity;

/**
 * 主页面
 */
public class MainActivity extends BaseUINavigateActivity implements AdapterView.OnItemClickListener {

    private ListView listView;

    @Override
    public int getViewId() {
        return R.layout.activity_main;
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

    @Override
    public void initView() {
        setToolbarTile(getString(R.string.activity_title_main));

        listView = findViewById(R.id.list);
    }

    @Override
    public void initText() {
    }

    @Override
    public void initListener() {
        listView.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        String[] data = getResources().getStringArray(R.array.demo_list);
        listView.setAdapter(new SampleAdapter(this, data));
    }

    @Override
    public void onIntentEvent(@NonNull Intent intent) {
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position) {
            case 0:
                try {
                    RouterResponse response = LocalRouter.getInstance(MaApplication.getMaApplication())
                            .route(MainActivity.this, RouterRequest.obtain(MainActivity.this)
                                    .provider("main")
                                    .action("A")
                                    .data("1", "我来也")
                                    .data("2", "亲"));

                    Toast.makeText(MainActivity.this, response.get(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case 1:
                startActivity(new Intent(this, BannerActivity.class));
                break;
        }
    }
}
