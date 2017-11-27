package com.example.bbacr.ddw.adapter.lrecycle;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.ListBaseAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.adapter.callback.ICarCallBack;
import com.example.bbacr.ddw.adapter.callback.IClassMoreCallBack;
import com.example.bbacr.ddw.adapter.callback.IContentCallBack;
import com.example.bbacr.ddw.adapter.recycle.BaseRecyclerAdapter;
import com.example.bbacr.ddw.adapter.recycle.RecyclerViewHolder;
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.bean.car.GetUserShoppingCart;
import com.example.bbacr.ddw.bean.classfy.TwoLevelCategory;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.StringUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.example.bbacr.ddw.widget.SwipeMenuView;
import com.sunfusheng.glideimageview.GlideImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class AdapterCar extends ListBaseAdapter<GetUserShoppingCart.DatasBean> {
    private List<GetUserShoppingCart.DatasBean> mDatas = new ArrayList<>();
    private GetUserShoppingCart.DatasBean mDatas1;
    private Context mContext;
    private ICarCallBack mCallBack;
    private static final int MYLIVE_MODE_CHECK = 0;
    int mEditMode = MYLIVE_MODE_CHECK;
    private int cartid;
    private boolean isIsSelect;
    private List<BaseRecyclerAdapter<GetUserShoppingCart.DatasBean.ValueAddedServicesListBean>> pAdapterList = new ArrayList<>();
    //    private IOrderCallBack mIOrderCallBack;
    public AdapterCar(Context context) {
        super(context);
    }
    public AdapterCar(Context context, List<GetUserShoppingCart.DatasBean> datas,ICarCallBack iContentCallBack) {
        super(context);
        mDatas = datas;
        mContext = context;
        mCallBack = iContentCallBack;
    }
    @Override
    public void setDataList(final List<GetUserShoppingCart.DatasBean> list) {
        super.setDataList(list);
        for (int i = 0; i <list.size() ; i++) {
            mDatas1 = list.get(i);
            isIsSelect=list.get(i).isIsSelect();
            cartid=list.get(i).getCartid();
            BaseRecyclerAdapter<GetUserShoppingCart.DatasBean.ValueAddedServicesListBean> adapter = new BaseRecyclerAdapter<GetUserShoppingCart.DatasBean.ValueAddedServicesListBean>(mContext, list.get(i).getValueAddedServicesList()) {
                @Override
                public int getItemLayoutId(int viewType) {
                    return R.layout.car_recycle_list;
                }
                @Override
                public void bindData(final RecyclerViewHolder holder, final int position, final GetUserShoppingCart.DatasBean.ValueAddedServicesListBean item) {
                    holder.setText(R.id.add_service, "【"+item.getName()).setText(R.id.add_service_money,item.getPrice()+"】");
                    holder.setImageCheck(R.id.id_cb_select_child, item.isIsSelect());
                    holder.setClickListener(R.id.id_cb_select_child, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (item.isIsSelect()) {
                                mCallBack.ClickBomFalse(isIsSelect,cartid,mDatas1,position);
                            } else {
                                mCallBack.ClickBomTure(isIsSelect, cartid, mDatas1, position);
                            }
                        }
                    });
                }
            };
            pAdapterList.add(adapter);
        }
    }
    @Override
    public void onClickBack(SuperViewHolder holder, int position, View view) {
    }
    @Override
    public int getLayoutId() {
        return  R.layout.car_child_layout;
    }
    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        final GetUserShoppingCart.DatasBean item =  getDataList().get(position);
        final CheckBox id_cb_select_child = holder.getView(R.id.id_cb_select_child);
        TextView tv_items_child = holder.getView(R.id.tv_items_child);/*名字*/
        TextView tv_items_child_desc = holder.getView(R.id.tv_items_child_desc);/*规格*/
        TextView id_tv_discount_price = holder.getView(R.id.id_tv_discount_price);/*价格*/
        final TextView tv_item_number_comm_detail = holder.getView(R.id.tv_item_number_comm_detail);/*数量*/
        TextView tv_item_add_comm_detail = holder.getView(R.id.tv_item_add_comm_detail);/*加*/
        TextView tv_item_minus_comm_detail = holder.getView(R.id.tv_item_minus_comm_detail);/*减*/
        Button collect = holder.getView(R.id.collect);
        Button delete = holder.getView(R.id.delete);
        GlideImageView id_iv_logo = holder.getView(R.id.id_iv_logo);
        if (TextUtils.isEmpty(item.getGoodSpecificationName())) {
            tv_items_child_desc.setVisibility(View.GONE);
        } else {
            tv_items_child_desc.setVisibility(View.VISIBLE);
        }
        if (item.isIsSelect()) {
            id_cb_select_child.setChecked(true);
        } else {
            id_cb_select_child.setChecked(false);
        }
        id_cb_select_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id_cb_select_child.isChecked()) {
                    mCallBack.ClickTure(position );
                } else {
                    mCallBack.ClickFalse(position);
                }
            }
        });
        /*规格*/
        tv_items_child_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.specification(item, position);
            }
        });
        if (item.getValueAddedServicesList().isEmpty() || item.getValueAddedServicesList() == null) {
            tv_item_minus_comm_detail.setOnClickListener(new View.OnClickListener() {/*减*/
                @Override
                public void onClick(View v) {
                    int goods_nmb = item.getCount();
                    if (goods_nmb < 2) {
                        ToastUtil.showShort("已是最低购买量");
                    } else {
                        item.setCount(goods_nmb - 1);
                        tv_item_number_comm_detail.setText(goods_nmb - 1 + "");
                        mCallBack.Count(item);
                    }
                }
            });
            tv_item_add_comm_detail.setOnClickListener(new View.OnClickListener() {/*加*/
                @Override
                public void onClick(View v) {
                    int goods_nmb = item.getCount();
                    item.setCount(goods_nmb + 1);
                    tv_item_number_comm_detail.setText(goods_nmb + 1 + "");
                    mCallBack.Count(item);
                }
            });
        } else if (item.isIsSelect() && !item.getValueAddedServicesList().isEmpty()) {
            tv_item_minus_comm_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showShort("此状态下不可选择");
                }
            });
            tv_item_add_comm_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showShort("此状态下不可选择");
                }
            });
        } else {
            tv_item_minus_comm_detail.setOnClickListener(new View.OnClickListener() {/*减*/
                @Override
                public void onClick(View v) {
                    int goods_nmb = item.getCount();
                    if (goods_nmb < 2) {
                        ToastUtil.showShort("已是最低购买量");
                    } else {
                        item.setCount(goods_nmb - 1);
                        tv_item_number_comm_detail.setText(goods_nmb - 1 + "");
                        mCallBack.Count(item);
                    }
                }
            });
            tv_item_add_comm_detail.setOnClickListener(new View.OnClickListener() {/*加*/
                @Override
                public void onClick(View v) {
                    int goods_nmb = item.getCount();
                    item.setCount(goods_nmb + 1);
                    tv_item_number_comm_detail.setText(goods_nmb + 1 + "");
                    mCallBack.Count(item);
                }
            });
        }
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.collect(item);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.delete(item,position);
            }
        });
        id_iv_logo.loadImage(item.getMainImg(), R.mipmap.ic_launcher);
        tv_item_number_comm_detail.setText(item.getCount()+"");
        tv_items_child.setText(item.getGoodsName());
        tv_items_child_desc.setText(item.getGoodSpecificationName());
        id_tv_discount_price.setText("￥:"+item.getMemberPrice());
        ((SwipeMenuView)holder.getView(R.id.SwipeMenuView)).setIos(false).setLeftSwipe(true);
        RecyclerView recyclerview = holder.getView(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setAdapter(pAdapterList.get(position));
    }
    public interface OnItemClickListener {
        void onItemClickListener(int pos,List<GetUserShoppingCart.DatasBean> myLiveList);
    }
    public void setEditMode(int editMode) {
        mEditMode = editMode;
        notifyDataSetChanged();
    }
}
