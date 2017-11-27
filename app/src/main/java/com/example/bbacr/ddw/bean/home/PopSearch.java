package com.example.bbacr.ddw.bean.home;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class PopSearch {

    /**
     * msg : success
     * code : 1
     * datas : ["诺基亚","苹果","索尼","摩托罗拉","尼康","富士","华为","佳能","三星"]
     */

    private String msg;
    private int code;
    private List<String> datas;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<String> getDatas() {
        return datas;
    }

    public void setDatas(List<String> datas) {
        this.datas = datas;
    }
}
