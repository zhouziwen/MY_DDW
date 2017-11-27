package com.example.bbacr.ddw.classify.view.init;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.callback.IClassMoreCallBack;
import com.example.bbacr.ddw.adapter.callback.OnPopWinDisMisBack;
import com.example.bbacr.ddw.adapter.listview.CommonAdapter;
import com.example.bbacr.ddw.adapter.listview.ViewHolder;
import com.example.bbacr.ddw.adapter.lrecycle.AdapterShoppingDetail;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.bean.ClassifyBean;
import com.example.bbacr.ddw.bean.JsonAddress;
import com.example.bbacr.ddw.bean.MenuItemEntity;
import com.example.bbacr.ddw.bean.detail.ShoppingDetailBean;
import com.example.bbacr.ddw.bean.home.GetPriceRecord;
import com.example.bbacr.ddw.bean.home.GuessYouLiveGoods;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.IEvent;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventBean;
import com.example.bbacr.ddw.eventbus.EventList;
import com.example.bbacr.ddw.home.dialogfragment.ChartsDialogFragment;
import com.example.bbacr.ddw.home.dialogfragment.SpecificationFragmentDialog;

import com.example.bbacr.ddw.home.shopdetail.MyShoppingDetailFragment;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.CommonUtils;
import com.example.bbacr.ddw.utils.GlideImageLoader;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.example.bbacr.ddw.widget.CityPickerDialog;
import com.example.bbacr.ddw.widget.CustomPopupWindow;
import com.example.bbacr.ddw.widget.DotViewPager;
import com.example.bbacr.ddw.widget.ExecutorFactory;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.reflect.TypeToken;
import com.gw.meituanturnpagelibrary.RVAdapter;
import com.gw.meituanturnpagelibrary.RecyclerViewHolder;
import com.lixs.charts.LineChartView;
import com.youth.banner.Banner;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 商品详情顶部
 */
public class ShoppingDetailFragment extends BaseFragment {
    @Bind(R.id.evaluate)
    TextView mEvaluate;
    @Bind(R.id.viewPager)
    DotViewPager mViewPager;
    @Bind(R.id.lRecyclerView)
    LRecyclerView mLRecyclerView;
    @Bind(R.id.price_chart)
    TextView mPriceChart;
    @Bind(R.id.container)
    TagFlowLayout mContainer;
    @Bind(R.id.layout)
    LinearLayout mLayout;
    @Bind(R.id.banner)
    Banner mBanner;
    @Bind(R.id.phone_detail)
    TextView mPhoneDetail;
    @Bind(R.id.phone_txt)
    TextView mPhoneTxt;
    @Bind(R.id.phone_money)
    TextView mPhoneMoney;
    private TagAdapter<ShoppingDetailBean.DatasBean.GoodsServiceBean> mAdapter;
    private CommonAdapter<ShoppingDetailBean.DatasBean.GoodsServiceBean> mCommonAdapter;
    private List<ShoppingDetailBean.DatasBean.GoodsServiceBean> mStrings = new ArrayList<>();
    private List<String> mString = new ArrayList<>();
    private List<Double> mDoubles = new ArrayList<>();
    @Bind(R.id.stages)
    LinearLayout mStages;
    @Bind(R.id.select_specification)
    LinearLayout mSelectSpecification;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private AdapterShoppingDetail mAdapterComment;
    List<GuessYouLiveGoods.DatasBean> titles = new ArrayList<>();
    private List<ShoppingDetailBean.DatasBean.CommentsListBean> mDatasOrder = new ArrayList<>();
    private List<ClassifyBean.DatasBean.ZuoDanListBean.GoodsOrderListBean> mList = new ArrayList<>();
    private CustomPopupWindow mPopupWindow;
    private List<String> images = new ArrayList<>();
    private int mLeftPosition = 0;
    public ShoppingDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAdapter = new TagAdapter<ShoppingDetailBean.DatasBean.GoodsServiceBean>(mStrings) {
            @Override
            public View getView(FlowLayout parent, int position, ShoppingDetailBean.DatasBean.GoodsServiceBean s) {
                final LayoutInflater mInflater = LayoutInflater.from(getActivity());
                TextView tv = (TextView) mInflater.inflate(R.layout.shopping_flow_layout_item,
                        mContainer, false);
                tv.setText(s.getName());
                return tv;
            }
        };
        mContainer.setAdapter(mAdapter);
        mContainer.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                showPop(mStrings);
                return true;
            }
        });
        mAdapterComment = new AdapterShoppingDetail(getContext(), null, new IClassMoreCallBack() {
            @Override
            public void Click(int i) {

            }
        });
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapterComment);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerDecoration divider = new DividerDecoration.Builder(getContext())
                .setHeight(R.dimen.a_1dp)
                .setColorResource(R.color.gray_background)
                .build();
        mLRecyclerView.addItemDecoration(divider);
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mLRecyclerView.setPullRefreshEnabled(false);
        mLRecyclerView.setLoadMoreEnabled(false);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mPriceChart.setOnClickListener(this);
        mStages.setOnClickListener(this);
        mSelectSpecification.setOnClickListener(this);
    }

    @Override
    protected void setData() {
        super.setData();
//        ddmGoodsC("1");
//        addFootMark("1");
        guessYouLiveGoods();
        getPriceRecord(PreferenceManager.instance().getGoodsId(),"");
        int resId = R.drawable.umeng_socialize_qzone;
//        for (int i = 0; i < list.size(); i++) {
//            titles.add(new MenuItemEntity(resId, list.get(i)));
//        }

//        for (int i = 0; i < 5; i++) {
//            mList.add(new ClassifyBean.DatasBean.ZuoDanListBean.GoodsOrderListBean("苹果" + i));
//        }
    }
