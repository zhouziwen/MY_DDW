package com.example.bbacr.ddw.adapter.lrecycle;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.ListBaseAdapter;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.example.bbacr.ddw.adapter.callback.IContentCallBack;
import com.example.bbacr.ddw.adapter.recycle.BaseRecyclerAdapter;
import com.example.bbacr.ddw.adapter.recycle.RecyclerViewHolder;
import com.example.bbacr.ddw.bean.ClassifyBean;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class AdaterSpecificationBom extends ListBaseAdapter<ClassifyBean.DatasBean.ZuoDanListBean> {
    private List<ClassifyBean.DatasBean.ZuoDanListBean> mDatas = new ArrayList<>();
    private Context mContext;
    private RecyclerView recyclerview;
    private IContentCallBack mCallBack;
    private List<BaseRecyclerAdapter<ClassifyBean.DatasBean.ZuoDanListBean.GoodsOrderListBean>> pAdapterList = new ArrayList<>();

    //    private IOrderCallBack mIOrderCallBack;
    public AdaterSpecificationBom(Context context) {
        super(context);
    }
    public AdaterSpecificationBom(Context context, List<ClassifyBean.DatasBean.ZuoDanListBean> datas,IContentCallBack iContentCallBack) {
        super(context);
        mDatas = datas;
        mContext = context;
        mCallBack = iContentCallBack;
    }
    @Override
    public void setDataList(List<ClassifyBean.DatasBean.ZuoDanListBean> list) {
        for (int i = 0; i <list.size() ; i++) {
            BaseRecyclerAdapter<ClassifyBean.DatasBean.ZuoDanListBean.GoodsOrderListBean> adapter = new BaseRecyclerAdapter<ClassifyBean.DatasBean.ZuoDanListBean.GoodsOrderListBean>(mContext, list.get(i).getGoodsOrderList()) {
                @Override
                public int getItemLayoutId(int viewType) {
                    return R.layout.specification_grid_two;
                }
                @Override
                public void bindData(final RecyclerViewHolder holder, final int position, final ClassifyBean.DatasBean.ZuoDanListBean.GoodsOrderListBean item) {
                    holder.setCheck(R.id.specification, item.getGoodsName()).setBackgroupDrawable(mContext.getDrawable(R.drawable.def_specification),R.id.specification)
                            .setTextColor(mContext.getColor(R.color.line_color),R.id.specification).setClickListener(R.id.specification, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            holder.setCheck(R.id.specification, item.getGoodsName()).setBackgroupDrawable(mContext.getDrawable(R.drawable.def_specification_check), R.id.specification)
                                    .setTextColor(mContext.getColor(R.color.red), R.id.specification);
                        }
                    });
                    holder.setClickListener(R.id.look, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            mCallBack.back(item);
                        }
                    });
                }
            };
            pAdapterList.add(adapter);

        }
        super.setDataList(list);

    }
    @Override
    public void onClickBack(SuperViewHolder holder, int position, View view) {
    }
    @Override
    public int getLayoutId() {
        return  R.layout.specification_lrecycle_list_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final ClassifyBean.DatasBean.ZuoDanListBean item =  getDataList().get(position);
        TextView textView = holder.getView(R.id.name);
        textView.setText(item.getShopName());
        recyclerview = holder.getView(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setAdapter(pAdapterList.get(position));
    }
}
