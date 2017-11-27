package com.example.bbacr.ddw.home.view;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.HomeClassTypeGridAdapter;
import com.example.bbacr.ddw.adapter.RcvCommonAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.adapter.callback.IHomeClassTypeGridCallBack;
import com.example.bbacr.ddw.adapter.recycle.BaseRecyclerAdapter;
import com.example.bbacr.ddw.adapter.recycle.RecyclerViewHolder;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.ClassifcationBean;
import com.example.bbacr.ddw.bean.HomeBomBean;
import com.example.bbacr.ddw.bean.JsonHome;
import com.example.bbacr.ddw.bean.home.GoodSearch;
import com.example.bbacr.ddw.bean.home.HomeSlider;
import com.example.bbacr.ddw.bean.home.HomeTop;
import com.example.bbacr.ddw.classify.view.init.ClassDetailFragment;
import com.example.bbacr.ddw.classify.view.init.ClassMoreFragment;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.home.InviteCodeFragmentFragment;
import com.example.bbacr.ddw.home.search.SearchFragment;
import com.example.bbacr.ddw.home.shopdetail.MyShoppingDetailFragment;
import com.example.bbacr.ddw.mine.coupon.SelectCouponFragment;
import com.example.bbacr.ddw.mine.myorder.MyAllOrderFragment;
import com.example.bbacr.ddw.mine.myorder.OrderFragment;
import com.example.bbacr.ddw.mine.shareteam.ShareFragment;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.GlideImageLoader;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
/**
 * A simple {@link Fragment} subclass.
 * 推荐
 */
