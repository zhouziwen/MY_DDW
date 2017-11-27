package com.example.bbacr.ddw.home.dialogfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.callback.ISpecificationCallBack;
import com.example.bbacr.ddw.adapter.listview.CommonAdapter;
import com.example.bbacr.ddw.adapter.listview.ViewHolder;
import com.example.bbacr.ddw.adapter.lrecycle.AdaterSpecification;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.ClassifcationBean;
import com.example.bbacr.ddw.bean.ClassifyBean;
import com.example.bbacr.ddw.bean.car.GetSpecification;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventBean;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.example.bbacr.ddw.widget.MyListView;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecificationFragment extends BaseFragment {
    @Bind(R.id.LRecyclerView)
    LRecyclerView mLRecyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private AdaterSpecification mAdaterSpecificationTop;
    //    @Bind(R.id.lRecyclerView_bom)
    MyListView mMyListView;
    private View mFootView;
    private int goods_nmb = 1;
    private String propertiesid;
    private TextView mSubtract, mAdd,mNum;
    private CommonAdapter<ClassifcationBean> mAdapter;
    private List<GetSpecification.DatasBean> mDatasOrder = new ArrayList<>();
    private List<ClassifyBean.DatasBean.ZuoDanListBean.GoodsOrderListBean> mList = new ArrayList<>();
    private List<ClassifcationBean> mOrderListBeen = new ArrayList<>();
    public SpecificationFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_specification, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        mFootView = LayoutInflater.from(getContext()).inflate(R.layout.footview_item, (ViewGroup) getActivity().findViewById(android.R.id.content), false);
        mMyListView = (MyListView) mFootView.findViewById(R.id.lRecyclerView_bom);
        mSubtract = (TextView) mFootView.findViewById(R.id.tv_item_minus_comm_detail);
        mAdd = (TextView) mFootView.findViewById(R.id.tv_item_add_comm_detail);
        mNum = (TextView) mFootView.findViewById(R.id.tv_item_number_comm_detail);
        mAdapter = new CommonAdapter<ClassifcationBean>(getContext(), null, R.layout.specification_grid_two) {
            @Override
            public void onClickBack(View v, int position, ViewHolder holder) {
            }
            @Override
            public void onBack(String name) {
            }
            @Override
            public void setViewData(ViewHolder holder, ClassifcationBean item, int position) {
                holder.setText("数据", R.id.specification);
            }
        };
        mMyListView.setAdapter(mAdapter);
        mAdaterSpecificationTop = new AdaterSpecification(getContext(), null, new ISpecificationCallBack() {
            @Override
            public void click(List<GetSpecification.DatasBean> list, GetSpecification.DatasBean.DataBean item) {
                for (int i = 0; i <list.size() ; i++) {
                    propertiesid=list.get(i).getId()+":"+item.getId();
                }
                getGoodSpecification("1",list.get(0).getId()+":"+item.getId());
            }
        });
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdaterSpecificationTop);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mLRecyclerView.setPullRefreshEnabled(false);
        mLRecyclerView.setLoadMoreEnabled(false);
        mLRecyclerViewAdapter.addFooterView(mFootView);
    }
    @Override
    protected void setListener() {
        super.setListener();
        mSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goods_nmb--;
                if (goods_nmb<1){
                    Toast.makeText(getActivity(),"已是最低购买量",Toast.LENGTH_SHORT).show();
                }else {
                    mNum.setText(String.valueOf(goods_nmb));
                }
            }
        });
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goods_nmb++;
                mNum.setText(String.valueOf(goods_nmb));
            }
        });
    }
    @Override
    protected void setData() {
        super.setData();
      getSpecification("1");
        for (int i = 0; i < 4; i++) {
            mOrderListBeen.add(new ClassifcationBean("数据"));
        }
        mAdapter.update(mOrderListBeen);
    }
    /**
     * @param goodsId
     * 规格
     */
    private void getSpecification(String goodsId){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("goodid",goodsId);
        HttpHelper.getInstance().post(Url.getSpecification, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                GetSpecification bean = JsonUtils.parse(response, GetSpecification.class);
                if (bean.getCode()==1) {
                    mDatasOrder.addAll(bean.getDatas());
                }
                mAdaterSpecificationTop.setDataList(mDatasOrder);
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }
    /**
     * @param goodsId
     * @param propertiesid
     * 规格详情
     */
    private void getGoodSpecification(String goodsId,String propertiesid){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("goodid",goodsId);
        hashMap.put("propertiesid",propertiesid);
        HttpHelper.getInstance().post(Url.getGoodSpecification, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
