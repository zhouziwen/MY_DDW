package com.example.bbacr.ddw.application;

import android.app.Application;

import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.utils.DisPlayUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.common.QueuedWork;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class App extends Application {
    private static App mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Config.DEBUG = true;
        DisPlayUtils.init(this);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        PreferenceManager.instance().init(this);
        QueuedWork.isUseThreadPool = false;
        UMShareAPI.get(this);
        //Fresco初始化
        Fresco.initialize(this);
    }
    public static App getInstance(){
        return mInstance;
    }

    //各个平台的配置，建议放在全局Application或者程序入口
    {
        PlatformConfig.setWeixin("wxee7e8ed2710ba22a", "3397618b9aba1f9d3ff081957d581e0a");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
        PlatformConfig.setQQZone("101442252", "2b3d1944b44d5193cf2b827b03c64bc6");
        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
        PlatformConfig.setAlipay("2015111700822536");
        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
        PlatformConfig.setPinterest("1439206");
        PlatformConfig.setKakao("e4f60e065048eb031e235c806b31c70f");
        PlatformConfig.setDing("dingoalmlnohc0wggfedpk");
        PlatformConfig.setVKontakte("5764965","5My6SNliAaLxEm3Lyd9J");
        PlatformConfig.setDropbox("oz8v5apet3arcdy","h7p2pjbzkkxt02a");
        PlatformConfig.setYnote("9c82bf470cba7bd2f1819b0ee26f86c6ce670e9b");
    }
}
