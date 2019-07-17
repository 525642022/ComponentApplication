package com.example.me.bean;

import java.util.List;

public class OrderListBean {

    /**
     * status : {"code":"0","message":"success","cninfo":""}
     * data : [{"id":201903251414441226,"status":"WAIT_FINISHED","points":1000,"title":"商品1","receiverName":"张三","receiverMobile":"18910598421","province":"北京","city":"北京市","district":"朝阳区","address":"东区国际4号楼1202","logisticsCompany":null,"logisticsCode":null,"pic":"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg","num":1,"type":1,"addressDetail":"收件信息：北京北京市朝阳区东区国际4号楼1202","codeInfo":"订单号：201903251414441226"}]
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
         * id : 201903251414441226
         * status : WAIT_FINISHED
         * points : 1000
         * title : 商品1
         * receiverName : 张三
         * receiverMobile : 18910598421
         * province : 北京
         * city : 北京市
         * district : 朝阳区
         * address : 东区国际4号楼1202
         * logisticsCompany : null
         * logisticsCode : null
         * pic : https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg
         * num : 1
         * type : 1
         * addressDetail : 收件信息：北京北京市朝阳区东区国际4号楼1202
         * codeInfo : 订单号：201903251414441226
         */

        private long id;
        private String status;
        private int points;
        private String title;
        private String receiverName;
        private String receiverMobile;
        private String province;
        private String city;
        private String district;
        private String address;
        private Object logisticsCompany;
        private Object logisticsCode;
        private String pic;
        private int num;
        private int type;
        private String addressDetail;
        private String codeInfo;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getReceiverName() {
            return receiverName;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }

        public String getReceiverMobile() {
            return receiverMobile;
        }

        public void setReceiverMobile(String receiverMobile) {
            this.receiverMobile = receiverMobile;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getLogisticsCompany() {
            return logisticsCompany;
        }

        public void setLogisticsCompany(Object logisticsCompany) {
            this.logisticsCompany = logisticsCompany;
        }

        public Object getLogisticsCode() {
            return logisticsCode;
        }

        public void setLogisticsCode(Object logisticsCode) {
            this.logisticsCode = logisticsCode;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public String getCodeInfo() {
            return codeInfo;
        }

        public void setCodeInfo(String codeInfo) {
            this.codeInfo = codeInfo;
        }
    }
}
