package com.example.bbacr.ddw.classify.view.init;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.RcvCommonAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.adapter.callback.IClassMoreCallBack;
import com.example.bbacr.ddw.adapter.callback.OnPopWinDisMisBack;
import com.example.bbacr.ddw.adapter.listview.CommonAdapter;
import com.example.bbacr.ddw.adapter.listview.ViewHolder;
import com.example.bbacr.ddw.adapter.lrecycle.AdapterPopupWindow;
import com.example.bbacr.ddw.adapter.recycle.BaseRecyclerAdapter;
import com.example.bbacr.ddw.adapter.recycle.RecyclerViewHolder;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.ClassifcationBean;
import com.example.bbacr.ddw.bean.ClassifyBean;
import com.example.bbacr.ddw.bean.PopBean;
import com.example.bbacr.ddw.bean.home.GoodSearch;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.home.search.SearchFragment;
import com.example.bbacr.ddw.home.shopdetail.MyShoppingDetailFragment;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.example.bbacr.ddw.widget.CustomPopupWindow;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 分类详情界面
 */
public class ClassDetailFragment extends BaseFragment {
    @Bind(R.id.title_back)
    TextView mTitleBack;
    @Bind(R.id.query)
    EditText mQuery;/*搜索框*/
    @Bind(R.id.select_item)
    CheckBox mSelectItem;
    @Bind(R.id.list_detail_top)
    RelativeLayout mListDetailTop;
    @Bind(R.id.homeListDetail_sale)
    TextView mHomeListDetailSale;
    @Bind(R.id.homeListDetail_price)
    TextView mHomeListDetailPrice;
    @Bind(R.id.homeListDetail_location)
    TextView mHomeListDetailLocation;
    @Bind(R.id.homeListDetail_screen)
    TextView mHomeListDetailScreen;
    @Bind(R.id.classDetail_one)
    TextView mClassDetailOne;
    @Bind(R.id.classDetail_two)
    TextView mClassDetailTwo;
    @Bind(R.id.classDetail_three)
    TextView mClassDetailThree;
    @Bind(R.id.classDetail_four)
    TextView mClassDetailFour;
    @Bind(R.id.lRecyclerView)
    LRecyclerView mLRecyclerView;
    @Bind(R.id.classDetail_view)
    View mClassDetailView;
    @Bind(R.id.classDetail_view_2)
    View mClassDetailView2;
    @Bind(R.id.classDetail_view_3)
    View mClassDetailView3;
    private boolean mBoolean;
    private int mPage = 1;
    /*列表数据*/
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private RcvCommonAdapter<GoodSearch.DatasBean> mAdapter;
    private List<GoodSearch.DatasBean> mList = new ArrayList<>();
    /*上面的popwindow*/
    private CustomPopupWindow mPopupWindow;
    private CommonAdapter<String> mCommonAdapter;
    /*下面的popwindow*/
    private BaseRecyclerAdapter<PopBean> mBaseRecyclerAdapter;
    List<PopBean> list4 = new ArrayList<>();
    /*右边的adapter*/
    private AdapterPopupWindow mAdapterPopupWindow;
    private LRecyclerViewAdapter mRecyclerViewAdapter;
    private List<ClassifyBean.DatasBean.ZuoDanListBean> mDatasOrder = new ArrayList<>();
    private List<ClassifyBean.DatasBean.ZuoDanListBean.GoodsOrderListBean> mlist = new ArrayList<>();
    private int composite=1;
    private int sales=1;
    private int price=1;
    public ClassDetailFragment() {

        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_class_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAdapter = new RcvCommonAdapter<GoodSearch.DatasBean>(getContext(), R.layout.class_item_lrecycle_glide) {
            @Override
            public void setViewData(SuperViewHolder holder, GoodSearch.DatasBean item, int position) {
                holder.setGlideImageView(item.getMain_img(), R.id.glide_icon)
                        .setText(item.getName(), R.id.shopping_name)
                        .setText("￥" + item.getMember_price(), R.id.tv_home_bottom_price)
                        .setText(item.getCommentNum()+"人评价",R.id.evaluate).setText(item.getFootMarkNum()+"次浏览",R.id.consult);
            }
            @Override
            public void setListener(SuperViewHolder holder, View view) {
                view.setOnClickListener(holder);
            }
            @Override
            public void onClickBack(SuperViewHolder holder, int position, View view) {
                PreferenceManager.instance().saveGoodsId(mList.get(position).getGoods_id());
                mArguments.putString("goodsId", mList.get(position).getGoods_id());
                ShowFragmentUtils.showFragment(getActivity(), MyShoppingDetailFragment.class, FragmentTag.MYSHOPPINGDETAILFRAGMENT, mArguments, true);
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mLRecyclerView.setPullRefreshEnabled(false);
        mLRecyclerView.setLoadMoreEnabled(false);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mTitleBack.setOnClickListener(this);
        mSelectItem.setOnClickListener(this);
        mHomeListDetailSale.setOnClickListener(this);
        mHomeListDetailPrice.setOnClickListener(this);
        mHomeListDetailLocation.setOnClickListener(this);
        mHomeListDetailScreen.setOnClickListener(this);
        mClassDetailOne.setOnClickListener(this);
        mClassDetailTwo.setOnClickListener(this);
        mClassDetailThree.setOnClickListener(this);
        mClassDetailFour.setOnClickListener(this);
        mQuery.setOnClickListener(this);
        if (TextUtils.isEmpty(mArguments.getString("keyWords"))) {
            goodSearch("", mPage, "1", "1", "1");
        } else {
            mQuery.setText(mArguments.getString("keyWords"));
            goodSearch(mArguments.getString("keyWords"),mPage, "1", "1", "1");
        }
    }

    @Override
    protected void setData() {
        super.setData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.query:
                ShowFragmentUtils.showFragment(getActivity(), SearchFragment.class,FragmentTag.SEARCHFRAGMENT,null,true);
                break;
            case R.id.title_back:
                popSelf();
                break;
            case R.id.select_item:
                chooseLayout();
                break;
            case R.id.homeListDetail_sale:
                if (composite==1) {
                    if (TextUtils.isEmpty(mArguments.getString("keyWords"))) {
                        goodSearch("", mPage, "0", "1", "1");
                    } else {
                        goodSearch(mArguments.getString("keyWords"),mPage, "0", "1", "1");
                    }
                    composite = 2;
                    changeTextColor(mHomeListDetailSale, true,0);
                    changeTextColor(mHomeListDetailPrice, false,0);
                    changeTextColor(mHomeListDetailLocation, false,0);
                } else if (composite==2) {
                    if (TextUtils.isEmpty(mArguments.getString("keyWords"))) {
                        goodSearch("", mPage, "1", "1", "1");
                    } else {
                        goodSearch(mArguments.getString("keyWords"),mPage, "1", "1", "1");
                    }
                    composite = 1;
                    changeTextColor(mHomeListDetailSale, true,1);
                    changeTextColor(mHomeListDetailPrice, false,1);
                    changeTextColor(mHomeListDetailLocation, false,1);
                }
                break;
            case R.id.homeListDetail_price:
                if (sales==1) {
                    if (TextUtils.isEmpty(mArguments.getString("keyWords"))) {
                        goodSearch("", mPage, "1", "0", "1");
                    } else {
                        goodSearch(mArguments.getString("keyWords"),mPage, "1", "0", "1");
                    }
                    sales = 2;
                    changeTextColor(mHomeListDetailPrice, true,0);
                    changeTextColor(mHomeListDetailSale, false,0);
                    changeTextColor(mHomeListDetailLocation, false,0);
                } else if (sales==2) {
                    if (TextUtils.isEmpty(mArguments.getString("keyWords"))) {
                        goodSearch("", mPage, "1", "1", "1");
                    } else {
                        goodSearch(mArguments.getString("keyWords"),mPage, "1", "1", "1");
                    }
                    sales = 1;
                    changeTextColor(mHomeListDetailPrice, true,1);
                    changeTextColor(mHomeListDetailSale, false,1);
                    changeTextColor(mHomeListDetailLocation, false,1);
                }
//                List<String> list1 = new ArrayList<>();
//                list1.add("销量从高到低");
//                list1.add("销量从低到高");
//                showPop(list1, 2);

                break;
            case R.id.homeListDetail_location:
                if (price==1) {
                    if (TextUtils.isEmpty(mArguments.getString("keyWords"))) {
                        goodSearch("", mPage, "1", "1", "0");
                    } else {
                        goodSearch(mArguments.getString("keyWords"),mPage, "1", "1", "0");
                    }
                    price = 2;
                    changeTextColor(mHomeListDetailLocation, true,0);
                    changeTextColor(mHomeListDetailPrice, false,0);
                    changeTextColor(mHomeListDetailSale, false,0);
                } else if (price==2) {
                    if (TextUtils.isEmpty(mArguments.getString("keyWords"))) {
                        goodSearch("", mPage, "1", "1", "1");
                    } else {
                        goodSearch(mArguments.getString("keyWords"),mPage, "1", "1", "1");
                    }
                    price = 1;
                    changeTextColor(mHomeListDetailLocation, true,1);
                    changeTextColor(mHomeListDetailPrice, false,1);
                    changeTextColor(mHomeListDetailSale, false,1);
                }
//                List<String> list2 = new ArrayList<>();
//                list2.add("价格从高到低");
//                list2.add("价格从低到高");
//                showPop(list2, 3);

                break;
            case R.id.homeListDetail_screen:
                showPopRight();
                changeTextColor2(mHomeListDetailScreen, true);
                break;
            case R.id.classDetail_one:
                showBom(list4);
                break;
            case R.id.classDetail_two:
                showBom(list4);
                break;
            case R.id.classDetail_three:
                showBom(list4);
                break;
            case R.id.classDetail_four:
                showBom(list4);
                break;
        }
    }

    /**
     * 列表的adapter
     */
    private void setAdapter() {
        mAdapter = new RcvCommonAdapter<GoodSearch.DatasBean>(getContext(), R.layout.class_item_lrecycle_glide) {
            @Override
            public void setViewData(SuperViewHolder holder, GoodSearch.DatasBean item, int position) {
                holder.setGlideImageView(item.getMain_img(), R.id.glide_icon)
                        .setText(item.getName(), R.id.shopping_name)
                        .setText("￥" + item.getMember_price(), R.id.tv_home_bottom_price)
                        .setText(item.getCommentNum()+"人评价",R.id.evaluate).setText(item.getFootMarkNum()+"次浏览",R.id.consult);

            }
            @Override
            public void setListener(SuperViewHolder holder, View view) {
                view.setOnClickListener(holder);
            }
            @Override
            public void onClickBack(SuperViewHolder holder, int position, View view) {

                PreferenceManager.instance().saveGoodsId(mList.get(position).getGoods_id());
                mArguments.putString("goodsId", mList.get(position).getGoods_id());
                ShowFragmentUtils.showFragment(getActivity(), MyShoppingDetailFragment.class, FragmentTag.MYSHOPPINGDETAILFRAGMENT, mArguments, true);
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mLRecyclerView.setPullRefreshEnabled(false);
        mLRecyclerView.setLoadMoreEnabled(false);
    }

    /**
     * 九宫格列表
     */
    private void setAdapterTwo() {
        mAdapter = new RcvCommonAdapter<GoodSearch.DatasBean>(getContext(), R.layout.item_home_bottom) {
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
            }

            @Override
            public void onClickBack(SuperViewHolder holder, int position, View view) {
                PreferenceManager.instance().saveGoodsId(mList.get(position).getGoods_id());
                mArguments.putString("goodsId", mList.get(position).getGoods_id());
                ShowFragmentUtils.showFragment(getActivity(), MyShoppingDetailFragment.class, FragmentTag.MYSHOPPINGDETAILFRAGMENT, mArguments, true);
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        mLRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mLRecyclerView.setPullRefreshEnabled(false);
        mLRecyclerView.setLoadMoreEnabled(false);
    }

    private void chooseLayout() {
        if (mSelectItem.isChecked()) {
            mSelectItem.setBackgroundResource(R.mipmap.hengbiao);
            setAdapterTwo();
            mAdapter.setDataList(mList);
        } else {
            mSelectItem.setBackgroundResource(R.mipmap.liebiao);
            setAdapter();
            mAdapter.setDataList(mList);
        }
    }
    /**
     * @param strings
     * @param who     上面的pop
     */
//    private void showPop(final List<String> strings, final int who) {
//        LogUtils.d("ddfffff");
//        mPopupWindow = new CustomPopupWindow(
//                getContext(),
//                R.layout.action_list,
//                WindowManager.LayoutParams.MATCH_PARENT,
//                WindowManager.LayoutParams.MATCH_PARENT,
//                new OnPopWinDisMisBack() {
//                    @Override
//                    public void onPopWindowDismiss() {
//                        changeTextColor(mHomeListDetailSale, false);
//                        changeTextColor(mHomeListDetailPrice, false);
//                        changeTextColor(mHomeListDetailLocation, false);
//                        changeTextColor(mHomeListDetailScreen, false);
//                    }}) {
//            @Override
//            public void setData(View view) {
//                ListView listView = (ListView) view.findViewById(R.id.list_view);
//                setPopAdapter(listView, strings, who);
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        switch (position) {
//                            case 0:
//                                goodSearch(mArguments.getString("keyWords"),mPage,"1","1","1");
//                                break;
//                            case 1:
//                                goodSearch(mArguments.getString("keyWords"),mPage,"0","0","0");
//                                break;
//                        }
//                    }
//                });
//                ImageView bg = (ImageView) view.findViewById(R.id.action_list_bgView);
//                DismissListener(bg, who);
//            }
//        };
//        mPopupWindow.showAsParentView(mClassDetailView);
//    }
    /**
     * @param strings 下面的pop
     */
    private void showBom(final List<PopBean> strings) {
        mPopupWindow = new CustomPopupWindow(
                getContext(),
                R.layout.popup,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                new OnPopWinDisMisBack() {
                    @Override
                    public void onPopWindowDismiss() {

                    }
                }) {
            @Override
            public void setData(View view) {
                RecyclerView recyclerview = (RecyclerView) view.findViewById(R.id.grid);
                TextView mSure = (TextView) view.findViewById(R.id.ok);
                TextView mReset = (TextView) view.findViewById(R.id.reset);/*重置*/
                ImageView mChose = (ImageView) view.findViewById(R.id.popup_view);
                setBomAdapter(recyclerview, strings);
                click(mSure, mReset, mChose);
            }
        };
        mPopupWindow.showAsParentView(mClassDetailView2);

    }

    /**
     *右边那个大的pop
     */
    private void showPopRight() {
        mPopupWindow = new CustomPopupWindow(
                getContext(),
                R.layout.popupwindow_screen,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                new OnPopWinDisMisBack() {
                    @Override
                    public void onPopWindowDismiss() {
                        changeTextColor2(mHomeListDetailScreen, false);
                    }
                }) {
            @Override
            public void setData(View view) {
                LRecyclerView lrecyclerview = (LRecyclerView) view.findViewById(R.id.lRecyclerView);
                setPopRightAdapter(lrecyclerview);
                mAdapterPopupWindow.setDataList(mDatasOrder);
            }
        };
        mPopupWindow.showAsXYPoint(mClassDetailView3, 0, 0);

    }

    private void click(TextView sure, TextView reset, ImageView chose) {
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismissWindow();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mPopupWindow.dismissWindow();
                ToastUtil.showShort("我被点击啦");
                mBaseRecyclerAdapter.notifyDataSetChanged();

            }
        });
        chose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismissWindow();
            }
        });
    }

    /**
     * @param imageView 上面的pop点击
     * @param who
     */
