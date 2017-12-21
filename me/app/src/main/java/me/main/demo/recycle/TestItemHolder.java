package me.main.demo.recycle;

import android.view.ViewGroup;
import android.widget.TextView;

import baselib.Adapter.BaseViewHolder;
import me.main.R;

/**
 * 作者：wjh on 2017/12/21 20:22
 */
public class TestItemHolder extends BaseViewHolder<Consumption> {
    private TextView name;
    private TextView type;
    private TextView consumeNum;
    private TextView remainNum;
    private TextView consumeAddress;
    private TextView time;

    public TestItemHolder(ViewGroup parent) {
        super(parent, R.layout.holder_consume);
    }

    @Override
    protected void onInitializeView() {
        name = findViewById(R.id.name);
        type = findViewById(R.id.type);
        consumeNum = findViewById(R.id.consume_num);
        remainNum = findViewById(R.id.remain_num);
        consumeAddress = findViewById(R.id.consume_address);
        time = findViewById(R.id.time);
    }

    @Override
    public void setData(Consumption data) {
        super.setData(data);
        name.setText("Demo");
        type.setText(data.getLx());
        consumeNum.setText("消费金额：" + data.getJe());
        remainNum.setText("卡里余额：" + data.getYe());
        consumeAddress.setText(data.getSh());
        time.setText(data.getSj());
    }
}