private void guessYouLiveGoods(){
    HashMap<String, String> hashMap = new HashMap<>();
    HttpHelper.getInstance().post(Url.guessYouLiveGoods, hashMap, new RawResponseHandler() {
        @Override
        public void onSuccess(int statusCode, String response) {
            GuessYouLiveGoods goods = JsonUtils.parse(response, GuessYouLiveGoods.class);
            if (goods.getCode()==1) {
                titles.addAll(goods.getDatas());
                mViewPager.setData(titles, 6, 3);
                mViewPager.getAdapter().setOnItemClickListener(new RVAdapter.OnItemClickListener<GuessYouLiveGoods.DatasBean>() {
                    @Override
                    public void onItemClick(RecyclerViewHolder holder, int position, GuessYouLiveGoods.DatasBean data) {
//                        mArguments.putString("goodsId", String.valueOf(data.getId()));
//                        ShowFragmentUtils.showFragment(getActivity(), MyShoppingDetailFragment.class,FragmentTag.MYSHOPPINGDETAILFRAGMENT,mArguments,true);
//                Toast.makeText(getActivity(), "position:" + position + "   title:" + data.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        @Override
        public void onFailure(int statusCode, String error_msg) {
            ToastUtil.showShort(error_msg);
        }
    });
}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.price_chart:
                if (mDoubles!=null&&!mDoubles.isEmpty()&&mString!=null&&!mString.isEmpty()) {
                    showPricePop(mDoubles,mString);
                }
                break;
            case R.id.stages:
//                ChartsDialogFragment chartsDialogFragment = new ChartsDialogFragment();
//                chartsDialogFragment.show(getChildFragmentManager(), "");
                List<String> mList = new ArrayList<>();
                mList.add("3期");
                mList.add("6期");
                mList.add("9期");
                mList.add("12期");
                showPopStage(mList);
                break;
            case R.id.select_specification:
//                SpecificationFragmentDialog dialog = new SpecificationFragmentDialog();
//                dialog.show(getChildFragmentManager(), "");
                EventBus.getDefault().post(new EventList("pop"));
                break;
        }
    }
    /**
     * @param strings
     * 增值服务的pop
     */
    private void showPop(final List<ShoppingDetailBean.DatasBean.GoodsServiceBean> strings) {
        mPopupWindow = new CustomPopupWindow(
                getContext(), R.layout.shopping_detail_pop,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT, new OnPopWinDisMisBack() {
            @Override
            public void onPopWindowDismiss() {
            }
        }) {
            @Override
            public void setData(View view) {
                ListView listView = (ListView) view.findViewById(R.id.list_view);
                setAdapterPop(listView, strings);
                ImageView bg = (ImageView) view.findViewById(R.id.action_list_bgView);
                ImageView bg1 = (ImageView) view.findViewById(R.id.back_img);
                bg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismissWindow();
                    }
                });
                bg1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismissWindow();
                    }
                });
            }

        };
        mPopupWindow.showAsDownWindow(mLayout);
    }

    /**
     * 分期付
     */
    private void showPopStage(final List<String>string){
        mPopupWindow = new CustomPopupWindow(
                getContext(), R.layout.shopping_detail_pop,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT, new OnPopWinDisMisBack() {
            @Override
            public void onPopWindowDismiss() {
            }
        }) {
            @Override
            public void setData(View view) {
                ListView listView = (ListView) view.findViewById(R.id.list_view);
//                setAdapterPop(listView, string);
                listView.setAdapter(new CommonAdapter<String>(getContext(),string,R.layout.car_recycle_list) {
                    @Override
                    public void setViewData(ViewHolder holder, String item, int position) {
                        holder.getView(R.id.add_service_money).setVisibility(View.GONE);
                        holder.setText(item, R.id.add_service);
                        if (position==mLeftPosition) {
                            holder.setImageRes(R.mipmap.ic_checked, R.id.id_cb_select_child);
                        }
                    }

                    @Override
                    protected void setEvent(ViewHolder holder, View convertView, int position) {
                        super.setEvent(holder, convertView, position);
                        holder.setOnclickListener(R.id.id_cb_select_child);
                    }

                    @Override
                    public void onClickBack(View v, int position, ViewHolder holder) {
                        switch (v.getId()) {
                            case R.id.id_cb_select_child:
                                mLeftPosition = position;
                                notifyDataSetChanged();
                                break;
                        }
                    }

                    @Override
                    public void onBack(String name) {

                    }
                });
                ImageView bg = (ImageView) view.findViewById(R.id.action_list_bgView);
                ImageView bg1 = (ImageView) view.findViewById(R.id.back_img);
                bg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismissWindow();
                    }
                });
                bg1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismissWindow();
                    }
                });
            }
        };
