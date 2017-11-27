package com.example.bbacr.ddw.bean.home;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class GoodSearch {

    /**
     * msg : success
     * code : 1
     * totalPage : 0
     * datas : [{"commentNum":0,"footMarkNum":440,"name":"Apple/苹果 iPhone 6","goods_id":"1","main_img":"http://ddw-image.oss-cn-beijing.aliyuncs.com/efb8cbe793d5418e9ea1c08c60a9496e20171113.png","member_price":"2650.0","abbreviation":"苹果6【12期分期送壳膜】 Apple/苹果 iPhone 6 32G全网通4g手机","index_name":"ddm_goods"},{"commentNum":0,"footMarkNum":25,"name":"Apple iPhone (A1865) 国行版 银色64GB","goods_id":"2","main_img":"http://ddw-image.oss-cn-beijing.aliyuncs.com/352193894b4642f0a348b1218efe5ba220171113.png","member_price":"8888.0","abbreviation":"iphone X （A1586） 国行版","index_name":"ddm_goods"}]
     * num : 2
     * recordsTotal : 2
     */

    private String msg;
    private int code;
    private int totalPage;
    private int num;
    private int recordsTotal;
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

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * commentNum : 0
         * footMarkNum : 440
         * name : Apple/苹果 iPhone 6
         * goods_id : 1
         * main_img : http://ddw-image.oss-cn-beijing.aliyuncs.com/efb8cbe793d5418e9ea1c08c60a9496e20171113.png
         * member_price : 2650.0
         * abbreviation : 苹果6【12期分期送壳膜】 Apple/苹果 iPhone 6 32G全网通4g手机
         * index_name : ddm_goods
         */

        private int commentNum;
        private int footMarkNum;
        private String name;
        private String goods_id;
        private String main_img;
        private String member_price;
        private String abbreviation;
        private String index_name;

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getFootMarkNum() {
            return footMarkNum;
        }

        public void setFootMarkNum(int footMarkNum) {
            this.footMarkNum = footMarkNum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getMain_img() {
            return main_img;
        }

        public void setMain_img(String main_img) {
            this.main_img = main_img;
        }

        public String getMember_price() {
            return member_price;
        }

        public void setMember_price(String member_price) {
            this.member_price = member_price;
        }

        public String getAbbreviation() {
            return abbreviation;
        }

        public void setAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        public String getIndex_name() {
            return index_name;
        }

        public void setIndex_name(String index_name) {
            this.index_name = index_name;
        }
    }
}
