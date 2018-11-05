package dog.cn.vapktest;

import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.MessageEvent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.didi.virtualapk.PluginManager;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        EventBus.getDefault().register(this);
//        HermesEventBus.getDefault().register(this);
        ((Button)findViewById(R.id.lala)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPlugin();
            }
        });
        CopyToSd();
//        loadPlugin();
    }
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void Event(MessageEvent messageEvent) {
////        mText.setText(messageEvent.getMessage());
//        Log.i("AAAAA","MainActivity收到消息:"+messageEvent.getMessage());
//        Toast.makeText(this,"MainActivity收到消息"+messageEvent.getMessage(),Toast.LENGTH_SHORT).show();
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if(EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().unregister(this);
//        }
//        if(HermesEventBus.getDefault().isRegistered(this)) {
//            HermesEventBus.getDefault().unregister(this);
//        }
    }


    void CopyToSd() {
        FileUtils.getInstance(this).copyAssetsToSD("", "AAA").setFileOperateCallback(new FileUtils.FileOperateCallback() {
            @Override
            public void onSuccess() {
                // TODO: 文件复制成功时，主线程回调
                Toast.makeText(MainActivity.this, "复制成功", Toast.LENGTH_SHORT).show();
                loadPlugin();
            }

            @Override
            public void onFailed(String error) {
                // TODO: 文件复制失败时，主线程回调
                Toast.makeText(MainActivity.this, "复制失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    PluginManager pluginManager;

    public void loadPlugin() {

        pluginManager = PluginManager.getInstance(this);

        //此处是当查看插件apk是否存在,如果存在就去加载(比如修改线上的bug,把插件apk下载到sdcard的根目录下取名为plugin-release.apk)
        File apk = new File(Environment.getExternalStorageDirectory() + "/AAA/", "dog.apk");

        if (apk.exists()) {
            try {
                pluginManager.loadPlugin(apk);
                Toast.makeText(this, "yes！", Toast.LENGTH_SHORT).show();
                ToPlugin_Activity();//插件加载成功跳转
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "no！", Toast.LENGTH_SHORT).show();
            }
        }


    }

    void ToPlugin_Activity() {
        Intent intent = new Intent();
        intent.setClassName("dog.cn.module_test", "dog.cn.module_test.m_MainActivity");
        intent.putExtra("data", "aaaaaaaaaaa");
        startActivity(intent);

        ClassLoader classLoader = getClassLoader();
        Fragment fg = null;
        try {
            fg = (Fragment) classLoader.loadClass("dog.cn.module_test.Plugin_Fragment").newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fg).commitAllowingStateLoss();

            //            FragmentManager fragmentManager = getFragmentManager();
//            FragmentTransaction transaction = fragmentManager.beginTransaction();
//            transaction.replace(R.id.fragment, fg);
//            transaction.commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
