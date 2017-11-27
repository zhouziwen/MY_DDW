package com.example.bbacr.ddw.bean.my;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class AddCollections {

    /**
     * msg : success
     * code : 1
     * datas : {"collectionsId":29}
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
         * collectionsId : 29
         */

        private String collectionsId;

        public String getCollectionsId() {
            return collectionsId;
        }

        public void setCollectionsId(String collectionsId) {
            this.collectionsId = collectionsId;
        }
    }
}
