package dog.cn.vapktest;

import android.app.Application;
import android.content.Context;

import com.didi.virtualapk.PluginManager;



public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        HermesEventBus.getDefault().init(this);
        //测试提价o
    }

    public static String aaa="33333333";
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        PluginManager.getInstance(base).init();
    }
}
