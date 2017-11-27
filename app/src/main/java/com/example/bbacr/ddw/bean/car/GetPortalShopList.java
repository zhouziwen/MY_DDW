package com.example.bbacr.ddw.bean.car;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class GetPortalShopList {

    /**
     * msg : success
     * code : 1
     * datas : [{"longitudeAndLatitude":"34.80573593002055,113.6709801409","contact":"18037350015","shopLevel":"店铺","name":"政七街店铺","location":"东风路政七街","id":1,"shopType":"自营","state":"有效","linkman":"王超"}]
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
         * longitudeAndLatitude : 34.80573593002055,113.6709801409
         * contact : 18037350015
         * shopLevel : 店铺
         * name : 政七街店铺
         * location : 东风路政七街
         * id : 1
         * shopType : 自营
         * state : 有效
         * linkman : 王超
         */

        private String longitudeAndLatitude;
        private String contact;
        private String shopLevel;
        private String name;
        private String location;
        private int id;
        private String shopType;
        private String state;
        private String linkman;

        public String getLongitudeAndLatitude() {
            return longitudeAndLatitude;
        }

        public void setLongitudeAndLatitude(String longitudeAndLatitude) {
            this.longitudeAndLatitude = longitudeAndLatitude;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getShopLevel() {
            return shopLevel;
        }

        public void setShopLevel(String shopLevel) {
            this.shopLevel = shopLevel;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getShopType() {
            return shopType;
        }

        public void setShopType(String shopType) {
            this.shopType = shopType;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }
    }
}
