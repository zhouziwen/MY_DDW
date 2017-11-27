package com.example.bbacr.ddw.bean.classfy;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */
public class TwoLevelCategory {


    /**
     * msg : success
     * code : 1
     * datas : {"imgUrl":"http://ddw-image.oss-cn-beijing.aliyuncs.com/category.jpg","secondCategory":[{"categoryType":2,"thirdCategory":[{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"苹果","id":11,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201703261133240.jpg","level":"三级分类","name":"华为","id":12,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201703261133240.jpg","level":"三级分类","name":"荣耀","id":13,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201703261133240.jpg","level":"三级分类","name":"小米","id":14,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201703261133240.jpg","level":"三级分类","name":"三星","id":15,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201703261133240.jpg","level":"三级分类","name":"OPPO","id":16,"levelValue":3}],"level":"二级分类","name":"热门品牌","id":9,"levelValue":2},{"categoryType":1,"thirdCategory":[{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"HTC","id":26,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"中兴","id":27,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"摩托罗拉","id":28,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"乐视","id":29,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"联想","id":30,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"酷派","id":31,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"LG","id":32,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"夏普","id":33,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"黑莓","id":34,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"AGM旅行手机","id":35,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"老人手机","id":36,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"全部手机","id":37,"levelValue":3}],"level":"二级分类","name":"更多品牌","id":10,"levelValue":2}],"linkUrl":"#"}
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
         * imgUrl : http://ddw-image.oss-cn-beijing.aliyuncs.com/category.jpg
         * secondCategory : [{"categoryType":2,"thirdCategory":[{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"苹果","id":11,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201703261133240.jpg","level":"三级分类","name":"华为","id":12,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201703261133240.jpg","level":"三级分类","name":"荣耀","id":13,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201703261133240.jpg","level":"三级分类","name":"小米","id":14,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201703261133240.jpg","level":"三级分类","name":"三星","id":15,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201703261133240.jpg","level":"三级分类","name":"OPPO","id":16,"levelValue":3}],"level":"二级分类","name":"热门品牌","id":9,"levelValue":2},{"categoryType":1,"thirdCategory":[{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"HTC","id":26,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"中兴","id":27,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"摩托罗拉","id":28,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"乐视","id":29,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"联想","id":30,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"酷派","id":31,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"LG","id":32,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"夏普","id":33,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"黑莓","id":34,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"AGM旅行手机","id":35,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"老人手机","id":36,"levelValue":3},{"imgUrl":"http://img2.ch999img.com/pic/category/201709211116130.jpg","level":"三级分类","name":"全部手机","id":37,"levelValue":3}],"level":"二级分类","name":"更多品牌","id":10,"levelValue":2}]
         * linkUrl : #
         */

        private String imgUrl;
        private String linkUrl;
        private List<SecondCategoryBean> secondCategory;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public List<SecondCategoryBean> getSecondCategory() {
            return secondCategory;
        }

        public void setSecondCategory(List<SecondCategoryBean> secondCategory) {
            this.secondCategory = secondCategory;
        }

        public static class SecondCategoryBean {
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
}
