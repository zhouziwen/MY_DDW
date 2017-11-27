package com.example.bbacr.ddw.car;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.RcvCommonAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.ShoppingCarBean;
import com.example.bbacr.ddw.bean.car.GetUserShoppingCart;
import com.example.bbacr.ddw.bean.my.GetDefault;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.example.bbacr.ddw.widget.CommonDialog;
import com.example.bbacr.ddw.widget.SwipeMenuView;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ShoppingCarFragment extends BaseFragment {
    @Bind(R.id.LRecyclerView)
    LRecyclerView mLRecyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private RcvCommonAdapter<ShoppingCarBean> mAdapter;
    private List<ShoppingCarBean> mList=new ArrayList<>();
    private List<ShoppingCarBean> mSelectList=new ArrayList<>();
    @Bind(R.id.id_cb_select_all)
    CheckBox mIdCbSelectAll;
    @Bind(R.id.id_tv_totalPrice)
    TextView mIdTvTotalPrice;
    @Bind(R.id.tvPostPrice)
    TextView mTvPostPrice;
    @Bind(R.id.id_tv_totalCount_jiesuan)
    TextView mIdTvTotalCountJiesuan;
    @Bind(R.id.id_tv_delete_all)
    TextView mIdTvDeleteAll;
    public ShoppingCarFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_car, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        mAdapter = new RcvCommonAdapter<ShoppingCarBean>(getContext(),R.layout.child_layout) {
            @Override
            public void setViewData(SuperViewHolder holder, ShoppingCarBean item, int position) {

                //判断是否已经选中
                if (mSelectList.contains(item)) {
//                    holder.getView(R.id.id_cb_select_child);
                    holder.setImgRes(R.mipmap.ic_checked, R.id.id_cb_select_child);
                } else {
                    holder.setImgRes(R.mipmap.ic_uncheck, R.id.id_cb_select_child);
                }
                updatePrice();
                DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
                float result = (Integer.valueOf(item.getCount())) * Float.valueOf(item.getShoppingPrice());
                //设置名字和监听
                holder.setText("￥" + decimalFormat.format(result), R.id.id_tv_discount_price);
                holder.setText(item.getShoppingName(), R.id.tv_items_child)
                        .setText(item.getShoppingSpecification(), R.id.tv_items_child_desc)
                        .setText(item.getShoppingPrice(), R.id.id_tv_discount_price)
                        .setText(String.valueOf(item.getCount()), R.id.tv_item_number_comm_detail);
                ((SwipeMenuView)holder.itemView).setIos(false).setLeftSwipe(true);
            }
            @Override
            public void setListener(SuperViewHolder holder, View view) {
                holder.setOnClickListener(R.id.tv_item_minus_comm_detail);
                holder.setOnClickListener(R.id.tv_item_add_comm_detail);
                holder.setOnClickListener(R.id.collect);
                holder.setOnClickListener(R.id.delete);
                holder.setOnClickListener(R.id.id_cb_select_child);}
            @Override
            public void onClickBack(SuperViewHolder holder, int position, View view) {
                final ShoppingCarBean item = getDataList().get(position);
                switch (view.getId()) {
                    case R.id.tv_item_minus_comm_detail:
                        int goods_nmb = item.getCount();
                        if (goods_nmb<2){
                            Toast.makeText(getActivity(),"已是最低购买量",Toast.LENGTH_SHORT).show();
                        }else {
                            item.setCount(goods_nmb-1);
                            holder.setText(String.valueOf(goods_nmb-1), R.id.tv_item_number_comm_detail);
                            DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
                            float result = (goods_nmb - 1) * Float.valueOf(item.getShoppingPrice());
                            holder.setText("￥" + decimalFormat.format(result), R.id.id_tv_discount_price);
                        }
                        updatePrice();
                        break;
                    case R.id.tv_item_add_comm_detail:/*加*/
                        goods_nmb = item.getCount();
                        item.setCount(goods_nmb+1);
                        holder.setText(String.valueOf(goods_nmb+1), R.id.tv_item_number_comm_detail);
                        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
                        float result = (goods_nmb + 1) * Float.valueOf(item.getShoppingPrice());
                        holder.setText("￥" + decimalFormat.format(result), R.id.id_tv_discount_price);
                        updatePrice();
                        break;
                    case R.id.collect:/*收藏*/
                        ToastUtil.showShort("已添加到收藏");
                        break;
                    case R.id.delete:/*删除*/
                        new CommonDialog.Builder(getActivity()).setTitle("提示")
                                .setCanceledOnTouchOutside(false)
                                .setMessage("确认从购物车删除此商品？")
                                .setPositiveButton("确认", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
//                                        requestDeleteCart(item.getSkuId(),item);
                                    }
                                }).setNegativeButton("取消", null).show();
                        break;
                    case R.id.id_cb_select_child:/*选择*/
                        if (mSelectList.contains(item)) {
                            mSelectList.remove(item);
                            holder.setImgRes(R.mipmap.ic_uncheck, R.id.id_cb_select_child);
                        } else {
                            mSelectList.add(item);
                            holder.setImgRes(R.mipmap.ic_checked, R.id.id_cb_select_child);
                        }
                        updatePrice();
                        updateSelectedAll();
                    break;
                }
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mLRecyclerView.setPullRefreshEnabled(false);
        mLRecyclerView.setLoadMoreEnabled(false);
    }
    @Override
    protected void setListener() {
        super.setListener();
        mIdTvTotalCountJiesuan.setOnClickListener(this);
        mIdCbSelectAll.setOnClickListener(this);
    }
    @Override
    protected void setData() {
        super.setData();
        for (int i = 0; i <5 ; i++) {
            mList.add(new ShoppingCarBean("商品"+i,"商品规格"+i,"123"+i,i+1));
        }
        mAdapter.setDataList(mList);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_cb_select_all:
                selectedAll();
                break;
            case R.id.id_tv_totalCount_jiesuan:
//                ShowFragmentUtils.showFragment(getActivity(),ConfirmOrderFragment.class, FragmentTag.CONFIRMORDERFRAGMENT,null,true);
        }
    }
    /**
     * 全选的点击事件
     */
    private void selectedAll() {
        if (mSelectList.size() != mList.size()) {
            mSelectList.clear();
            mSelectList.addAll(mList);
            mLRecyclerViewAdapter.notifyDataSetChanged();
            mIdCbSelectAll.setChecked(true);
        } else {
            mSelectList.clear();
            mLRecyclerViewAdapter.notifyDataSetChanged();
            mIdCbSelectAll.setChecked(false);
        }
    }
    /**
     * 更新选中商品的价格
     */
    private void updatePrice() {
        float selectedPrice = 0;
        for (ShoppingCarBean bean : mSelectList) {
            selectedPrice += Float.valueOf(bean.getShoppingPrice()) *
                    Integer.valueOf(bean.getCount());
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        mIdTvTotalPrice.setText("合计：￥" + decimalFormat.format(selectedPrice));
        mTvPostPrice.setText("总共："+mSelectList.size()+"件");
    }
    /**
     * 当全选时更新全选图标
     */
    private void updateSelectedAll() {
        if (mSelectList.size() == mList.size()) {//全选状态
            mIdCbSelectAll.setChecked(true);
//            Drawable leftDrawable = getResources().getDrawable(R.mipmap.cb_pay_ok);
//            leftDrawable.setBounds(0, 0, leftDrawable.getMinimumWidth(), leftDrawable.getMinimumHeight());
//            mTvSelectedAll.setCompoundDrawables(leftDrawable, null, null, null);
        } else {
            mIdCbSelectAll.setChecked(false);
//            Drawable leftDrawable = getResources().getDrawable(R.mipmap.cb_pay);
//            leftDrawable.setBounds(0, 0, leftDrawable.getMinimumWidth(), leftDrawable.getMinimumHeight());
//            mTvSelectedAll.setCompoundDrawables(leftDrawable, null, null, null);
        }
    }
    private void getUserShoppingCart(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        HttpHelper.getInstance().post(Url.getUserShoppingCart, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("getUserShoppingCart"+response);
                GetUserShoppingCart bean = JsonUtils.parse(response, GetUserShoppingCart.class);
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
