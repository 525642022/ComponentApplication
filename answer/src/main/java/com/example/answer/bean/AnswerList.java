package com.example.answer.bean;

import java.util.List;

public class AnswerList {

    /**
     * status : {"code":"0","message":"success","cninfo":""}
     * data : [{"id":2,"points":100,"title":"法律法规第二套测试题","successed":0},{"id":1,"points":50,"title":"法律法规第一套测试题","successed":0}]
     */

    private StatusBean status;
    private List<DataBean> data;

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class StatusBean {
        /**
         * code : 0
         * message : success
         * cninfo :
         */

        private String code;
        private String message;
        private String cninfo;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCninfo() {
            return cninfo;
        }

        public void setCninfo(String cninfo) {
            this.cninfo = cninfo;
        }
    }

    public static class DataBean {
        /**
         * id : 2
         * points : 100
         * title : 法律法规第二套测试题
         * successed : 0
         */

        private String id;
        private int points;
        private String title;
        private int successed;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getSuccessed() {
            return successed;
        }

        public void setSuccessed(int successed) {
            this.successed = successed;
        }
    }
}
