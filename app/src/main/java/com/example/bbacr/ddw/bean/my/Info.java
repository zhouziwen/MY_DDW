package com.example.bbacr.ddw.bean.my;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class Info {

    /**
     * msg : success
     * code : 1
     * datas : {"sexValue":1,"qq":"","birthday":"2017-10-25 11:03:20","realName":"樊飞龙","weixin":"","identity":"","sex":"隐私","name":"18838998708","id":4,"email":"","avotorr":"http://image.ddmzl.com/e9a51aa71ca24ce4bcd53bf089050f5020170714.jpg"}
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
         * sexValue : 1
         * qq :
         * birthday : 2017-10-25 11:03:20
         * realName : 樊飞龙
         * weixin :
         * identity :
         * sex : 隐私
         * name : 18838998708
         * id : 4
         * email :
         * avotorr : http://image.ddmzl.com/e9a51aa71ca24ce4bcd53bf089050f5020170714.jpg
         */

        private int sexValue;
        private String qq;
        private String birthday;
        private String realName;
        private String weixin;
        private String identity;
        private String sex;
        private String name;
        private int id;
        private String email;
        private String avotorr;

        public int getSexValue() {
            return sexValue;
        }

        public void setSexValue(int sexValue) {
            this.sexValue = sexValue;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAvotorr() {
            return avotorr;
        }

        public void setAvotorr(String avotorr) {
            this.avotorr = avotorr;
        }
    }
}
