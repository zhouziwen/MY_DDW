package com.example.bbacr.ddw.adapter.callback;

import com.example.bbacr.ddw.bean.car.GetSpecification;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public interface ISpecificationCallBack {
    void click(  List<GetSpecification.DatasBean> list, GetSpecification.DatasBean.DataBean item);

}
