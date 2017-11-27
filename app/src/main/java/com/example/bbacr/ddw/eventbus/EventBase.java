package com.example.bbacr.ddw.eventbus;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class EventBase {
    String msg;
    public EventBase() {
    }
    public EventBase(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
