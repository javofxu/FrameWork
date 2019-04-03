package com.example.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Skygge on 2018/10/01.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements UIInterface, BaseView, IAlertDialogView {

    protected boolean isVisible;
    protected T mPresent;

    protected String TAG = getClass().getSimpleName();
    private AlertDialog mProgressDialog;
    private Unbinder mUnBinder;
    protected IAlertDialogPresent iAlertDialogPresent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        processBeforeInitView();
        iAlertDialogPresent = new IAlertDialogPresentImpl(this);
        if (getLayoutId() != -1) {
            setContentView(getLayoutId());
            mUnBinder = ButterKnife.bind(this);

        }
        initPresent();
        if (mPresent != null) {
            mPresent.attachView(this);
        }
        hideStatusBar();
        initView();
        initData();
        startServices();
        initListener();
    }


    /**
     * 初始化Present
     */
    protected void initPresent() {

    }

    /**
     * 设置标题
     */
    protected int setCenterTitle() {
        return -1;
    }


    /**
     * 在初始化布局钱的操作
     */
    protected void processBeforeInitView() {
    }

    protected int getLayoutId() {
        return -1;
    }

    /**
     * 初始化布局
     */
    protected void initView() {

    }


    /**
     * 初始化数据
     */
    @Override
    public void initData() {

    }

    /**
     * 开启服务
     */

    protected void startServices(){

    }

    /**
     * 关闭服务
     */
    protected void stopService(){

    }


    protected void initListener() {
    }

    @Override
    protected void onStart() {
        super.onStart();
        isVisible = true;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        isVisible = false;
    }

    /**
     * 为状态栏设置颜色
     */
    protected void setStatusBarColor(int color, boolean isNavigationBarHide) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //将状态栏设置成透明
            window.setStatusBarColor(color);
            //将底部的虚拟键设置成共鸣
            if (isNavigationBarHide) {
//                window.setNavigationBarColor(Color.parseColor("#000000"));

            }
        }
    }

    /**
     * 隐藏状态栏和虚拟栏
     */
    protected void hideStatusBar() {
        setStatusBarColor(Color.TRANSPARENT, true);
    }


    /**
     * 跳转到另外一个Activity
     *
     * @param clazz 对应的字节码
     */
    protected void skipAnotherActivity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    protected void skipAnotherActivityForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    protected void skipAnotherActivity(Bundle bundle, Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService();
        if (mPresent != null) {
            mPresent.detachView();
        }
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        AppManager.getAppManager().removeActivity(this);

    }

    protected void  showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }


    /**
     * 显示进度条的对话框
     */
    @Override
    public void showProgressDialog() {
        showProgressDialogs("");
    }

    public void showProgressDialog(String msg){
        showProgressDialogs(msg);
    }

    protected final void showProgressDialogs(String tips) {
        mProgressDialog = new AlertDialog.Builder(this).create();
        mProgressDialog.setCanceledOnTouchOutside(true);
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
        Window window = mProgressDialog.getWindow();
        window.setContentView(R.layout.dialog_progress);
        window.setDimAmount(0f);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        TextView tv = window.findViewById(R.id.tv_pos);
        tv.setText(tips);
    }


    /**
     * 隐藏进度条对话框
     */
    @Override
    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }


    @Override
    public void showErrorMsg(String mErrorMsg) {
        if (TextUtils.isEmpty(mErrorMsg)) {
            return;
        }
        showToast(mErrorMsg);
    }


    @Override
    public void stateError() {

    }

    @Override
    public void showSuccess() {
        showToast(getResources().getString(R.string.Success));
    }

    @Override
    public void showFailed() {
        showToast(getResources().getString(R.string.Failed));
    }


    @Override
    public void errorCode(int errorCode) {
        //回到登录界面
    }

    @Override
    public void onPositiveButtonClicked(int titleId) {
    }

    @Override
    public void onNegativeButtonClicked(int titleId) {

    }


    protected final int getStatusBarHeight() {
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    protected final int getNavigationBarHeight() {
        int navigationBarHeight = 0;
        int resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            navigationBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        return navigationBarHeight;
    }

    protected void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

    /**
     * 设置点击EditText外部隐藏键盘
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    /**
     * 判断是否需要隐藏键盘
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
