package com.example.bbacr.ddw.bean.car;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class GetGoodsValueAddedServices {

    /**
     * msg : success
     * code : 1
     * datas : [{"detailsUrl":"www.baidu.com","price":"240","name":"一年内单次碎屏险","id":2},{"detailsUrl":"www.baidu.com","price":"1000","name":"一年内五次碎屏险","id":3},{"detailsUrl":"www.baidu.com","price":"500","name":"两年延保服务","id":4}]
     */

    private String msg;
    private int code;
    private List<DatasBean> datas;

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

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * detailsUrl : www.baidu.com
         * price : 240
         * name : 一年内单次碎屏险
         * id : 2
         */

        private String detailsUrl;
        private String price;
        private String name;
        private int id;

        public String getDetailsUrl() {
            return detailsUrl;
        }

        public void setDetailsUrl(String detailsUrl) {
            this.detailsUrl = detailsUrl;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
