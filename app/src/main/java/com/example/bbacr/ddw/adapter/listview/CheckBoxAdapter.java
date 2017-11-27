package com.example.bbacr.ddw.adapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.bean.car.GetGoodsValueAddedServices;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class CheckBoxAdapter extends BaseAdapter {
    private List<GetGoodsValueAddedServices.DatasBean> stringList;
    private Context mContext;
    private List<String> checkBoxIDList;            //存储checkBox的值

    //get set
    public List<String> getCheckBoxIDList() {
        return checkBoxIDList;
    }
    public void setCheckBoxIDList(List<String> checkBoxIDList) {
        this.checkBoxIDList = checkBoxIDList;
    }
    public CheckBoxAdapter(List<GetGoodsValueAddedServices.DatasBean> stringList, Context mContext) {
        this.stringList = stringList;
        this.mContext = mContext;
        checkBoxIDList= new ArrayList<>();
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int position) {
        return stringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final TestViewHolder testViewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.specification_grid_two, null);
            testViewHolder = new TestViewHolder();

            testViewHolder.item_checkBox = (CheckBox) convertView.findViewById(R.id.specification);

            convertView.setTag(testViewHolder);
        } else {
            testViewHolder = (TestViewHolder) convertView.getTag();
        }

        //设置checkBox的值
        testViewHolder.item_checkBox.setText(stringList.get(position).getName());

        //获取复选框选中状态改变事件进行增删改
        testViewHolder.item_checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                /*
                * b=选中状态
                * if b = true 将值添加至checkBoxIDList
                * if b = false 将值从checkBoxIDList移除
                * */
                if (b) {
                    checkBoxIDList.add(stringList.get(position).getId()+"");
                } else {
                    checkBoxIDList.remove(stringList.get(position).getId()+"");
                }
            }
        });
        return convertView;
    }

    static class TestViewHolder {

        CheckBox item_checkBox;

    }

}


