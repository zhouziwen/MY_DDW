package com.example.bbacr.ddw.bean.home;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class GetPriceRecord {

    /**
     * msg : success
     * code : 1
     * datas : {"date":["10-24","10-25","10-26","10-27","10-28","10-29"],"price":["6100","6100","5700","5810","6100","6100"]}
     */

    private String msg;
    private int code;
    private DatasBean datas;

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

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        private List<String> date;
        private List<String> price;

        public List<String> getDate() {
            return date;
        }

        public void setDate(List<String> date) {
            this.date = date;
        }

        public List<String> getPrice() {
            return price;
        }

        public void setPrice(List<String> price) {
            this.price = price;
        }
    }
}
