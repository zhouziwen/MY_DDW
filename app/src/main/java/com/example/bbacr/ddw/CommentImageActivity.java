package com.example.bbacr.ddw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.bbacr.ddw.base.BaseActivity;
import com.example.bbacr.ddw.home.evaluate.LargeImageAdapter;
import com.example.bbacr.ddw.home.evaluate.MultiTouchViewPager;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CommentImageActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @Bind(R.id.ll_back)
    AutoLinearLayout mLlBack;
    @Bind(R.id.tv_current_index)
    TextView mTvCurrentIndex;
    @Bind(R.id.ll_remove)
    AutoLinearLayout mLlRemove;
    @Bind(R.id.vp_large_image)
    MultiTouchViewPager mVpLargeImage;
    private LargeImageAdapter adapter;
    private List<String> imgUrls;
    private int currentIndex;

    private final int RESULT_CODE_LARGE_IMAGE = 1;
    @Override
    public int getLayoutId() {
        return R.layout.activity_comment_image;
    }

    @Override
    public void initView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            currentIndex = bundle.getInt(EvaluateActivity.KEY_CURRENT_INDEX);
            imgUrls = bundle.getStringArrayList(EvaluateActivity.KEY_IMAGE_LIST);
            mVpLargeImage.setAdapter(adapter = new LargeImageAdapter(this, imgUrls));//设置晒单图显示
            mVpLargeImage.setOffscreenPageLimit(imgUrls.size());//预加载的数量为图片集合的长度
            mVpLargeImage.setCurrentItem(currentIndex);
            mTvCurrentIndex.setText(currentIndex + 1 + " / " + imgUrls.size());}
    }
    @Override
    public void initListener() {
        mLlBack.setOnClickListener(this);
        mLlRemove.setOnClickListener(this);
        mVpLargeImage.addOnPageChangeListener(this);
    }
    @Override
    public void initData() {
    }
    @Override
    public void onBackPressed() {
        //回到晒单页时候把处理后的图片集合返回过去
        Intent intent = new Intent();
        intent.putStringArrayListExtra(EvaluateActivity.KEY_IMAGE_LIST, (ArrayList<String>) adapter.getData());
        setResult(RESULT_CODE_LARGE_IMAGE, intent);
        super.onBackPressed();
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
    @Override
    public void onPageSelected(int position) {
        currentIndex = position;
        mTvCurrentIndex.setText(++position + " / " + imgUrls.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                onBackPressed();
                break;

            case R.id.ll_remove:
                //删除当前晒单图
                if (imgUrls.size() == 1) {
                    //删除最后一张时直接回到晒单评论页
                    imgUrls.clear();
                    onBackPressed();
                } else {
                    //删除指定索引的图片
                    removeImage(currentIndex);
                }
                break;

            default:
                break;
        }
    }
    /**
     * 删除指定索引的晒单图
     * @param index
     */
    private void removeImage(int index) {
        imgUrls.remove(index);
        setImageTitle(index);
        mVpLargeImage.removeAllViews();//删除viewpager所有的子View
        mVpLargeImage.setAdapter(adapter = new LargeImageAdapter(this, imgUrls));//重新设置适配器数据显示
        mVpLargeImage.setCurrentItem(index == imgUrls.size() - 1 ? ++index : --index);//显示指定位置
        adapter.notifyDataSetChanged();
    }

    /**
     * 设置标题显示
     * <p>比如3 / 4
     * @param index
     */
    private void setImageTitle(int index) {
        //删除图片路径后
        if (index == 0 || imgUrls.size() == 1) {
            // 索引 == 0 || 图片集合只剩一张图
            // 就把索引值固定为1
            index = 1;
        } else if (index == imgUrls.size() - 1) {
            // 当前索引 == 图片集合的最后一张
            // 就不做任何处理
        } else {
            //否则就把索引+1便于显示
            index += 1;
        }
        mTvCurrentIndex.setText(index + " / " + imgUrls.size());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
