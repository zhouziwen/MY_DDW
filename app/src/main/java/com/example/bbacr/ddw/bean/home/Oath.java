package com.example.bbacr.ddw.bean.home;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class Oath {

    /**
     * msg : success
     * code : 1
     * datas : {"token":"rNDKHJYA9WCGez5PgbrtYnvSP9OABylxC7k-dwFkKd7SLVv_AQ1zj_JgFhJujixj3JbkXCQ9KL5gQrRurt7HRl1sKnMVzifZSyxZFE0iXCw","isOAuth":"1"}
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
         * token : rNDKHJYA9WCGez5PgbrtYnvSP9OABylxC7k-dwFkKd7SLVv_AQ1zj_JgFhJujixj3JbkXCQ9KL5gQrRurt7HRl1sKnMVzifZSyxZFE0iXCw
         * isOAuth : 1
         */

        private String token;
        private String isOAuth;
        private String accessToken;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        private String openId;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getIsOAuth() {
            return isOAuth;
        }

        public void setIsOAuth(String isOAuth) {
            this.isOAuth = isOAuth;
        }
    }
}
