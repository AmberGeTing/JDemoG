package bwie.com.jdemo.bean;

import java.util.List;

/**
 * Created by ASUS on 2017/12/14.
 */

public class TuiJianBean {

    /**
     * server_time : 1513216235
     * list : [{"goods_id":309291536,"goods_name":"【不开胶不断底】内增高女鞋2017秋冬季新款百搭韩版加绒棉鞋休闲鞋刺绣厚底松糕鞋女潮","short_name":"【不开胶不断底】内增高女鞋2017秋冬季新款百搭韩版加绒棉鞋休闲鞋刺绣厚底松糕鞋女潮","thumb_url":"http://omsproductionimg.yangkeduo.com/images/2017-12-11/d63e021d7cc8b5a534aff3ccba422360.jpeg","hd_thumb_url":"http://omsproductionimg.yangkeduo.com/images/2017-12-11/cb1af3554b037a5a315e598486b75aee.jpeg","is_app":0,"event_type":0,"goods_type":1,"customer_num":2,"sales":8,"price":5600,"normal_price":6500,"market_price":29800,"min_on_sale_group_price":5600,"p_rec":{"t":"default","g":"OLD","bk":"19","m":null},"visible":true,"rec_visible":true},{"goods_id":280654783,"goods_name":"毛毛鞋保暖棉鞋板鞋休闲鞋韩版百搭内增高系带平底加绒厚底保暖学生高帮鞋女圆头防滑板鞋","short_name":"毛毛鞋保暖棉鞋板鞋休闲鞋韩版百搭内增高系带平底加绒厚底保暖学生高帮鞋女圆头防滑板鞋","thumb_url":"http://omsproductionimg.yangkeduo.com/images/2017-12-04/99ded2c631a7c8a0791dd409de857af9.jpeg","hd_thumb_url":"http://omsproductionimg.yangkeduo.com/images/2017-12-04/9e060118a4abe04386587e0fb5278a31.jpeg","is_app":0,"event_type":0,"goods_type":1,"customer_num":2,"sales":12,"price":5500,"normal_price":5800,"market_price":39800,"min_on_sale_group_price":5500,"p_rec":{"t":"default","g":"OLD","bk":"19","m":null},"visible":true,"rec_visible":true},{"goods_id":278660920,"goods_name":"2017冬新款加绒加厚雪地靴女厚底短筒牛反绒棉鞋平底铆钉短靴百搭学生棉鞋保暖加绒低帮潮","short_name":"2017冬新款加绒加厚雪地靴女厚底短筒牛反绒棉鞋平底铆钉短靴百搭学生棉鞋保暖加绒低帮潮","thumb_url":"http://omsproductionimg.yangkeduo.com/images/2017-12-04/044b9658ca6415bbb9984fe9155dbc97.jpeg","hd_thumb_url":"http://omsproductionimg.yangkeduo.com/images/2017-12-04/4cc02af170cfe0555b19ded3cd889597.jpeg","is_app":0,"event_type":0,"goods_type":1,"customer_num":2,"sales":2,"price":3990,"normal_price":4500,"market_price":29800,"min_on_sale_group_price":3990,"p_rec":{"t":"default","g":"OLD","bk":"19","m":null},"visible":true,"rec_visible":true}]
     */

    private int server_time;
    private List<ListBean> list;

    public int getServer_time() {
        return server_time;
    }

    public void setServer_time(int server_time) {
        this.server_time = server_time;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * goods_id : 309291536
         * goods_name : 【不开胶不断底】内增高女鞋2017秋冬季新款百搭韩版加绒棉鞋休闲鞋刺绣厚底松糕鞋女潮
         * short_name : 【不开胶不断底】内增高女鞋2017秋冬季新款百搭韩版加绒棉鞋休闲鞋刺绣厚底松糕鞋女潮
         * thumb_url : http://omsproductionimg.yangkeduo.com/images/2017-12-11/d63e021d7cc8b5a534aff3ccba422360.jpeg
         * hd_thumb_url : http://omsproductionimg.yangkeduo.com/images/2017-12-11/cb1af3554b037a5a315e598486b75aee.jpeg
         * is_app : 0
         * event_type : 0
         * goods_type : 1
         * customer_num : 2
         * sales : 8
         * price : 5600
         * normal_price : 6500
         * market_price : 29800
         * min_on_sale_group_price : 5600
         * p_rec : {"t":"default","g":"OLD","bk":"19","m":null}
         * visible : true
         * rec_visible : true
         */

        private int goods_id;
        private String goods_name;
        private String short_name;
        private String thumb_url;
        private String hd_thumb_url;
        private int is_app;
        private int event_type;
        private int goods_type;
        private int customer_num;
        private int sales;
        private int price;
        private int normal_price;
        private int market_price;
        private int min_on_sale_group_price;
        private PRecBean p_rec;
        private boolean visible;
        private boolean rec_visible;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getShort_name() {
            return short_name;
        }

        public void setShort_name(String short_name) {
            this.short_name = short_name;
        }

        public String getThumb_url() {
            return thumb_url;
        }

        public void setThumb_url(String thumb_url) {
            this.thumb_url = thumb_url;
        }

        public String getHd_thumb_url() {
            return hd_thumb_url;
        }

        public void setHd_thumb_url(String hd_thumb_url) {
            this.hd_thumb_url = hd_thumb_url;
        }

        public int getIs_app() {
            return is_app;
        }

        public void setIs_app(int is_app) {
            this.is_app = is_app;
        }

        public int getEvent_type() {
            return event_type;
        }

        public void setEvent_type(int event_type) {
            this.event_type = event_type;
        }

        public int getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(int goods_type) {
            this.goods_type = goods_type;
        }

        public int getCustomer_num() {
            return customer_num;
        }

        public void setCustomer_num(int customer_num) {
            this.customer_num = customer_num;
        }

        public int getSales() {
            return sales;
        }

        public void setSales(int sales) {
            this.sales = sales;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getNormal_price() {
            return normal_price;
        }

        public void setNormal_price(int normal_price) {
            this.normal_price = normal_price;
        }

        public int getMarket_price() {
            return market_price;
        }

        public void setMarket_price(int market_price) {
            this.market_price = market_price;
        }

        public int getMin_on_sale_group_price() {
            return min_on_sale_group_price;
        }

        public void setMin_on_sale_group_price(int min_on_sale_group_price) {
            this.min_on_sale_group_price = min_on_sale_group_price;
        }

        public PRecBean getP_rec() {
            return p_rec;
        }

        public void setP_rec(PRecBean p_rec) {
            this.p_rec = p_rec;
        }

        public boolean isVisible() {
            return visible;
        }

        public void setVisible(boolean visible) {
            this.visible = visible;
        }

        public boolean isRec_visible() {
            return rec_visible;
        }

        public void setRec_visible(boolean rec_visible) {
            this.rec_visible = rec_visible;
        }

        public static class PRecBean {
            /**
             * t : default
             * g : OLD
             * bk : 19
             * m : null
             */

            private String t;
            private String g;
            private String bk;
            private Object m;

            public String getT() {
                return t;
            }

            public void setT(String t) {
                this.t = t;
            }

            public String getG() {
                return g;
            }

            public void setG(String g) {
                this.g = g;
            }

            public String getBk() {
                return bk;
            }

            public void setBk(String bk) {
                this.bk = bk;
            }

            public Object getM() {
                return m;
            }

            public void setM(Object m) {
                this.m = m;
            }
        }
    }
}
