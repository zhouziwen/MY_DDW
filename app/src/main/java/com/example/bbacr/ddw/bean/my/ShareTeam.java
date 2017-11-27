package com.example.bbacr.ddw.bean.my;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class ShareTeam {

    /**
     * msg : success
     * code : 1
     * datas : {"one":{"persons":0,"percentage":0},"two":{"persons":0,"percentage":0},"three":{"persons":0,"percentage":0}}
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
         * one : {"persons":0,"percentage":0}
         * two : {"persons":0,"percentage":0}
         * three : {"persons":0,"percentage":0}
         */

        private OneBean one;
        private TwoBean two;
        private ThreeBean three;

        public OneBean getOne() {
            return one;
        }

        public void setOne(OneBean one) {
            this.one = one;
        }

        public TwoBean getTwo() {
            return two;
        }

        public void setTwo(TwoBean two) {
            this.two = two;
        }

        public ThreeBean getThree() {
            return three;
        }

        public void setThree(ThreeBean three) {
            this.three = three;
        }

        public static class OneBean {
            /**
             * persons : 0
             * percentage : 0
             */

            private int persons;
            private int percentage;

            public int getPersons() {
                return persons;
            }

            public void setPersons(int persons) {
                this.persons = persons;
            }

            public int getPercentage() {
                return percentage;
            }

            public void setPercentage(int percentage) {
                this.percentage = percentage;
            }
        }

        public static class TwoBean {
            /**
             * persons : 0
             * percentage : 0
             */

            private int persons;
            private int percentage;

            public int getPersons() {
                return persons;
            }

            public void setPersons(int persons) {
                this.persons = persons;
            }

            public int getPercentage() {
                return percentage;
            }

            public void setPercentage(int percentage) {
                this.percentage = percentage;
            }
        }

        public static class ThreeBean {
            /**
             * persons : 0
             * percentage : 0
             */

            private int persons;
            private int percentage;

            public int getPersons() {
                return persons;
            }

            public void setPersons(int persons) {
                this.persons = persons;
            }

            public int getPercentage() {
                return percentage;
            }

            public void setPercentage(int percentage) {
                this.percentage = percentage;
            }
        }
    }
}
