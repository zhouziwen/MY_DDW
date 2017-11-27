package com.example.bbacr.ddw.bean;

/**
 * Created by Bbacr on 2017/7/6.
 * 短信登录
 */

public class CodeLogin {

    /**
     * msg : success
     * code : 1
     * datas : {"realName":"飞龙","userTypeValue":1,"userType":"普通用户","userId":4,"token":"5A900C0E2C54D7E9340F7CB3B7AF01B8"}
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
         * realName : 飞龙
         * userTypeValue : 1
         * userType : 普通用户
         * userId : 4
         * token : 5A900C0E2C54D7E9340F7CB3B7AF01B8
         */

        private String realName;
        private int userTypeValue;
        private String userType;
        private int userId;
        private String token;

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getUserTypeValue() {
            return userTypeValue;
        }

        public void setUserTypeValue(int userTypeValue) {
            this.userTypeValue = userTypeValue;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
