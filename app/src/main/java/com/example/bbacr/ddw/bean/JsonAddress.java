package com.example.bbacr.ddw.bean;

import java.util.List;

/**
 * Created by Nianner Wang on 2017/9/23.
 * wytaper495@qq.com
 * 类注释：选择地址的bean类
 */

public class JsonAddress {

    /**
     * id : 100
     * name : 浙江省
     */

    private String id;
    private String name;
    private List<CityBean> city;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityBean> getCity() {
        return city;
    }

    public void setCity(List<CityBean> city) {
        this.city = city;
    }

    public static class CityBean {
        /**
         * id : 101
         * name : 杭州市
         * county : [{"id":"102","name":"上城区"},{"id":"103","name":"下城区"},{"id":"104","name":"江干区"},{"id":"105","name":"拱墅区"},{"id":"106","name":"西湖区"},{"id":"107","name":"滨江区"},{"id":"108","name":"萧山区"},{"id":"109","name":"余杭区"},{"id":"110","name":"桐庐县"},{"id":"111","name":"淳安县"},{"id":"112","name":"建德市"},{"id":"113","name":"富阳市"},{"id":"114","name":"临安市"},{"id":"115","name":"其他区"}]
         */

        private String id;
        private String name;
        private List<CountyBean> county;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<CountyBean> getCounty() {
            return county;
        }

        public void setCounty(List<CountyBean> county) {
            this.county = county;
        }

        public static class CountyBean {
            /**
             * id : 102
             * name : 上城区
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
