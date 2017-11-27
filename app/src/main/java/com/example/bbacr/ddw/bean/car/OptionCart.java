package com.example.bbacr.ddw.bean.car;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class OptionCart {

    /**
     * msg : success
     * code : 1
     * datas : {"totalMoney":"6666.00","rowCount":1}
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
         * totalMoney : 6666.00
         * rowCount : 1
         */

        private String totalMoney;
        private int rowCount;

        public String getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(String totalMoney) {
            this.totalMoney = totalMoney;
        }

        public int getRowCount() {
            return rowCount;
        }

        public void setRowCount(int rowCount) {
            this.rowCount = rowCount;
        }
    }
}
