package me.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.spinytech.macore.MaApplication;
import com.spinytech.macore.router.LocalRouter;
import com.spinytech.macore.router.RouterRequest;
import com.spinytech.macore.router.RouterResponse;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_one, bt_two, bt_three, bt_four, bt_five, bt_six;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_one = findViewById(R.id.bt_one);
        bt_two = findViewById(R.id.bt_two);
        bt_three = findViewById(R.id.bt_three);
        bt_four = findViewById(R.id.bt_four);
        bt_five = findViewById(R.id.bt_five);
        bt_six = findViewById(R.id.bt_six);

        bt_one.setOnClickListener(this);
        bt_two.setOnClickListener(this);
        bt_three.setOnClickListener(this);
        bt_four.setOnClickListener(this);
        bt_five.setOnClickListener(this);
        bt_six.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_one:
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

            case R.id.bt_two:
                break;

            case R.id.bt_three:
                break;

            case R.id.bt_four:
                break;

            case R.id.bt_five:
                break;

            case R.id.bt_six:
                break;
        }
    }
}
