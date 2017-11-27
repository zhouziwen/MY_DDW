package com.example.bbacr.ddw.bean.home;

import java.util.List;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class GetUserMessages {

    /**
     * msg : success
     * code : 1
     * datas : {"totalPage":1,"pageNo":1,"messages":[{"ifRead":false,"createTime":"2017-10-01","id":1500,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1499,"title":"中奖信息","content":"恭喜您获得 五等奖,请在中奖纪录中领取"},{"ifRead":false,"createTime":"2017-10-01","id":1498,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1497,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1496,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1495,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1494,"title":"中奖信息","content":"恭喜您获得 七等奖,奖品为：2积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1493,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1492,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1491,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1490,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1489,"title":"中奖信息","content":"恭喜您获得 六等奖,请在中奖纪录中领取"},{"ifRead":false,"createTime":"2017-10-01","id":1488,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1487,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1486,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1485,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1484,"title":"中奖信息","content":"恭喜您获得 七等奖,奖品为：2积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-09-25","id":1170,"title":"积分兑换审核通过","content":"您TX7651506047949079的积分兑换订单:审核通过"}],"recordsTotal":18}
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
         * totalPage : 1
         * pageNo : 1
         * messages : [{"ifRead":false,"createTime":"2017-10-01","id":1500,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1499,"title":"中奖信息","content":"恭喜您获得 五等奖,请在中奖纪录中领取"},{"ifRead":false,"createTime":"2017-10-01","id":1498,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1497,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1496,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1495,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1494,"title":"中奖信息","content":"恭喜您获得 七等奖,奖品为：2积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1493,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1492,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1491,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1490,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1489,"title":"中奖信息","content":"恭喜您获得 六等奖,请在中奖纪录中领取"},{"ifRead":false,"createTime":"2017-10-01","id":1488,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1487,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1486,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1485,"title":"中奖信息","content":"恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-10-01","id":1484,"title":"中奖信息","content":"恭喜您获得 七等奖,奖品为：2积分红包，已经存入您的资金账户"},{"ifRead":false,"createTime":"2017-09-25","id":1170,"title":"积分兑换审核通过","content":"您TX7651506047949079的积分兑换订单:审核通过"}]
         * recordsTotal : 18
         */

        private int totalPage;
        private int pageNo;
        private int recordsTotal;
        private List<MessagesBean> messages;

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getRecordsTotal() {
            return recordsTotal;
        }

        public void setRecordsTotal(int recordsTotal) {
            this.recordsTotal = recordsTotal;
        }

        public List<MessagesBean> getMessages() {
            return messages;
        }

        public void setMessages(List<MessagesBean> messages) {
            this.messages = messages;
        }

        public static class MessagesBean {
            /**
             * ifRead : false
             * createTime : 2017-10-01
             * id : 1500
             * title : 中奖信息
             * content : 恭喜您获得 八等奖,奖品为：1积分红包，已经存入您的资金账户
             */

            private boolean ifRead;
            private String createTime;
            private int id;
            private String title;
            private String content;

            public boolean isIfRead() {
                return ifRead;
            }

            public void setIfRead(boolean ifRead) {
                this.ifRead = ifRead;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
