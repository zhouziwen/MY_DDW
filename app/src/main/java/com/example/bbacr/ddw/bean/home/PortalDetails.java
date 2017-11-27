package com.example.bbacr.ddw.bean.home;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class PortalDetails {

    /**
     * msg : success
     * code : 1
     * datas : {"parameter":"参数9","detailsImg":"<img src=\"http://image.ddmzl.com/74e39a80011e4ea7b3e66c0f7025436520171001.jpg\" alt=\"img\" style=\"width: 90%\"><img src=\"http://image.ddmzl.com/e75bfa063e854cad9713d409a8eff31720171001.jpg\" alt=\"img\" style=\"width: 90%\">","id":1,"safeguard":"这是售后保障"}
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
         * parameter : 参数9
         * detailsImg : <img src="http://image.ddmzl.com/74e39a80011e4ea7b3e66c0f7025436520171001.jpg" alt="img" style="width: 90%"><img src="http://image.ddmzl.com/e75bfa063e854cad9713d409a8eff31720171001.jpg" alt="img" style="width: 90%">
         * id : 1
         * safeguard : 这是售后保障
         */

        private String parameter;
        private String detailsImg;
        private int id;
        private String safeguard;

        public String getParameter() {
            return parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }

        public String getDetailsImg() {
            return detailsImg;
        }

        public void setDetailsImg(String detailsImg) {
            this.detailsImg = detailsImg;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSafeguard() {
            return safeguard;
        }

        public void setSafeguard(String safeguard) {
            this.safeguard = safeguard;
        }
    }
}
