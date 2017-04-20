package com.example.administrator.imm;

import android.inputmethodservice.InputMethodService;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.Button;

/**
 * Created by yang.jianan on 2017/04/19 14:37.
 * 开发参考：http://blog.csdn.net/le_go/article/details/9264831
 * 输入法服务的生命周期图：http://img.blog.csdn.net/20130707203833640
 */

public class AndroidInputMethodService extends InputMethodService implements View.OnClickListener {
    private String TAG = AndroidInputMethodService.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
    }

    @Override
    public View onCreateInputView() {
        //加载keyboard.xml文件
        View view = getLayoutInflater().inflate(R.layout.keyboard, null);
        //键盘 点击事件
        view.findViewById(R.id.btn1).setOnClickListener(this);
        view.findViewById(R.id.btn2).setOnClickListener(this);
        view.findViewById(R.id.btn3).setOnClickListener(this);
        view.findViewById(R.id.btn4).setOnClickListener(this);
        view.findViewById(R.id.btn_hide).setOnClickListener(this);
        Log.d(TAG, "onCreateInputView()");

        return view;
    }

    @Override
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
    }

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
}
