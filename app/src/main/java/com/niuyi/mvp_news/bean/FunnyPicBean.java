package com.niuyi.mvp_news.bean;

import java.util.List;

/**
 * 作者：${牛毅} on 2016/12/7 15:00
 * 邮箱：niuyi19900923@hotmail.com
 */
public class FunnyPicBean {

    /**
     * error_code : 0
     * reason : Success
     * result : {"data":[{"content":"加油 你是最胖的！","hashId":"8FE9FF8207DB688410ADD06FADE23963","unixtime":1481011508,"updatetime":"2016-12-06 16:05:08","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201612/06/8FE9FF8207DB688410ADD06FADE23963.png"},{"content":"餐馆老板庆祝成功离婚 心情愉快!全场7.6折","hashId":"AE0BB1693DD6D3B95793161178FABF29","unixtime":1481011508,"updatetime":"2016-12-06 16:05:08","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201612/06/AE0BB1693DD6D3B95793161178FABF29.jpg"},{"content":"阿姨的意思可能是多干几次！","hashId":"37961CA27F6913444E9172E42A298E13","unixtime":1481011508,"updatetime":"2016-12-06 16:05:08","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201612/06/37961CA27F6913444E9172E42A298E13.jpg"},{"content":"这鱼丸汤好新鲜啊！","hashId":"EDCC0C1C65A61F86A3DCDB0980956514","unixtime":1481011508,"updatetime":"2016-12-06 16:05:08","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201612/06/EDCC0C1C65A61F86A3DCDB0980956514.gif"},{"content":"为什么受伤的总是我","hashId":"FF2BC16BF887CA4DA327805D78466CE7","unixtime":1481011508,"updatetime":"2016-12-06 16:05:08","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201612/06/FF2BC16BF887CA4DA327805D78466CE7.jpg"},{"content":"免费洗车","hashId":"F857507607C2D6F5F24BD7AC752E4514","unixtime":1481011508,"updatetime":"2016-12-06 16:05:08","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201612/06/F857507607C2D6F5F24BD7AC752E4514.gif"},{"content":"我好像懂了","hashId":"33BC843A73EA168B8F9B235AAD733234","unixtime":1481011508,"updatetime":"2016-12-06 16:05:08","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201612/06/33BC843A73EA168B8F9B235AAD733234.jpg"},{"content":"谁家的猫的尾巴掉了","hashId":"30B3B72547490568C6A6C2C0509A1757","unixtime":1481011508,"updatetime":"2016-12-06 16:05:08","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201612/06/30B3B72547490568C6A6C2C0509A1757.png"},{"content":"男女主播藏进商场衣橱欲过夜，称回忆童年躲猫猫","hashId":"399A6526D710339BB6C8103ED00E21E2","unixtime":1481011508,"updatetime":"2016-12-06 16:05:08","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201612/06/399A6526D710339BB6C8103ED00E21E2.png"},{"content":"在路上练习排水沟过弯，正在要超车时悲剧了","hashId":"323EB7FD0B23619E235AC844CF5A9499","unixtime":1481011508,"updatetime":"2016-12-06 16:05:08","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201612/06/323EB7FD0B23619E235AC844CF5A9499.gif"}]}
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
             * content : 加油 你是最胖的！
             * hashId : 8FE9FF8207DB688410ADD06FADE23963
             * unixtime : 1481011508
             * updatetime : 2016-12-06 16:05:08
             * url : http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201612/06/8FE9FF8207DB688410ADD06FADE23963.png
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
