package com.ly.myapplication.base

/**
 * Created by ly on 2019/7/5 11:19
 *
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresApi
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.*
import com.ly.myapplication.R


/**
 * Created by seatrend on 2018/8/20.
 */

abstract class BaseActivity : AppCompatActivity(), BaseView {

    var ivBack: ImageView? = null
    var ivRight: ImageView?=null;
    var tvPageTitle: TextView? = null
    var tvRight: TextView? = null
    var rlParent: RelativeLayout?=null;
    private var noDataView: View? = null






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN); //全屏
        setContentView(getLayout())
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// 横屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT//竖屏
        initCommonTitle()
        initView()

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            setStatusBarColor(resources.getColor(R.color.blue))
        }

    }
    @SuppressLint("WrongViewCast")
    fun initCommonTitle() {
        ivBack = findViewById(R.id.iv_back)
        ivRight = findViewById(R.id.iv_right)
        tvPageTitle = findViewById(R.id.tv_titile)
        tvRight = findViewById(R.id.tv_right)
        rlParent = findViewById(R.id.rl_parent)

        if (ivBack != null) {
            ivBack!!.setOnClickListener { finish() }
        }

    }

    abstract fun initView()

    abstract fun getLayout(): Int


    fun setPageTitle(pageTitle: String) {
        if (tvPageTitle != null) {
            tvPageTitle!!.text = pageTitle
        }
    }

    fun setRightTitle(rightTitle: String) {
        //tvRight
        if (tvRight != null) {
            tvRight!!.text = rightTitle
        }
    }

    /*private void showNetWorkError(){
        if(rlParent==null){
            return;
        }
        View view = LayoutInflater.from(this).inflate(R.layout.popupwindow_network_erroe, null);
        final PopupWindow popupWindow=new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.popmenu_animation);
        try {
            popupWindow.showAsDropDown(rlParent, 0, 0);
        }catch (Exception e){
           e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        popupWindow.dismiss();
                    }
                });
            }
        }).start();
    }*/


    protected fun hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            val v = this.window.decorView
            v.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            val decorView = window.decorView
            val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN)
            decorView.systemUiVisibility = uiOptions
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private fun setStatusBarColor(statusColor: Int) {
        val window = window
        //取消状态栏透明
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //设置状态栏颜色
        window.statusBarColor = statusColor
        //设置系统状态栏处于可见状态
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        //让view不根据系统窗口来调整自己的布局
        val mContentView = window.findViewById<View>(Window.ID_ANDROID_CONTENT) as ViewGroup
        val mChildView = mContentView.getChildAt(0)
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false)
            ViewCompat.requestApplyInsets(mChildView)
        }
    }

    override fun showErrorDialog(msg: String) {
    }

    override fun showToast(msg: String) {

    }

    override fun showToast(msgId: Int) {

    }
}
