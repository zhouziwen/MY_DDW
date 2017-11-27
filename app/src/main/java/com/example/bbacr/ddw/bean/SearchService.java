package com.example.bbacr.ddw.bean;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class SearchService {

    /**
     * msg : success
     * code : 1
     * datas : {"data":[{"imie":"","goodName":"Apple iPhone (A1865) 国行版 银色64GB","createTime":"2017-11-13","num":1,"name":"两次碎屏保（限时特惠）","shopName":"政七街店铺","id":47,"state":true,"endTime":"2018-03-13"},{"imie":"","goodName":"Apple iPhone (A1865) 国行版 银色64GB","createTime":"2017-11-13","num":1,"name":"两次碎屏保（限时特惠）","shopName":"政七街店铺","id":46,"state":true,"endTime":"2018-03-13"},{"imie":"","goodName":"Apple iPhone (A1865) 国行版 银色64GB","createTime":"2017-11-11","num":1,"name":"两次碎屏保（限时特惠）","shopName":"政七街店铺","id":45,"state":false,"endTime":"2018-03-11"},{"imie":"","goodName":"Apple iPhone (A1865) 国行版 银色64GB","createTime":"2017-11-11","num":1,"name":"两次碎屏保（限时特惠）","shopName":"政七街店铺","id":44,"state":false,"endTime":"2018-03-11"},{"imie":"","goodName":"Apple iPhone (A1865) 国行版 银色64GB","createTime":"2017-11-11","num":1,"name":"两次碎屏保（限时特惠）","shopName":"政七街店铺","id":43,"state":false,"endTime":"2018-03-11"}],"totalPage":2,"recordsTotal":6}
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
         * data : [{"imie":"","goodName":"Apple iPhone (A1865) 国行版 银色64GB","createTime":"2017-11-13","num":1,"name":"两次碎屏保（限时特惠）","shopName":"政七街店铺","id":47,"state":true,"endTime":"2018-03-13"},{"imie":"","goodName":"Apple iPhone (A1865) 国行版 银色64GB","createTime":"2017-11-13","num":1,"name":"两次碎屏保（限时特惠）","shopName":"政七街店铺","id":46,"state":true,"endTime":"2018-03-13"},{"imie":"","goodName":"Apple iPhone (A1865) 国行版 银色64GB","createTime":"2017-11-11","num":1,"name":"两次碎屏保（限时特惠）","shopName":"政七街店铺","id":45,"state":false,"endTime":"2018-03-11"},{"imie":"","goodName":"Apple iPhone (A1865) 国行版 银色64GB","createTime":"2017-11-11","num":1,"name":"两次碎屏保（限时特惠）","shopName":"政七街店铺","id":44,"state":false,"endTime":"2018-03-11"},{"imie":"","goodName":"Apple iPhone (A1865) 国行版 银色64GB","createTime":"2017-11-11","num":1,"name":"两次碎屏保（限时特惠）","shopName":"政七街店铺","id":43,"state":false,"endTime":"2018-03-11"}]
         * totalPage : 2
         * recordsTotal : 6
         */

        private int totalPage;
        private int recordsTotal;
        private List<DataBean> data;

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getRecordsTotal() {
            return recordsTotal;
        }

        public void setRecordsTotal(int recordsTotal) {
            this.recordsTotal = recordsTotal;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * imie :
             * goodName : Apple iPhone (A1865) 国行版 银色64GB
             * createTime : 2017-11-13
             * num : 1
             * name : 两次碎屏保（限时特惠）
             * shopName : 政七街店铺
             * id : 47
             * state : true
             * endTime : 2018-03-13
             */

            private String imie;
            private String goodName;
            private String createTime;
            private int num;
            private String name;
            private String shopName;
            private int id;
            private boolean state;
            private String endTime;

            public String getImie() {
                return imie;
            }

            public void setImie(String imie) {
                this.imie = imie;
            }

            public String getGoodName() {
                return goodName;
            }

            public void setGoodName(String goodName) {
                this.goodName = goodName;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public boolean isState() {
                return state;
            }

            public void setState(boolean state) {
                this.state = state;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }
        }
    }
}
