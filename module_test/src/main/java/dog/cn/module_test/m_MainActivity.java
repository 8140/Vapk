package dog.cn.module_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;


import java.util.Random;


public class m_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m__main);
//        HermesEventBus.getDefault().register(this);
        ((Button) findViewById(R.id.crashtext)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random ra = new Random();
//                tv.setText((ra.nextInt(10) + 1)+"-->>ABC");
//                EventBus.getDefault().post(new MessageEvent("欢迎大家浏览我写的博客"+(ra.nextInt(10) + 1)));

            }
        });
//        init();
    }



    OrientationUtils orientationUtils;
    StandardGSYVideoPlayer videoPlayer;

    private void init() {
        videoPlayer = (StandardGSYVideoPlayer) findViewById(R.id.video_play);
        String source1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
        videoPlayer.setUp(source1, true, "测试视频");
        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.VISIBLE);
        //设置旋转
        orientationUtils = new OrientationUtils(this, videoPlayer);
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);
        //设置返回按键功能
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        videoPlayer.startPlayLogic();
    }
}