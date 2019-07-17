package com.example.answer.bean;

public class AnswerPoint {

    /**
     * status : {"code":"0","message":"success","cninfo":""}
     * data : {"right":2,"wrong":8,"score":20}
     */

    private StatusBean status;
    private DataBean data;

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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
         * right : 2
         * wrong : 8
         * score : 20
         */

        private String right;
        private String wrong;
        private String score;

        public String getRight() {
            return right;
        }

        public void setRight(String right) {
            this.right = right;
        }

        public String getWrong() {
            return wrong;
        }

        public void setWrong(String wrong) {
            this.wrong = wrong;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }
    }
}
