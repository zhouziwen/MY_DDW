package com.example.bbacr.ddw.bean.my;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class SearchUserCardsNum {

    /**
     * msg : success
     * code : 1
     * datas : {"due":1,"unused":2,"used":2}
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
         * due : 1
         * unused : 2
         * used : 2
         */

        private int due;
        private int unused;
        private int used;

        public int getDue() {
            return due;
        }

        public void setDue(int due) {
            this.due = due;
        }

        public int getUnused() {
            return unused;
        }

        public void setUnused(int unused) {
            this.unused = unused;
        }

        public int getUsed() {
            return used;
        }

        public void setUsed(int used) {
            this.used = used;
        }
    }
}
