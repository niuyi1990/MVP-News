package com.niuyi.mvp_news.bean;

import java.util.List;

/**
 * 作者：${牛毅} on 2016/12/6 10:51
 * 邮箱：niuyi19900923@hotmail.com
 */
public class FunnyJokeBean {

    /**
     * error_code : 0
     * reason : Success
     * result : {"data":[{"content":"好厉害！太逼真了","hashId":"49D15B3C4BB6E83478B3ECC34154FF36","unixtime":1480917918,"updatetime":"2016-12-05 14:05:18","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201612/05/49D15B3C4BB6E83478B3ECC34154FF36.gif"},{"content":"恕我孤陋寡闻，原来2016年了 中国还有美国的租界咯？","hashId":"BD1D38648515F76FB1A43FF9F8329925","unixtime":1480917918,"updatetime":"2016-12-05 14:05:18","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201612/05/BD1D38648515F76FB1A43FF9F8329925.png"},{"content":"希望你们余生的日子里，不会再有深夜痛哭的眼泪。","hashId":"203A83B7C56DC45EACBA95D41E94DF81","unixtime":1480917918,"updatetime":"2016-12-05 14:05:18","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201612/05/203A83B7C56DC45EACBA95D41E94DF81.jpg"},{"content":"没有电脑特效时代的荧幕英雄","hashId":"771B41A59FD3406F12F761A5B49E8D68","unixtime":1480917918,"updatetime":"2016-12-05 14:05:18","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201612/05/771B41A59FD3406F12F761A5B49E8D68.gif"},{"content":"你说虾米？！","hashId":"ED78B181DB6B2D0BCDFC3F37C44F5E49","unixtime":1480917918,"updatetime":"2016-12-05 14:05:18","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201612/05/ED78B181DB6B2D0BCDFC3F37C44F5E49.png"},{"content":"好爸爸","hashId":"45EDE388219ABDB4D776C7656E444BA9","unixtime":1480917918,"updatetime":"2016-12-05 14:05:18","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201612/05/45EDE388219ABDB4D776C7656E444BA9.jpg"},{"content":"当你试图去理解一个女生的时候...","hashId":"13E68C0451B1ABF298F4987825111829","unixtime":1480917918,"updatetime":"2016-12-05 14:05:18","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201612/05/13E68C0451B1ABF298F4987825111829.jpg"},{"content":"把电脑上\u201c我的电脑\u201d删除后会怎样？","hashId":"3358AE6204D4795093160CDDA48ABE3B","unixtime":1480917918,"updatetime":"2016-12-05 14:05:18","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201612/05/3358AE6204D4795093160CDDA48ABE3B.gif"},{"content":"捡回来的流浪小猫害怕再被抛弃，每次入睡都要牵着主人手！简直要萌化了","hashId":"5A941642FB512CE639C581A8FC1F1221","unixtime":1480917918,"updatetime":"2016-12-05 14:05:18","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201612/05/5A941642FB512CE639C581A8FC1F1221.gif"},{"content":"不好意思，给亚洲拖后腿长肉了\u2026\u2026","hashId":"2DCA69EDA643DF94DC8C0BE707DC3D31","unixtime":1480917918,"updatetime":"2016-12-05 14:05:18","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201612/05/2DCA69EDA643DF94DC8C0BE707DC3D31.jpg"}]}
     */

    private int error_code;
    private String reason;
    private ResultBean result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * content : 好厉害！太逼真了
             * hashId : 49D15B3C4BB6E83478B3ECC34154FF36
             * unixtime : 1480917918
             * updatetime : 2016-12-05 14:05:18
             * url : http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201612/05/49D15B3C4BB6E83478B3ECC34154FF36.gif
             */

            private String content;
            private String hashId;
            private int unixtime;
            private String updatetime;
            private String url;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getHashId() {
                return hashId;
            }

            public void setHashId(String hashId) {
                this.hashId = hashId;
            }

            public int getUnixtime() {
                return unixtime;
            }

            public void setUnixtime(int unixtime) {
                this.unixtime = unixtime;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
