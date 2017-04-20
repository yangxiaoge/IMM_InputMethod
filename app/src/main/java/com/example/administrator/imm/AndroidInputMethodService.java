package com.example.administrator.imm;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodSubtype;

/**
 * Created by yang.jianan on 2017/04/19 14:37.
 * 开发参考：http://blog.csdn.net/le_go/article/details/9264831
 * 输入法服务的生命周期图：http://img.blog.csdn.net/20130707203833640
 */

public class AndroidInputMethodService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {
    private String TAG = AndroidInputMethodService.class.getName();

    private KeyboardView keyboardView; // 对应keyboard.xml中定义的KeyboardView
    private Keyboard keyboard; // 对应qwerty.xml中定义的Keyboard

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
    }

    @Override
    public View onCreateInputView() {
        // keyboard被创建后，将调用onCreateInputView函数
        keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);  // 此处使用了keyboard.xml
        keyboard = new Keyboard(this, R.xml.qwerty); // 此处使用了qwerty.xml
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);

        Log.d(TAG, "onCreateInputView()");
        return keyboardView;
    }

    /*    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_hide) {
            // 隐藏软键盘
            hideWindow();
        } else {
            Button button = (Button) v;
            // 获得InputConnection对象, InputConnection由客户端控件创建，并传递给输入法应用，由输入法应用调用，进行信息反馈
            InputConnection inputConnection = getCurrentInputConnection();
            if (button.getId() == R.id.btn1) {
                // 设置预输入文本
                // setComposingText方法的第2个参数值为1，表示在当前位置预输入文本
                inputConnection.setComposingText(button.getText(), 1);
            } else {
                // 向当前获得焦点的EditText控件输出文本
                // commitText方法第2个参数值为1，表示在当前位置插入文本
                inputConnection.commitText(button.getText(), 1);
            }
        }
    }*/

    @Override
    public View onCreateCandidatesView() {
        Log.d(TAG, "onCreateCandidatesView()");
        return super.onCreateCandidatesView();
    }

    @Override
    public void onStartInputView(EditorInfo info, boolean restarting) {
        super.onStartInputView(info, restarting);
        Log.d(TAG, "onStartInputView()");
    }

    @Override
    protected void onCurrentInputMethodSubtypeChanged(InputMethodSubtype newSubtype) {
        super.onCurrentInputMethodSubtypeChanged(newSubtype);
        Log.d(TAG, "onCurrentInputMethodSubtypeChanged()");
    }

    @Override
    public void onFinishInput() {
        super.onFinishInput();
        Log.d(TAG, "onFinishInput()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection inputConnection = getCurrentInputConnection();

        switch (primaryCode) {
            case Keyboard.KEYCODE_DELETE: //删除键
                inputConnection.deleteSurroundingText(1, 0);
                break;
            case Keyboard.KEYCODE_DONE: //完成键
                inputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                //hideWindow(); //隐藏键盘
                break;
            default: //普通输入
                char code = (char) primaryCode;
                inputConnection.commitText(String.valueOf(code) + "嘿", 1); //可以对输入的字符串做 加密等等处理
        }
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
