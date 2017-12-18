package bwie.com.jdemo.bean;

import java.util.List;

/**
 * Created by ASUS on 2017/12/9.
 */

public class PingBean {

    /**
     * data : [{"uid":2513954815,"comment":"很好的一双鞋子，平时穿36的，这次买了37的有点大，还好店家送了鞋垫，穿着刚好，鞋子打开没有异味，店家在里面放了香包，打开就有一股淡淡的香味。","stars":0,"desc_score":5,"logistics_score":5,"service_score":5,"specs":"[{\"spec_id\":1,\"spec_key\":\"颜色\",\"spec_value\":\"黑色\"},{\"spec_id\":77529,\"spec_key\":\"尺码\",\"spec_value\":\"37标准码\"}]","time":1512469342,"avatar":"http://avatar.yangkeduo.com/a/4a91af91439765085c770b00ae75a8dc959c3628-1507610658?x-oss-process=image/resize,w_100","name":"左慧","pictures":[{"url":"http://pinduoduoimg.yangkeduo.com/review/2017-12-05/26f2f1fd865d7678f2a9beba9ca3ff53.jpeg","width":600,"height":800},{"url":"http://pinduoduoimg.yangkeduo.com/review/2017-12-05/abfb4dd9c7b6433b5c20159c719b6a4d.jpeg","width":600,"height":800},{"url":"http://pinduoduoimg.yangkeduo.com/review/2017-12-05/e670dca56f5864cf99eca93048b33b29.jpeg","width":600,"height":800}],"review_id":"127699239209479135"},{"uid":4332939209,"comment":"很喜欢的一双鞋子，和卖家描述的一样个人一直很喜欢运动休闲风，这双鞋上脚舒适保暖，不管走跳蹦跑都可以，喜欢可以购买，价格便宜","stars":0,"desc_score":5,"logistics_score":5,"service_score":5,"specs":"[{\"spec_id\":1,\"spec_key\":\"颜色\",\"spec_value\":\"黑色\"},{\"spec_id\":33339,\"spec_key\":\"尺码\",\"spec_value\":\"40标准码\"}]","time":1512538610,"avatar":"http://avatar.yangkeduo.com/a/62a481221d5083bae589a8c458b933cb6e31e500-1508804376?x-oss-process=image/resize,w_100","name":"洋洋","pictures":[],"review_id":"127844506127969247"}]
     * recommend_list : []
     * average : 0.0
     * number : 54
     * score_num : 97
     * labels : [{"id":2,"level":1,"name":"质量很好","num":10,"positive":1},{"id":746,"level":2,"name":"很舒服","num":5,"positive":1},{"id":3,"level":1,"name":"质量不错","num":3,"positive":1}]
     */

    private double average;
    private int number;
    private int score_num;
    private List<DataBean> data;
    private List<?> recommend_list;
    private List<LabelsBean> labels;

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getScore_num() {
        return score_num;
    }

    public void setScore_num(int score_num) {
        this.score_num = score_num;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<?> getRecommend_list() {
        return recommend_list;
    }

    public void setRecommend_list(List<?> recommend_list) {
        this.recommend_list = recommend_list;
    }

    public List<LabelsBean> getLabels() {
        return labels;
    }

    public void setLabels(List<LabelsBean> labels) {
        this.labels = labels;
    }

    public static class DataBean {
        /**
         * uid : 2513954815
         * comment : 很好的一双鞋子，平时穿36的，这次买了37的有点大，还好店家送了鞋垫，穿着刚好，鞋子打开没有异味，店家在里面放了香包，打开就有一股淡淡的香味。
         * stars : 0
         * desc_score : 5
         * logistics_score : 5
         * service_score : 5
         * specs : [{"spec_id":1,"spec_key":"颜色","spec_value":"黑色"},{"spec_id":77529,"spec_key":"尺码","spec_value":"37标准码"}]
         * time : 1512469342
         * avatar : http://avatar.yangkeduo.com/a/4a91af91439765085c770b00ae75a8dc959c3628-1507610658?x-oss-process=image/resize,w_100
         * name : 左慧
         * pictures : [{"url":"http://pinduoduoimg.yangkeduo.com/review/2017-12-05/26f2f1fd865d7678f2a9beba9ca3ff53.jpeg","width":600,"height":800},{"url":"http://pinduoduoimg.yangkeduo.com/review/2017-12-05/abfb4dd9c7b6433b5c20159c719b6a4d.jpeg","width":600,"height":800},{"url":"http://pinduoduoimg.yangkeduo.com/review/2017-12-05/e670dca56f5864cf99eca93048b33b29.jpeg","width":600,"height":800}]
         * review_id : 127699239209479135
         */

        private long uid;
        private String comment;
        private int stars;
        private int desc_score;
        private int logistics_score;
        private int service_score;
        private String specs;
        private int time;
        private String avatar;
        private String name;
        private String review_id;
        private List<PicturesBean> pictures;

        public long getUid() {
            return uid;
        }

        public void setUid(long uid) {
            this.uid = uid;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getStars() {
            return stars;
        }

        public void setStars(int stars) {
            this.stars = stars;
        }

        public int getDesc_score() {
            return desc_score;
        }

        public void setDesc_score(int desc_score) {
            this.desc_score = desc_score;
        }

        public int getLogistics_score() {
            return logistics_score;
        }

        public void setLogistics_score(int logistics_score) {
            this.logistics_score = logistics_score;
        }

        public int getService_score() {
            return service_score;
        }

        public void setService_score(int service_score) {
            this.service_score = service_score;
        }

        public String getSpecs() {
            return specs;
        }

        public void setSpecs(String specs) {
            this.specs = specs;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getReview_id() {
            return review_id;
        }

        public void setReview_id(String review_id) {
            this.review_id = review_id;
        }

        public List<PicturesBean> getPictures() {
            return pictures;
        }

        public void setPictures(List<PicturesBean> pictures) {
            this.pictures = pictures;
        }

        public static class PicturesBean {
            /**
             * url : http://pinduoduoimg.yangkeduo.com/review/2017-12-05/26f2f1fd865d7678f2a9beba9ca3ff53.jpeg
             * width : 600
             * height : 800
             */

            private String url;
            private int width;
            private int height;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }
        }
    }

    public static class LabelsBean {
        /**
         * id : 2
         * level : 1
         * name : 质量很好
         * num : 10
         * positive : 1
         */

        private int id;
        private int level;
        private String name;
        private int num;
        private int positive;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getPositive() {
            return positive;
        }

        public void setPositive(int positive) {
            this.positive = positive;
        }
    }
}
