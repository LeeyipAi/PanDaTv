package leeyip.pandatv.application;

import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import leeyip.pandatv.api.NetWorkApi;
import leeyip.pandatv.net.config.NetWorkConfiguration;
import leeyip.pandatv.net.http.HttpUtils;
import leeyip.pandatv.utils.Utils;


/**
 * Author： yolanda
 * <p>
 * CreateTime： 2016/12/7 0007 下午 2:11
 * <p>
 * description：
 */


public class PanDaApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
//        Buy收集
        String packageName = context.getPackageName();
        String processName = getProcessName(Process.myPid());
//        图片加载
        Fresco.initialize(context);
        Utils.init(this);
        initOkHttpUtils();
    }

    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private void initOkHttpUtils() {
        /**
         *  网络配置
         */
        NetWorkConfiguration configuration = new NetWorkConfiguration(this)
                .baseUrl(NetWorkApi.baseUrl)
                .isCache(true)
                .isDiskCache(true)
                .isMemoryCache(true);
        HttpUtils.setConFiguration(configuration);

    }

    public static Context getContext() {
        return context;
    }
}
