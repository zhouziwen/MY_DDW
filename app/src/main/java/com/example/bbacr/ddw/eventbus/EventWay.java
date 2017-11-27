package com.example.bbacr.ddw.eventbus;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class EventWay {
    String msg;

    public EventWay(String msg, String way) {
        this.msg = msg;
        this.way = way;
    }

    String way;

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
