package com.example.bbacr.ddw.bean.home;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class BuyRightNow {

    /**
     * address : {"ifDefault":true,"phone":"18838998708","city":"HongKong","name":"kd","location":"114.15,22.15","id":3,"detail":"jiandong259"}
     * allPrice : 8500
     * useAbleNum : 1
     * unUseAbleNum : 1
     * useCardsIdList : [7]
     * addressType : 2
     * unUseAbleList : [{"cardsState":"未使用","money":"60","endUseTime":"2017-11-30","name":"狗熊券","id":7,"beginTime":"2017-10-17","cardsType":"商品券","cardsTypeValue":3}]
     * goodsList : [{"good":{"imgUrl":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png,http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png,http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","goodName":"iPhone 8(A1863) 国行版 金色 64GB","goodsPrice":"8500","num":1},"addServices":[{"price":"500","name":"两年延保服务","time":24,"url":"www.baidu.com"},{"price":"500","name":"两年延保服务","time":24,"url":"www.baidu.com"},{"price":"500","name":"两年延保服务","time":24,"url":"www.baidu.com"},{"price":"500","name":"两年延保服务","time":24,"url":"www.baidu.com"}]}]
     * endPrice : 8440
     * timeOutList : [{"cardsState":"已过期","money":"60","endUseTime":"2017-10-30","name":"满减券（过期）","id":11,"beginTime":"2017-10-16","cardsType":"商品券","cardsTypeValue":3}]
     * cardNumAndMoney : {"money":"60","num":1}
     * useAbleList : [{"cardsState":"未使用","money":"60","endUseTime":"2017-11-30","name":"狗熊券","id":7,"beginTime":"2017-10-17","cardsType":"商品券","cardsTypeValue":3}]
     */

    private AddressBean address;
    private String allPrice;
    private int useAbleNum;
    private int unUseAbleNum;
    private int addressType;
    private String endPrice;

    public String getAddressContent() {
        return addressContent;
    }

    public void setAddressContent(String addressContent) {
        this.addressContent = addressContent;
    }

    private String addressContent;
    private CardNumAndMoneyBean cardNumAndMoney;
    private List<Integer> useCardsIdList;
    private List<UnUseAbleListBean> unUseAbleList;
    private List<GoodsListBean> goodsList;
    private List<TimeOutListBean> timeOutList;
    private List<UseAbleListBean> useAbleList;

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public String getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(String allPrice) {
        this.allPrice = allPrice;
    }

    public int getUseAbleNum() {
        return useAbleNum;
    }

    public void setUseAbleNum(int useAbleNum) {
        this.useAbleNum = useAbleNum;
    }

    public int getUnUseAbleNum() {
        return unUseAbleNum;
    }

    public void setUnUseAbleNum(int unUseAbleNum) {
        this.unUseAbleNum = unUseAbleNum;
    }

    public int getAddressType() {
        return addressType;
    }

    public void setAddressType(int addressType) {
        this.addressType = addressType;
    }

    public String getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(String endPrice) {
        this.endPrice = endPrice;
    }

    public CardNumAndMoneyBean getCardNumAndMoney() {
        return cardNumAndMoney;
    }

    public void setCardNumAndMoney(CardNumAndMoneyBean cardNumAndMoney) {
        this.cardNumAndMoney = cardNumAndMoney;
    }

    public List<Integer> getUseCardsIdList() {
        return useCardsIdList;
    }

    public void setUseCardsIdList(List<Integer> useCardsIdList) {
        this.useCardsIdList = useCardsIdList;
    }

    public List<UnUseAbleListBean> getUnUseAbleList() {
        return unUseAbleList;
    }

    public void setUnUseAbleList(List<UnUseAbleListBean> unUseAbleList) {
        this.unUseAbleList = unUseAbleList;
    }

    public List<GoodsListBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsListBean> goodsList) {
        this.goodsList = goodsList;
    }

    public List<TimeOutListBean> getTimeOutList() {
        return timeOutList;
    }

    public void setTimeOutList(List<TimeOutListBean> timeOutList) {
        this.timeOutList = timeOutList;
    }

    public List<UseAbleListBean> getUseAbleList() {
        return useAbleList;
    }

    public void setUseAbleList(List<UseAbleListBean> useAbleList) {
        this.useAbleList = useAbleList;
    }

    public static class AddressBean {
        /**
         * ifDefault : true
         * phone : 18838998708
         * city : HongKong
         * name : kd
         * location : 114.15,22.15
         * id : 3
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

    public static class CardNumAndMoneyBean {
        /**
         * money : 60
         * num : 1
         */

        private String money;
        private int num;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

    public static class UnUseAbleListBean {
        /**
         * cardsState : 未使用
         * money : 60
         * endUseTime : 2017-11-30
         * name : 狗熊券
         * id : 7
         * beginTime : 2017-10-17
         * cardsType : 商品券
         * cardsTypeValue : 3
         */

        private String cardsState;
        private String money;
        private String endUseTime;
        private String name;
        private int id;
        private String beginTime;
        private String cardsType;
        private int cardsTypeValue;

        public String getCardsState() {
            return cardsState;
        }

        public void setCardsState(String cardsState) {
            this.cardsState = cardsState;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getEndUseTime() {
            return endUseTime;
        }

        public void setEndUseTime(String endUseTime) {
            this.endUseTime = endUseTime;
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

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getCardsType() {
            return cardsType;
        }

        public void setCardsType(String cardsType) {
            this.cardsType = cardsType;
        }

        public int getCardsTypeValue() {
            return cardsTypeValue;
        }

        public void setCardsTypeValue(int cardsTypeValue) {
            this.cardsTypeValue = cardsTypeValue;
        }
    }

    public static class GoodsListBean {
        /**
         * good : {"imgUrl":"http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png,http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png,http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png","goodName":"iPhone 8(A1863) 国行版 金色 64GB","goodsPrice":"8500","num":1}
         * addServices : [{"price":"500","name":"两年延保服务","time":24,"url":"www.baidu.com"},{"price":"500","name":"两年延保服务","time":24,"url":"www.baidu.com"},{"price":"500","name":"两年延保服务","time":24,"url":"www.baidu.com"},{"price":"500","name":"两年延保服务","time":24,"url":"www.baidu.com"}]
         */

        private GoodBean good;
        private List<AddServicesBean> addServices;

        public GoodBean getGood() {
            return good;
        }

        public void setGood(GoodBean good) {
            this.good = good;
        }

        public List<AddServicesBean> getAddServices() {
            return addServices;
        }

        public void setAddServices(List<AddServicesBean> addServices) {
            this.addServices = addServices;
        }

        public static class GoodBean {
            /**
             * imgUrl : http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png,http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png,http://image.ddmzl.com/09c9fd4de67f4013a029f9caf5c7e2e820171001.png
             * goodName : iPhone 8(A1863) 国行版 金色 64GB
             * goodsPrice : 8500
             * num : 1
             */

            private String imgUrl;
            private String goodName;
            private String goodsPrice;
            private int num;

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getGoodName() {
                return goodName;
            }

            public void setGoodName(String goodName) {
                this.goodName = goodName;
            }

            public String getGoodsPrice() {
                return goodsPrice;
            }

            public void setGoodsPrice(String goodsPrice) {
                this.goodsPrice = goodsPrice;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }
        }

        public static class AddServicesBean {
            /**
             * price : 500
             * name : 两年延保服务
             * time : 24
             * url : www.baidu.com
             */

            private String price;
            private String name;
            private int time;
            private String url;

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

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }

    public static class TimeOutListBean {
        /**
         * cardsState : 已过期
         * money : 60
         * endUseTime : 2017-10-30
         * name : 满减券（过期）
         * id : 11
         * beginTime : 2017-10-16
         * cardsType : 商品券
         * cardsTypeValue : 3
         */

        private String cardsState;
        private String money;
        private String endUseTime;
        private String name;
        private int id;
        private String beginTime;
        private String cardsType;
        private int cardsTypeValue;

        public String getCardsState() {
            return cardsState;
        }

        public void setCardsState(String cardsState) {
            this.cardsState = cardsState;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getEndUseTime() {
            return endUseTime;
        }

        public void setEndUseTime(String endUseTime) {
            this.endUseTime = endUseTime;
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

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getCardsType() {
            return cardsType;
        }

        public void setCardsType(String cardsType) {
            this.cardsType = cardsType;
        }

        public int getCardsTypeValue() {
            return cardsTypeValue;
        }

        public void setCardsTypeValue(int cardsTypeValue) {
            this.cardsTypeValue = cardsTypeValue;
        }
    }

    public static class UseAbleListBean {
        /**
         * cardsState : 未使用
         * money : 60
         * endUseTime : 2017-11-30
         * name : 狗熊券
         * id : 7
         * beginTime : 2017-10-17
         * cardsType : 商品券
         * cardsTypeValue : 3
         */

        private String cardsState;
        private String money;
        private String endUseTime;
        private String name;
        private int id;
        private String beginTime;
        private String cardsType;
        private int cardsTypeValue;

        public String getCardsState() {
            return cardsState;
        }

        public void setCardsState(String cardsState) {
            this.cardsState = cardsState;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getEndUseTime() {
            return endUseTime;
        }

        public void setEndUseTime(String endUseTime) {
            this.endUseTime = endUseTime;
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

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getCardsType() {
            return cardsType;
        }

        public void setCardsType(String cardsType) {
            this.cardsType = cardsType;
        }

        public int getCardsTypeValue() {
            return cardsTypeValue;
        }

        public void setCardsTypeValue(int cardsTypeValue) {
            this.cardsTypeValue = cardsTypeValue;
        }
    }
}
