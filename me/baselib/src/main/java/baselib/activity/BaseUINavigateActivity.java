package baselib.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import baselib.R;

/**
 * 带导航栏的Activity
 * 作者：wjh on 2017/11/24 12:09
 */
public abstract class BaseUINavigateActivity extends BaseUIActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.include_layout_normal);
    }

    @Override
    protected void init() {
        LayoutInflater.from(baseActivity).inflate(getViewId(), findViewById(R.id.baseContainer), true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        onKeyBack(new KeyBack() {
            @Override
            public void onBack() {
                back();
            }
        });
        super.init();
    }

    public abstract int getViewId();

    public abstract int getMenuRes();

    public abstract void onCreateMenu(Menu menu);

    public abstract boolean onMenuItemSelected(MenuItem item);

    protected void back() {
        finish();
    }

    public void setToolbarTile(@StringRes int title) {
        getToolbar().setTitle(title);
    }

    public void setToolbarTile(String title) {
        getToolbar().setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getMenuRes() > 0) {
            getMenuInflater().inflate(getMenuRes(), menu);
            onCreateMenu(menu);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (onMenuItemSelected(item)) {
            return super.onOptionsItemSelected(item);
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                back();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
