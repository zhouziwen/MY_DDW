package com.example.bbacr.ddw.bean.home;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class HomeTop {

    /**
     * msg : success
     * code : 1
     * datas : [{"createUserId":1,"img":"../phoneImg/index_coupon_icon.png","createTime":"Fri Oct 20 13:40:54 CST 2017","name":"领券中心","appUrl":"","id":9,"state":1,"shortName":"好券领不停","url":"http://localhost/m/phoneHtml/coupon.html"},{"createUserId":1,"img":"../phoneImg/index_day_new.png","createTime":"Fri Oct 20 14:25:10 CST 2017","name":"每日新品","appUrl":"isDate=1&page=1","id":8,"state":1,"shortName":"iphoneX已开启","url":"http://localhost/m/phoneHtml/goods-list.html?isDate=1&page=1"},{"createUserId":1,"img":"../phoneImg/index_accessories_icon.png","createTime":"Fri Oct 20 14:25:37 CST 2017","name":"手机精品配件","appUrl":"category_id=59&page=1","id":7,"state":1,"shortName":"给手机搭配好的","url":"http://localhost/m/phoneHtml/goods-list.html?category_id=59&page=1"},{"createUserId":1,"img":"../phoneImg/index_school_icon.png","createTime":"Fri Oct 20 14:26:46 CST 2017","name":"热销商品","appUrl":"isSales=1&page=1","id":6,"state":1,"shortName":"叮当商品排行榜","url":"http://localhost/m/phoneHtml/goods-list.html?isSales=1&page=1"}]
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
         * createUserId : 1
         * img : ../phoneImg/index_coupon_icon.png
         * createTime : Fri Oct 20 13:40:54 CST 2017
         * name : 领券中心
         * appUrl :
         * id : 9
         * state : 1
         * shortName : 好券领不停
         * url : http://localhost/m/phoneHtml/coupon.html
         */

        private int createUserId;
        private String img;
        private String createTime;
        private String name;
        private String appUrl;
        private int id;
        private int state;
        private String shortName;
        private String url;

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAppUrl() {
            return appUrl;
        }

        public void setAppUrl(String appUrl) {
            this.appUrl = appUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