//        mPopupWindow.showAsDownWindow(mLayout);
    }
    /**
     * 价格走势
     */
private void showPricePop(final List<Double>data, final List<String>list){
    mPopupWindow = new CustomPopupWindow(getContext(), R.layout.fragment_price_chart,
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT, new OnPopWinDisMisBack() {
        @Override
        public void onPopWindowDismiss() {

        }
    }) {
        @Override
        public void setData(View view) {
            LineChartView mLineView = (LineChartView) view.findViewById(R.id.lineView);
            mLineView.setBarTitle(mPhoneDetail.getText().toString());
            mLineView.setDatas(data,list);
            ImageView bg = (ImageView) view.findViewById(R.id.action_list_bgView);
            ImageView bg1 = (ImageView) view.findViewById(R.id.back_img);
            bg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopupWindow.dismissWindow();
                }
            });
            bg1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopupWindow.dismissWindow();
                }
            });
        }
    };
    mPopupWindow.showAsDownWindow(mLayout);
}
    /**
     * @param listView
     * @param strings
     * 增值服务的adapter
     */
    private void setAdapterPop(ListView listView, List<ShoppingDetailBean.DatasBean.GoodsServiceBean> strings) {
        mCommonAdapter = new CommonAdapter<ShoppingDetailBean.DatasBean.GoodsServiceBean>(getContext(), strings, R.layout.list_view_item_service) {
            @Override
            public void setViewData(ViewHolder holder, ShoppingDetailBean.DatasBean.GoodsServiceBean item, int position) {
                holder.setText(item.getName(), R.id.title).setGlideImageView(item.getImgUrl(),R.id.image).setText(item.getDetails(),R.id.content);
            }

            @Override
            public void onClickBack(View v, int position, ViewHolder holder) {

            }

            @Override
            public void onBack(String name) {

            }
        };
        listView.setAdapter(mCommonAdapter);
    }

    /**
     * @param bean
     * 商品详情
     */
    @Subscribe
    public void eventBus(ShoppingDetailBean bean) {
        if (bean!=null) {
            mPhoneTxt.setText(bean.getDatas().getGoodsExplain());
            mPhoneDetail.setText(bean.getDatas().getName());
            mPhoneMoney.setText(bean.getDatas().getMemberPrice());
            mDatasOrder.addAll(bean.getDatas().getCommentsList());
            mStrings.addAll(bean.getDatas().getGoodsService());
            String d[] = bean.getDatas().getMainImg().split(",");
            for (int i = 0; i <d.length ; i++) {
                images.add(d[i]);/*轮播图集合*/
            }
            //设置图片加载器
            mBanner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            mBanner.setImages(images);
            //banner设置方法全部调用完毕时最后调用
            mBanner.start();
        }
        mAdapterComment.setDataList(mDatasOrder);
        mAdapter.notifyDataChanged();
    }
    /**
     * @param goodsId
     * @param specificationId
     * 价格走势
     */
    private void getPriceRecord(String goodsId,String specificationId){
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("goodsId", goodsId);
        hashMap.put("specificationId",specificationId);
        HttpHelper.getInstance().post(Url.getPriceRecord, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                closeLoadingDialog();
                GetPriceRecord bean = JsonUtils.parse(response, GetPriceRecord.class);
                if (bean.getCode() == 1) {
                    mString.addAll(bean.getDatas().getDate());
                    for (int i = 0; i < bean.getDatas().getPrice().size(); i++) {
                        mDoubles.add(Double.valueOf(bean.getDatas().getPrice().get(i)));
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                closeLoadingDialog();
                ToastUtil.showShort(error_msg);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