//    private void DismissListener(ImageView imageView, final int who) {
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (who) {
//                    case 1:
//                        mPopupWindow.dismissWindow();
//                        changeTextColor(mHomeListDetailSale, false);
//                        break;
//                    case 2:
//                        mPopupWindow.dismissWindow();
//                        changeTextColor(mHomeListDetailPrice, false);
//                        break;
//                    case 3:
//                        mPopupWindow.dismissWindow();
//                        changeTextColor(mHomeListDetailLocation, false);
//                        break;
//                    case 4:
//                        mPopupWindow.dismissWindow();
//                        changeTextColor(mHomeListDetailScreen, false);
//                        break;
//                }
//            }
//        });
//    }

    /**
     * @param listView 上面pop的adapter
     * @param strings
     * @param who
     */
    private void setPopAdapter(ListView listView, final List<String> strings, final int who) {
        mCommonAdapter = new CommonAdapter<String>(getContext(), strings, R.layout.action_list_item) {
            @Override
            public void setViewData(ViewHolder holder, String item, int position) {
                holder.setText(item, R.id.action_list_item_tv);

            }

            @Override
            protected void setEvent(ViewHolder holder, View convertView, int position) {
                holder.setOnclickListener(R.id.relative);
            }

            @Override
            public void onClickBack(View v, int position, ViewHolder holder) {
                switch (who) {
                    case 1:
                        mPopupWindow.dismissWindow();
                        break;
                    case 2:
                        mPopupWindow.dismissWindow();
                        break;
                    case 3:
                        mPopupWindow.dismissWindow();
                        break;
                    case 4:
                        mPopupWindow.dismissWindow();
                        break;
                }

            }

            @Override
            public void onBack(String name) {

            }
        };

        listView.setAdapter(mCommonAdapter);
    }

    /**
     * @param recyclerview 下面pop的adapter
     * @param strings
     */
    private void setBomAdapter(RecyclerView recyclerview, final List<PopBean> strings) {
        mBaseRecyclerAdapter = new BaseRecyclerAdapter<PopBean>(getContext(), strings) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.popwindow_bom_item;
            }

            @Override
            public void bindData(RecyclerViewHolder holder, final int position, final PopBean item) {
                holder.setCheck(R.id.tv1, item.getContent()).setClickListener(R.id.tv1, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showShort("我被点击啦" + position);
                        mClassDetailOne.setText(item.getContent());
                    }
                });
            }
        };
        recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerview.setAdapter(mBaseRecyclerAdapter);
    }

    /**
     * @param lrecyclerview 右边的adapter
     */
