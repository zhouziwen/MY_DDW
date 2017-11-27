package com.example.bbacr.ddw.bean.my;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class GetUserAddress {

    /**
     * msg : success
     * code : 1
     * datas : {"size":2,"addressLest":[{"ifDefault":true,"phone":"18838998708","city":"HongKong","name":"kd","location":"114.15,22.15","id":2,"detail":"jiandong259"},{"ifDefault":false,"phone":"18838998708","city":"HongKong","name":"kd","location":"114.15,22.15","id":1,"detail":"dongfenlu35"}]}
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
        /**
         * size : 2
         * addressLest : [{"ifDefault":true,"phone":"18838998708","city":"HongKong","name":"kd","location":"114.15,22.15","id":2,"detail":"jiandong259"},{"ifDefault":false,"phone":"18838998708","city":"HongKong","name":"kd","location":"114.15,22.15","id":1,"detail":"dongfenlu35"}]
         */

        private int size;
        private List<AddressLestBean> addressLest;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public List<AddressLestBean> getAddressLest() {
            return addressLest;
        }

        public void setAddressLest(List<AddressLestBean> addressLest) {
            this.addressLest = addressLest;
        }

        public static class AddressLestBean {
            /**
             * ifDefault : true
             * phone : 18838998708
             * city : HongKong
             * name : kd
             * location : 114.15,22.15
             * id : 2
             * detail : jiandong259
             */

            private boolean ifDefault;
            private String phone;
            private String city;
            private String name;
            private String location;
            private int id;
            private String detail;

            public boolean isIfDefault() {
                return ifDefault;
            }

            public void setIfDefault(boolean ifDefault) {
                this.ifDefault = ifDefault;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
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

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }
        }
    }
}
