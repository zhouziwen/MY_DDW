package com.example.bbacr.ddw.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.bean.MenuItemEntity;
import com.example.bbacr.ddw.bean.home.GuessYouLiveGoods;
import com.gw.meituanturnpagelibrary.RVAdapter;
import com.gw.meituanturnpagelibrary.RecyclerViewHolder;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class DotViewPager extends FrameLayout implements ViewPager.OnPageChangeListener {
    private float density;
    private ViewPager mViewPager;
    private LinearLayout llDot;
    private RVAdapter<GuessYouLiveGoods.DatasBean> adapter;
    public DotViewPager(Context context) {
        this(context, null, 0);
    }
    public DotViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public DotViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        density = getResources().getDisplayMetrics().density;
        View mView = LayoutInflater.from(context).inflate(R.layout.dot_view_pager, this, true);
        mViewPager = (ViewPager) mView.findViewById(R.id.dotViewPager);
        mViewPager.addOnPageChangeListener(this);
        llDot = (LinearLayout) mView.findViewById(R.id.llDot);
    }
    public void setData(List<GuessYouLiveGoods.DatasBean> mList, int pageSize, int spanCount) {
        if (mList == null || mList.size() == 0) {
            return;
        }
        adapter = new RVAdapter<GuessYouLiveGoods.DatasBean>(getContext(),
                mList, pageSize, spanCount) {
            @Override
            protected int getLayoutId() {
                return R.layout.dot_view_pager_item;
            }
            @Override
            protected void onBindView(RecyclerViewHolder holder, int position, GuessYouLiveGoods.DatasBean data) {
                TextView tv = holder.getView(R.id.tv);
                GlideImageView imageView = holder.getView(R.id.iv_classifcation_img);
                imageView.loadImage(data.getMainImg(), R.mipmap.ic_launcher);
                tv.setText(data.getName());
            }
        };
        mViewPager.setAdapter(adapter);
        llDot.removeAllViews();
        int pageCount = ((mList.size() - 1) / pageSize + 1);
        FrameLayout.LayoutParams lp1 = (FrameLayout.LayoutParams) llDot.getLayoutParams();
        if (pageCount > 1) {
            lp1.setMargins(0, 0, 0, 0);
            for (int i = 0; i < pageCount; i++) {
                ImageView ivDot = new ImageView(getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins((int) (2.5 * density), (int) (10 * density), (int) (2.5 * density), (int) (10 * density));
                ivDot.setImageResource(R.mipmap.dot_blur);
                llDot.addView(ivDot, params);
            }
            ((ImageView) llDot.getChildAt(0)).setImageResource(R.mipmap.pot);
        } else {
            lp1.setMargins(0, (int) (15 * density), 0, 0);
        }
    }
    public RVAdapter<GuessYouLiveGoods.DatasBean> getAdapter() {
        return adapter;
    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < llDot.getChildCount(); i++) {
            ImageView iv = ((ImageView) llDot.getChildAt(i));
            if (i == position) {
                iv.setImageResource(R.mipmap.pot);
            } else {
                iv.setImageResource(R.mipmap.dot_blur);
            }
        }
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
