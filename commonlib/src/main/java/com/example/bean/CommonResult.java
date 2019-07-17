package com.example.bean;

public class CommonResult {

    /**
     * status : {"code":"0","message":"success","cninfo":""}
     * data : {"detail":{"id":3,"title":"1123123123","store":3,"price":1,"points":2,"pic":"http://news-api.usingde.com/res/201903/i_1553655578122.png","content":"[{\"id\":\"227\", \"picUrl\":\"http://news-api.usingde.com/res/201903/1553656348704.png\",\"sort\":\"0\"},{\"id\":\"228\", \"picUrl\":\"http://news-api.usingde.com/res/201903/1553656330711.png\",\"sort\":\"1\"},{\"id\":\"229\", \"picUrl\":\"http://news-api.usingde.com/res/201903/1553656328160.png\",\"sort\":\"2\"}]","imgList":["http://news-api.usingde.com/res/201903/1553656348704.png","http://news-api.usingde.com/res/201903/1553656330711.png","http://news-api.usingde.com/res/201903/1553656328160.png"]}}
     */

    private StatusBean status;
    private Object data;

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
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

}
