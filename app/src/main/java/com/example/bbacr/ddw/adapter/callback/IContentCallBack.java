package com.example.bbacr.ddw.adapter.callback;

import com.example.bbacr.ddw.bean.classfy.TwoLevelCategory;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public interface IContentCallBack {
    void back(TwoLevelCategory.DatasBean.SecondCategoryBean.ThirdCategoryBean item);

    void more(TwoLevelCategory.DatasBean.SecondCategoryBean item);
}
