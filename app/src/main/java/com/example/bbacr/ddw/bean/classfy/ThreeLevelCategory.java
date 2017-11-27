package com.example.bbacr.ddw.bean.classfy;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class ThreeLevelCategory {

    /**
     * msg : success
     * code : 1
     * datas : [{"categoryType":2,"thirdCategory":[{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"苹果","id":11,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201703261133240.jpg","level":"三级分类","name":"华为","id":12,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201703261133240.jpg","level":"三级分类","name":"荣耀","id":13,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201703261133240.jpg","level":"三级分类","name":"小米","id":14,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201703261133240.jpg","level":"三级分类","name":"三星","id":15,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201703261133240.jpg","level":"三级分类","name":"OPPO","id":16,"levelValue":3}],"level":"二级分类","name":"热门品牌","id":9,"levelValue":2},{"categoryType":2,"thirdCategory":[{"imgUrl":"http://img2.ch999img.com/pic/category/201704120956590.jpg","level":"三级分类","name":"苹果","id":100,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201704120956590.jpg","level":"三级分类","name":"华硕","id":101,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201704120956590.jpg","level":"三级分类","name":"小米","id":102,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201704120956590.jpg","level":"三级分类","name":"联想","id":103,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201704120956590.jpg","level":"三级分类","name":"戴尔","id":104,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201704120956590.jpg","level":"三级分类","name":"微软","id":105,"levelValue":3}],"level":"二级分类","name":"热销品牌","id":82,"levelValue":2}]
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
         * categoryType : 2
         * thirdCategory : [{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"苹果","id":11,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201703261133240.jpg","level":"三级分类","name":"华为","id":12,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201703261133240.jpg","level":"三级分类","name":"荣耀","id":13,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201703261133240.jpg","level":"三级分类","name":"小米","id":14,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201703261133240.jpg","level":"三级分类","name":"三星","id":15,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201703261133240.jpg","level":"三级分类","name":"OPPO","id":16,"levelValue":3}]
         * level : 二级分类
         * name : 热门品牌
         * id : 9
         * levelValue : 2
         */

        private int categoryType;
        private String level;
        private String name;
        private int id;
        private int levelValue;
        private List<ThirdCategoryBean> thirdCategory;

        public int getCategoryType() {
            return categoryType;
        }

        public void setCategoryType(int categoryType) {
            this.categoryType = categoryType;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
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

        public int getLevelValue() {
            return levelValue;
        }

        public void setLevelValue(int levelValue) {
            this.levelValue = levelValue;
        }

        public List<ThirdCategoryBean> getThirdCategory() {
            return thirdCategory;
        }

        public void setThirdCategory(List<ThirdCategoryBean> thirdCategory) {
            this.thirdCategory = thirdCategory;
        }

        public static class ThirdCategoryBean {
            /**
             * imgUrl : http://img2.ch999img.com/pic/category/201709211116130.jpg
             * level : 三级分类
             * name : 苹果
             * id : 11
             * levelValue : 3
             */

            private String imgUrl;
            private String level;
            private String name;
            private int id;
            private int levelValue;

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
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

            public int getLevelValue() {
                return levelValue;
            }

            public void setLevelValue(int levelValue) {
                this.levelValue = levelValue;
            }
        }
    }
}