private void  setPopRightAdapter(LRecyclerView lrecyclerview){
    mAdapterPopupWindow = new AdapterPopupWindow(getContext(), null, new IClassMoreCallBack() {
        @Override
        public void Click(int i) {

        }
    });
    mRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapterPopupWindow);
    lrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
    lrecyclerview.setAdapter(mRecyclerViewAdapter);
    lrecyclerview.setPullRefreshEnabled(false);
    lrecyclerview.setLoadMoreEnabled(false);

}
    //改变top文字的颜色
    private void changeTextColor(TextView textView, boolean isRed,int i) {
        if (isRed) {
            if (i == 0) {
                textView.setTextColor(getResources().getColor(R.color.red));
                setTvDrawable(textView, R.mipmap.price_up_pressed);
            } else if (i==1) {
                textView.setTextColor(getResources().getColor(R.color.red));
                setTvDrawable(textView, R.mipmap.price_down_normal);
            }
        } else {
            textView.setTextColor(getResources().getColor(R.color.line_color));
            setTvDrawable(textView, R.mipmap.price_down_gray);
        }
    }
    private void changeTextColor2(TextView textView, boolean isRed) {
        if (isRed) {
            textView.setTextColor(getResources().getColor(R.color.red));
            setTvDrawable(textView, R.mipmap.shaixuan_press);
        } else {
            textView.setTextColor(getResources().getColor(R.color.line_color));
            setTvDrawable(textView, R.mipmap.shaixuan);
        }
    }

    public void setTvDrawable(TextView tv, int image) {
        Drawable d = getResources().getDrawable(image);
        d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
        tv.setCompoundDrawables(null, null, d, null);
    }
    /**
     * 为你优选
     */
    private void goodSearch(String keywords, int page,String isDate,String isSales,String isPrice){
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("keywords", keywords);
        hashMap.put("page", String.valueOf(page));
        hashMap.put("isDate", isDate);
        hashMap.put("isSales", isSales);
        hashMap.put("isPrice", isPrice);
        HttpHelper.getInstance().post(Url.goodSearch, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("goodSearch"+response);
                closeLoadingDialog();
                GoodSearch bean = JsonUtils.parse(response, GoodSearch.class);
                if (bean.getCode()==1) {
                    if (mList!=null) {
                        mList.clear();
                        mList.addAll(bean.getDatas());
                    }
                    mAdapter.setDataList(bean.getDatas());
                } else if (bean.getCode()==0) {
                    ToastUtil.showShort(bean.getMsg());
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
