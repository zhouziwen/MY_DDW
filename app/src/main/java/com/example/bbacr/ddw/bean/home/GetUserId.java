package com.example.bbacr.ddw.bean.home;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class GetUserId {

    /**
     * msg : success
     * code : 1
     * datas : {"userId":765}
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
         * userId : 765
         */

        private int userId;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
