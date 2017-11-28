package baselib.view;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;

/**
 * EditText输入手机号自动带空格用户输入手机号便于观看
 * 格式:186 xxxx xxxx
 * 作者：wjh on 2017/11/28 15:39
 */
public class EditTextNumberSignedView extends android.support.v7.widget.AppCompatEditText {

    public EditTextNumberSignedView(Context context) {
        super(context);
        init();
    }

    public EditTextNumberSignedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextNumberSignedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);

        initListener();
    }

    private void initListener() {
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence == null || charSequence.length() == 0)
                    return;
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < charSequence.length(); i++) {
                    if (i != 3 && i != 8 && charSequence.charAt(i) == ' ') {
                        continue;
                    } else {
                        stringBuilder.append(charSequence.charAt(i));
                        if ((stringBuilder.length() == 4 || stringBuilder.length() == 9)
                                && stringBuilder.charAt(stringBuilder.length() - 1) != ' ') {
                            stringBuilder.insert(stringBuilder.length() - 1, ' ');
                        }
                    }
                }
                if (!stringBuilder.toString().equals(charSequence.toString())) {
                    int index = start + 1;
                    if (stringBuilder.charAt(start) == ' ') {
                        if (before == 0) {
                            index++;
                        } else {
                            index--;
                        }
                    } else {
                        if (before == 1) {
                            index--;
                        }
                    }
                    setText(stringBuilder.toString());
                    setSelection(index);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
