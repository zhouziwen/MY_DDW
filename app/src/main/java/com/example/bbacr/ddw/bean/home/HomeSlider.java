package com.example.bbacr.ddw.bean.home;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class HomeSlider {

    /**
     * msg : success
     * code : 1
     * datas : [{"img":"http://ddm-image.oss-cn-beijing.aliyuncs.com/banner3.jpg","typeValue":2,"id":17,"sort":1,"type":"wap推荐","url":"#"},{"img":"http://ddm-image.oss-cn-beijing.aliyuncs.com/banner3.jpg","typeValue":2,"id":27,"sort":2,"type":"wap推荐","url":"http://www.hantangyd.com/m/html/pattern.html"}]
     */

    private String msg;
    private int code;
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

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * img : http://ddm-image.oss-cn-beijing.aliyuncs.com/banner3.jpg
         * typeValue : 2
         * id : 17
         * sort : 1
         * type : wap推荐
         * url : #
         */
        private String img;
        private int typeValue;
        private int id;
        private int sort;
        private String type;
        private String url;
        public String getImg() {
            return img;
        }
        public void setImg(String img) {
            this.img = img;
        }
        public int getTypeValue() {
            return typeValue;
        }
        public void setTypeValue(int typeValue) {
            this.typeValue = typeValue;
        }
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
