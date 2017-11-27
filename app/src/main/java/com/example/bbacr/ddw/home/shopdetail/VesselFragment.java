package com.example.bbacr.ddw.home.shopdetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.classify.view.init.ShoppingDetailFragment;
import com.example.bbacr.ddw.customview.DragLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * A simple {@link Fragment} subclass.
 * 商品详情的容器
 */
public class VesselFragment extends BaseFragment {

    @Bind(R.id.activity_product_details_first)
    FrameLayout mActivityProductDetailsFirst;
    @Bind(R.id.activity_product_details_second)
    FrameLayout mActivityProductDetailsSecond;
    @Bind(R.id.draglayout)
    DragLayout mDraglayout;
    public VesselFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vessel, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        try {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_product_details_first, new ShoppingDetailFragment()).add(R.id.activity_product_details_second, new DetailsBottomFragment())
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DragLayout.ShowNextPageNotifier nextIntf = new DragLayout.ShowNextPageNotifier() {
            @Override
            public void onDragNext() {
                //    mFragmentBottom.initView();
            }
        };
        mDraglayout.setNextPageListener(nextIntf);
    }

    @Override
    protected void setListener() {
        super.setListener();
    }

    @Override
    protected void setData() {
        super.setData();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
