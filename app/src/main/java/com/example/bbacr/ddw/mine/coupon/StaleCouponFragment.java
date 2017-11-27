package com.example.bbacr.ddw.mine.coupon;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.adapter.RcvCommonAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.my.MyCard;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * A simple {@link Fragment} subclass.
 * 已过期的优惠券
 */
public class StaleCouponFragment extends BaseFragment {
    @Bind(R.id.lRecyclerView)
    LRecyclerView mLRecyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private RcvCommonAdapter<MyCard.DatasBean.DataBean> mAdapter;
    private List<MyCard.DatasBean.DataBean> mList = new ArrayList<>();
    private int mPage;
    public StaleCouponFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stale_coupon, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        mAdapter = new RcvCommonAdapter<MyCard.DatasBean.DataBean>(getContext(),R.layout.my_coupon_delay_item) {
            @Override
            public void setViewData(SuperViewHolder holder, MyCard.DatasBean.DataBean item, int position) {
                holder.setText(item.getCardsType(), R.id.mold).
                        setText(item.getName(), R.id.shop_name).
                        setText(item.getBeginTime() + item.getEndUseTime(), R.id.time1)
                        .setText(item.getMoney(),R.id.money);
            }
            @Override
            public void setListener(SuperViewHolder holder, View view) {
            }
            @Override
            public void onClickBack(SuperViewHolder holder, int position, View view) {
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerDecoration divider = new DividerDecoration.Builder(getContext())
                .setHeight(R.dimen.a_5dp)
                .setColorResource(R.color.gray_background)
                .build();
        mLRecyclerView.addItemDecoration(divider);
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mLRecyclerView.setPullRefreshEnabled(true);
        mLRecyclerView.setLoadMoreEnabled(true);
    }
    @Override
    protected void setListener() {
        super.setListener();
        mLRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchCards1(1, 2);
                mLRecyclerView.refreshComplete(1000);
            }
        });
        mLRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage++;
                searchCards(mPage, 2);
                mLRecyclerView.refreshComplete(1000);
            }
        });
    }

    @Override
    protected void setData() {
        super.setData();
        searchCards(mPage,2);
    }

    @Override
    public void onClick(View v) {

    }
    /**
     * 领取优惠券
     */
    private void searchCards(int page,int cardsStateValue){
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("page", String.valueOf(page));
        hashMap.put("cardsStateValue", String.valueOf(cardsStateValue));
        HttpHelper.getInstance().post(Url.MyCard, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                closeLoadingDialog();
                MyCard bean = JsonUtils.parse(response, MyCard.class);
                if (bean.getCode() == 1) {
                    mList.addAll(bean.getDatas().getData());
                    mAdapter.setDataList(mList);
                } else if (bean.getCode() == -1) {
                    ToastUtil.showShort(bean.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class, null);
                } else {
                    ToastUtil.showShort(bean.getMsg());
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtil.showShort(error_msg);
                closeLoadingDialog();
            }
        });
    }
    /**
     * 领取优惠券
     */
    private void searchCards1(int page,int cardsStateValue){
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("page", String.valueOf(page));
        hashMap.put("cardsStateValue", String.valueOf(cardsStateValue));
        HttpHelper.getInstance().post(Url.MyCard, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                closeLoadingDialog();
                MyCard bean = JsonUtils.parse(response, MyCard.class);
                if (bean.getCode() == 1) {
                    if (mList!=null) {
                        mList.clear();
                        mList.addAll(bean.getDatas().getData());
                    }
                    mAdapter.setDataList(bean.getDatas().getData());
                } else if (bean.getCode() == -1) {
                    ToastUtil.showShort(bean.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class, null);
                } else {
                    ToastUtil.showShort(bean.getMsg());
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtil.showShort(error_msg);
                closeLoadingDialog();
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
