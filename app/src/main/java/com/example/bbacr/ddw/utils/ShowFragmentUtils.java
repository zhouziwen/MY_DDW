package com.example.bbacr.ddw.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.base.BaseFragment;

import java.util.List;

/**
 * Created by Hcw on 2016/9/7.
 * 显示Fragment的工具类
 */
public class ShowFragmentUtils {
    private ShowFragmentUtils() {

    }

    /**
     * @param activity :
     * @param who :
     * @param tag :
     * @param arguments :
     * @param isAddToBackStack :是否添加到回退栈中
     */
    public static void showFragment(
            FragmentActivity activity,
            Class<? extends BaseFragment> who,
            String tag,
            Bundle arguments,
            boolean isAddToBackStack) {
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        BaseFragment fragment = null;

        try {
            fragment = who.newInstance();
        } catch (Exception e) {
            Log.e("showFragment", e.getMessage());
        }

        if (fragment == null) {
            Log.e("showFragment", "fragment is Null !!!");
            return;
        }

        fragment.setArguments(arguments);
        //系统的动画 Fragment 跳转动画
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit, R.anim.fragment_pop_enter, R.anim.fragment_pop_exist);
        transaction.add(android.R.id.content, fragment, tag);

        if (isAddToBackStack) {
            transaction.addToBackStack(tag);
        }
        transaction.commit();
    }

    public static void popBackStack(FragmentActivity activity) {
        FragmentManager manager = activity.getSupportFragmentManager();
        manager.popBackStack();
    }

    /**获取当前Activity中正在显示的Fragment
     * @param activity :
     * @return :
     */
    public static BaseFragment getCurrentFragment(FragmentActivity activity) {
        FragmentManager manager = activity.getSupportFragmentManager();
        List<Fragment> fragments = manager.getFragments();
        int size = fragments.size();
        Fragment current = null;
        for (int i = size-1; i >=0 ; i--) {
            Fragment fragment = fragments.get(i);
            if (fragment == null) {
                continue;
            }
            current = fragment;
            break;
        }
        return (BaseFragment) current;
    }

    public static BaseFragment findFragmentByTag(FragmentActivity activity, String tag) {
        FragmentManager manager = activity.getSupportFragmentManager();
        return (BaseFragment) manager.findFragmentByTag(tag);
    }
}
