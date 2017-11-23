package baselib.util;

import android.graphics.Paint;
import android.os.Build;
import android.text.Editable;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * EditText 常用的一些属性值设置
 *
 * @author wjh
 */
public class EditTextUtil {

    /**
     * 光标设置最后
     *
     * @param editText
     */
    public static void setSelectionEnd(EditText editText) {
        if (editText != null) {
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
            Editable edit = editText.getText();
            Selection.setSelection(edit, edit.length());
        }
    }

    /**
     * 全选
     *
     * @param editText
     */
    public static void setSelectAllOnFocus(EditText editText) {
        if (editText != null) {
            editText.setSelectAllOnFocus(true);
        }
    }

    /**
     * 关键字高亮
     *
     * @param tv
     * @param content   要高亮显示的原字符串
     * @param keyWord   要高亮显示的关键字，如搜索关键字
     * @param linkColor
     */
    public static void setKeyWordLink(TextView tv, String content, String keyWord, int linkColor) {
        if (TextUtils.isEmpty(content) || TextUtils.isEmpty(keyWord)) {
            return;
        }

        int searchWordLength = keyWord.length();
        int searchCursor = 0;
        SpannableStringBuilder builder = new SpannableStringBuilder(content);
        while (true) {
            int indexOf = content.substring(searchCursor).indexOf(keyWord);
            if (indexOf != -1) {
                int start = searchCursor + indexOf;
                int end = start + searchWordLength;
                builder.setSpan(new ForegroundColorSpan(linkColor), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                searchCursor = end;
            } else {
                break;
            }
        }
        tv.setText(builder);
    }

    /**
     * 设置EditText的可编辑状态
     *
     * @param textView
     * @param isState  true:可编辑,false:不可编辑
     */
    public static void setFocusable(TextView textView, boolean isState) {
        if (textView != null) {
            if (isState) {
                textView.setFocusableInTouchMode(true);
                textView.setFocusable(true);
                textView.setCursorVisible(true);
                textView.requestFocus();
            } else {
                textView.setFocusable(false);
                textView.setFocusableInTouchMode(false);
                textView.setCursorVisible(false);
            }
        }
    }

    /**
     * 获取行高
     *
     * @param textView
     * @return
     */
    public static float getLineHeight(TextView textView) {
        if (textView == null) {
            return 0;
        }

        Paint.FontMetrics fontMetrics = textView.getPaint().getFontMetrics();
        float lineSpacing = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            lineSpacing = textView.getLineSpacingExtra();
        }

        return Math.abs(fontMetrics.bottom) + Math.abs(fontMetrics.top) + lineSpacing;
    }

    /**
     * 设置几行的高度
     *
     * @param textView
     * @param lines
     */
    public static void setTextViewHeight(TextView textView, int lines) {
        if (lines < 1 || textView == null) {
            return;
        }

        int linesHeight = textView.getLineHeight() * lines;
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        layoutParams.height = linesHeight + textView.getPaddingTop() + textView.getPaddingBottom();
        textView.setLayoutParams(layoutParams);
    }

}
