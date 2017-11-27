package com.example.bbacr.ddw.classify.view.init;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.callback.IClassMoreCallBack;
import com.example.bbacr.ddw.adapter.lrecycle.AdapterMoreClass;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.ClassifyBean;
import com.example.bbacr.ddw.bean.classfy.ThreeLevelCategory;
import com.example.bbacr.ddw.bean.classfy.TwoLevelCategory;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.home.dialogfragment.SpecificationFragmentDialog;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.home.dialogfragment.ChartsDialogFragment;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * A simple {@link Fragment} subclass.
 * 更多品牌
 */
public class ClassMoreFragment extends BaseFragment {
    @Bind(R.id.lRecyclerView)
    LRecyclerView mLRecyclerView;
    private TextView mBack;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private AdapterMoreClass mAdapterMoreClass;
    private List<ThreeLevelCategory.DatasBean> mDatasOrder = new ArrayList<>();
    public ClassMoreFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_class_more, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
        mAdapterMoreClass = new AdapterMoreClass(getContext(), null, new IClassMoreCallBack() {

            @Override
            public void Click(int i) {
                ShowFragmentUtils.showFragment(getActivity(), ClassDetailFragment.class, FragmentTag.CLASSDETAILFRAGMENT,null,true);
            }
        });
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapterMoreClass);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mLRecyclerView.setPullRefreshEnabled(false);
        mLRecyclerView.setLoadMoreEnabled(false);
    }
    @Override
    protected void setListener() {
        super.setListener();
        mBack.setOnClickListener(this);
    }
    @Override
    protected void setData() {
        super.setData();
        threeLevelCategory(mArguments.getString("parentCategoryId"));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.app_title_back:
                ShowFragmentUtils.popBackStack(getActivity());
                break;
        }
    }
    /**
     * @param parentCategoryId
     * 更多分类
     */
    private void threeLevelCategory(String parentCategoryId) {
        startLoadingDialog("正在加载");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("parentCategoryId", parentCategoryId);
        HttpHelper.getInstance().post(Url.threeLevelCategory, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("twoLevelCategory" + response);
                closeLoadingDialog();
                ThreeLevelCategory category = JsonUtils.parse(response, ThreeLevelCategory.class);
                if (category.getCode() == 1) {
                    mDatasOrder.addAll(category.getDatas());
                }
                mAdapterMoreClass.setDataList(mDatasOrder);
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