public class RecommendFragment extends BaseFragment {
    @Bind(R.id.LRecyclerView)
    LRecyclerView mLRecyclerView;
    private LRecyclerViewAdapter mAdapterBottom;
    private BaseRecyclerAdapter<ClassifcationBean> mAdapter;
    private BaseRecyclerAdapter<HomeTop.DatasBean> mAdapterBom;
    private RcvCommonAdapter<GoodSearch.DatasBean> mHomeBottomAdapter;
    private GridLayoutManager gridLayoutManager;
    private List<String> images = new ArrayList<>();
    //header
    private List<ClassifcationBean> classifcationBeanList = new ArrayList<>();
    private List<GoodSearch.DatasBean> mDatasFloorModel = new ArrayList<>();
    private List<HomeSlider.DatasBean> mDatasSlideImage = new ArrayList<>();
    private View mHeaderView;
    private Banner mBanner;
    private RecyclerView mRecyclerView,mRecyclerViewBom;
    private int classArray[] = {R.mipmap.order, R.mipmap.tgm, R.mipmap.xp, R.mipmap.qb};
    private String classTextArray[] = {"我的订单", "推广码", "分享经济", "全部"};
    public RecommendFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        mHeaderView = LayoutInflater.from(getContext()).inflate(R.layout.head_item, (ViewGroup)getActivity().findViewById(android.R.id.content), false);
        mBanner = (Banner) mHeaderView.findViewById(R.id.banner);
        mRecyclerView = (RecyclerView) mHeaderView.findViewById(R.id.lRecyclerView);
        mRecyclerViewBom = (RecyclerView) mHeaderView.findViewById(R.id.lRecyclerView_bom);
        mAdapter = new BaseRecyclerAdapter<ClassifcationBean>(getActivity(),null) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.classifcation_grid;
            }
            @Override
            public void bindData(RecyclerViewHolder holder, final int position, ClassifcationBean item) {
                holder.setText(R.id.tv_classifcation_text, item.getClassifcationName())
                        .setBackground(R.id.iv_classifcation_img, item.getClassifcationImg())
                .setClickListener(R.id.iv_classifcation_img, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (position) {
                            case 0:
                                ShowFragmentUtils.showFragment(getActivity(), OrderFragment.class, FragmentTag.ORDERFRAGMENT, null, true);
                                break;
                            case 1:
                                ShowFragmentUtils.showFragment(getActivity(), InviteCodeFragmentFragment.class, FragmentTag.INVITECODEFRAGMENTFRAGMENT, null, true);
                                break;
                            case 2:
                                ShowFragmentUtils.showFragment(getActivity(), ShareFragment.class,FragmentTag.SHAREFRAGMENT,null,true);
                                break;
                            case 3:
                                ShowFragmentUtils.showFragment(getActivity(), ClassDetailFragment.class,FragmentTag.CLASSDETAILFRAGMENT,null,true);
                                break;
                        }
                    }
                });
            }
        };
        gridLayoutManager = new GridLayoutManager(getActivity(),4);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapterBom = new BaseRecyclerAdapter<HomeTop.DatasBean>(getActivity(), null) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.home_grid_bom;
            }
            @Override
            public void bindData(RecyclerViewHolder holder, int position, HomeTop.DatasBean item) {
                switch (position) {
                    case 0:
                        holder.setText(R.id.title_bar, item.getName()).
                                setText(R.id.content,item.getShortName()).setGlideImageView(item.getImg(),R.id.icon).setTextColor(R.color.vote_2, R.id.title_bar);
                        break;
                    case 1:
                        holder.setText(R.id.title_bar, item.getName()).
                                setText(R.id.content,item.getShortName()).setGlideImageView(item.getImg(),R.id.icon).setTextColor(R.color.vote_9, R.id.title_bar);
                        break;
                    case 2:
                        holder.setText(R.id.title_bar, item.getName()).
                                setText(R.id.content,item.getShortName()).setGlideImageView(item.getImg(),R.id.icon).setTextColor(R.color._ff7a9a, R.id.title_bar);
                        break;
                    case 3:
                        holder.setText(R.id.title_bar, item.getName()).
                                setText(R.id.content,item.getShortName()).
                                setGlideImageView(item.getImg(),R.id.icon).setTextColor(R.color.btn_logout_normal, R.id.title_bar);
                        break;
                }
            }
        };
        gridLayoutManager = new GridLayoutManager(getActivity(),2);
        mRecyclerViewBom.setLayoutManager(gridLayoutManager);
        mRecyclerViewBom.setAdapter(mAdapterBom);
        mAdapterBom.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                switch (pos) {
                    case 0:
                        ShowFragmentUtils.showFragment(getActivity(), SelectCouponFragment.class,FragmentTag.SELECTCOUPONFRAGMENT,null,true);
                        break;
                    case 1:
                        ShowFragmentUtils.showFragment(getActivity(), ClassDetailFragment.class,FragmentTag.CLASSDETAILFRAGMENT,null,true);
                        break;
                    case 2:
                        ShowFragmentUtils.showFragment(getActivity(), ClassDetailFragment.class,FragmentTag.CLASSDETAILFRAGMENT,null,true);
                        break;
                    case 3:
                        ShowFragmentUtils.showFragment(getActivity(), ClassDetailFragment.class,FragmentTag.CLASSDETAILFRAGMENT,null,true);
                        break;
                }
            }
        });
        //商超推荐
        mHomeBottomAdapter = new RcvCommonAdapter<GoodSearch.DatasBean>(
                getActivity(),R.layout.item_home_bottom) {
            @Override
            public void setViewData(SuperViewHolder holder, GoodSearch.DatasBean item, int position) {
                holder.setGlideImageView(item.getMain_img(), R.id.iv_home_bottom_icon)
                        .setText(item.getName(), R.id.tv_home_bottom_name)
                        .setText("￥" + item.getMember_price(), R.id.tv_home_bottom_price)
                .setText(item.getCommentNum()+"人评价",R.id.evaluate).setText(item.getFootMarkNum()+"次浏览",R.id.consult);
            }
            @Override
            public void setListener(SuperViewHolder holder, View view) {
                view.setOnClickListener(holder);
                holder.setOnClickListener(R.id.iv_home_bottom_icon);
            }
            @Override
            public void onClickBack(SuperViewHolder holder, int position,View view) {
                PreferenceManager.instance().saveGoodsId(mDatasFloorModel.get(position).getGoods_id());
                mArguments.putString("goodsId", mDatasFloorModel.get(position).getGoods_id());
                ShowFragmentUtils.showFragment(getActivity(), MyShoppingDetailFragment.class, FragmentTag.MYSHOPPINGDETAILFRAGMENT, mArguments, true);
            }
        };
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mLRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapterBottom = new LRecyclerViewAdapter(mHomeBottomAdapter);
        mAdapterBottom.addHeaderView(mHeaderView);
        mLRecyclerView.setAdapter(mAdapterBottom);
        mLRecyclerView.setLoadMoreEnabled(false);
        initHomeClassTypeData();
    }
    @Override
    protected void setListener() {
        super.setListener();
        //下拉刷新
        mLRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                goodSearch();
                getListTop();
                getSlider();
                mLRecyclerView.refreshComplete(1000);
            }
        });
        getListTop();
        getSlider();
        goodSearch();
    }
    @Override
    protected void setData() {
        super.setData();
    }
    private void  initBannerData(){
        images.clear();
        for (HomeSlider.DatasBean img : mDatasSlideImage) {
            images.add(img.getImg());
        }
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                switch (position) {
                    case 0:
                        ShowFragmentUtils.showFragment(getActivity(), OrderFragment.class, FragmentTag.ORDERFRAGMENT, null, true);
                        break;
                    case 1:
                        ShowFragmentUtils.showFragment(getActivity(), InviteCodeFragmentFragment.class, FragmentTag.INVITECODEFRAGMENTFRAGMENT, null, true);
                        break;
                    case 2:
                        break;
                    case 3:
                        ShowFragmentUtils.showFragment(getActivity(), ClassDetailFragment.class,FragmentTag.CLASSDETAILFRAGMENT,null,true);
                        break;
                }
            }
        });
    }
    private  void initHomeClassTypeData(){
        for (int i = 0; i < classTextArray.length; i++) {
            ClassifcationBean classifcationBean = new ClassifcationBean();
            classifcationBean.setClassifcationImg(classArray[i]);
            classifcationBean.setClassifcationName(classTextArray[i]);
            classifcationBeanList.add(classifcationBean);
        }
        mAdapter.setDataList(classifcationBeanList);
    }
    @Override
    public void onClick(View v) {
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        OkHttpUtils.getInstance().cancelTag(this);
    }
    private void getListTop(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("indexCategoryId", "1");
        HttpHelper.getInstance().post(Url.entranceC, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("indexCategoryId"+response);
                final HomeTop homeTop = JsonUtils.parse(response, HomeTop.class);
                if (homeTop.getCode()==1) {
                    mAdapterBom.setDataList(homeTop.getDatas());
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtil.showShort(error_msg);
            }
        });
    }
    /**
     * 为你优选
     */
    private void goodSearch(){
        HashMap<String, String> hashMap = new HashMap<>();
        HttpHelper.getInstance().post(Url.goodSearch, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("goodSearch"+response);
                GoodSearch bean = JsonUtils.parse(response, GoodSearch.class);
                if (bean.getCode()==1) {
                    if (mDatasFloorModel!=null) {
                        mDatasFloorModel.clear();
                        mDatasFloorModel.addAll(bean.getDatas());
                    }
                    mHomeBottomAdapter.setDataList(bean.getDatas());
                } else if (bean.getCode()==0) {
                    ToastUtil.showShort(bean.getMsg());
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtil.showShort(error_msg);
            }
        });
    }
    private void getSlider(){
        startLoadingDialog("正在加载");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("typeValue", "1");
        HttpHelper.getInstance().post(Url.bannerC, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("bannerC"+response);
                closeLoadingDialog();
                HomeSlider homeSlider = JsonUtils.parse(response, HomeSlider.class);
                if (homeSlider.getCode()==1) {
                    if (mDatasSlideImage!=null) {
                        mDatasSlideImage.clear();
                    }
                    mDatasSlideImage.addAll(homeSlider.getDatas());
                    initBannerData();
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                closeLoadingDialog();
                ToastUtil.showShort(error_msg);
            }
        });
    }
}
