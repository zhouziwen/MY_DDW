package com.example.bbacr.ddw.bean.list;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class ListBean<T> implements Serializable{
    private List<T> mList;

    public ListBean(List<T> list) {
        mList = list;
    }

    public List<T> getList() {

        return mList;
    }

    public void setList(List<T> list) {
        mList = list;
    }
}
