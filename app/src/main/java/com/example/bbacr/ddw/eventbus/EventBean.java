package com.example.bbacr.ddw.eventbus;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class EventBean {
    String msg;
    String list;

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public EventBean() {
    }

    public EventBean(String msg) {
        this.msg = msg;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
