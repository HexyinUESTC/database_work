package com.example.myshop.bean;


import java.io.Serializable;
import java.util.List;

//public class ResultBeanData {
//
//    /**
//     * msg : 请求数据成功
//     * success : true
//     * details : {"bannerproductList":[{"id":1,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"\\baby_1.jpg"},{"id":2,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"\\baby_1.jpg"},{"id":3,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"\\baby_1.jpg"},{"id":4,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"\\baby_1.jpg"},{"id":5,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"\\baby_1.jpg"}],"seckillproductList":[{"id":1,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"\\baby_1.jpg"},{"id":2,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"\\baby_1.jpg"},{"id":3,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"\\baby_1.jpg"},{"id":4,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"\\baby_1.jpg"},{"id":5,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"\\baby_1.jpg"}],"recommendproductList":[{"id":1,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"\\baby_1.jpg"},{"id":2,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"\\baby_1.jpg"},{"id":3,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"\\baby_1.jpg"},{"id":4,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"\\baby_1.jpg"},{"id":5,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"\\baby_1.jpg"}],"hotproductList":[{"id":1,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"\\baby_1.jpg"},{"id":2,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"\\baby_1.jpg"},{"id":3,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"\\baby_1.jpg"},{"id":4,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"\\baby_1.jpg"},{"id":5,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"\\baby_1.jpg"}]}
//     */
//
//    private String msg;
//    private boolean success;
//    private DetailsBean details;
//
//    @Override
//    public String toString() {
//        return "ResultBeanData{" +
//                "msg='" + msg + '\'' +
//                ", success=" + success +
//                ", details=" + details +
//                '}';
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public boolean isSuccess() {
//        return success;
//    }
//
//    public void setSuccess(boolean success) {
//        this.success = success;
//    }
//
//    public DetailsBean getDetails() {
//        return details;
//    }
//
//    public void setDetails(DetailsBean details) {
//        this.details = details;
//    }
//
//    public static class DetailsBean {
//        private List<BannerproductListBean> bannerproductList;
//        private List<SeckillproductListBean> seckillproductList;
//        private List<RecommendproductListBean> recommendproductList;
//        private Hotproduct hotproduct;
//
//        @Override
//        public String toString() {
//            return "DetailsBean{" +
//                    "bannerproductList=" + bannerproductList +
//                    ", seckillproductList=" + seckillproductList +
//                    ", recommendproductList=" + recommendproductList +
//                    ", hotproduct=" + hotproduct +
//                    '}';
//        }
//
//        public List<BannerproductListBean> getBannerproductList() {
//            return bannerproductList;
//        }
//
//        public void setBannerproductList(List<BannerproductListBean> bannerproductList) {
//            this.bannerproductList = bannerproductList;
//        }
//
//        public List<SeckillproductListBean> getSeckillproductList() {
//            return seckillproductList;
//        }
//
//        public void setSeckillproductList(List<SeckillproductListBean> seckillproductList) {
//            this.seckillproductList = seckillproductList;
//        }
//
//        public List<RecommendproductListBean> getRecommendproductList() {
//            return recommendproductList;
//        }
//
//        public void setRecommendproductList(List<RecommendproductListBean> recommendproductList) {
//            this.recommendproductList = recommendproductList;
//        }
//
//        public Hotproduct getHotproduct() {
//            return hotproduct;
//        }
//
//        public void setHotproduct(Hotproduct hotproduct) {
//            this.hotproduct = hotproduct;
//        }
//        public static class Hotproduct {
//
//            private int startTime;
//            private int endTime;
//            private List<HotproductListBean> hotproductListBeanList;
//
//            @Override
//            public String toString() {
//                return "Hotproduct{" +
//                        "startTime=" + startTime +
//                        ", endTime=" + endTime +
//                        ", hotproductListBeanList=" + hotproductListBeanList +
//                        '}';
//            }
//
//            public int getStartTime() {
//                return startTime;
//            }
//
//            public void setStartTime(int startTime) {
//                this.startTime = startTime;
//            }
//
//            public int getEndTime() {
//                return endTime;
//            }
//
//            public void setEndTime(int endTime) {
//                this.endTime = endTime;
//            }
//
//            public List<HotproductListBean> getHotproductListBeanList() {
//                return hotproductListBeanList;
//            }
//
//            public void setHotproductListBeanList(List<HotproductListBean> hotproductListBeanList) {
//                this.hotproductListBeanList = hotproductListBeanList;
//            }
//            public static class HotproductListBean {
//                @Override
//                public String toString() {
//                    return "HotproductListBean{" +
//                            "id=" + id +
//                            ", name='" + name + '\'' +
//                            ", description='" + description + '\'' +
//                            ", price=" + price +
//                            ", stock=" + stock +
//                            ", fileName='" + fileName + '\'' +
//                            '}';
//                }
//
//                /**
//                 * id : 1
//                 * name : 花椒
//                 * description : 花椒
//                 * price : 100
//                 * stock : 100
//                 * fileName : \baby_1.jpg
//                 */
//
//                private int id;
//                private String name;
//                private String description;
//                private int price;
//                private int stock;
//                private String fileName;
//
//                public int getId() {
//                    return id;
//                }
//
//                public void setId(int id) {
//                    this.id = id;
//                }
//
//                public String getName() {
//                    return name;
//                }
//
//                public void setName(String name) {
//                    this.name = name;
//                }
//
//                public String getDescription() {
//                    return description;
//                }
//
//                public void setDescription(String description) {
//                    this.description = description;
//                }
//
//                public int getPrice() {
//                    return price;
//                }
//
//                public void setPrice(int price) {
//                    this.price = price;
//                }
//
//                public int getStock() {
//                    return stock;
//                }
//
//                public void setStock(int stock) {
//                    this.stock = stock;
//                }
//
//                public String getFileName() {
//                    return fileName;
//                }
//
//                public void setFileName(String fileName) {
//                    this.fileName = fileName;
//                }
//            }
//        }
//        public static class BannerproductListBean {
//            /**
//             * id : 1
//             * name : 花椒
//             * description : 花椒
//             * price : 100
//             * stock : 100
//             * fileName : \baby_1.jpg
//             */
//
//            private int id;
//            private String name;
//            private String description;
//            private int price;
//            private int stock;
//            private String fileName;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getDescription() {
//                return description;
//            }
//
//            public void setDescription(String description) {
//                this.description = description;
//            }
//
//            public int getPrice() {
//                return price;
//            }
//
//            public void setPrice(int price) {
//                this.price = price;
//            }
//
//            public int getStock() {
//                return stock;
//            }
//
//            public void setStock(int stock) {
//                this.stock = stock;
//            }
//
//            public String getFileName() {
//                return fileName;
//            }
//
//            public void setFileName(String fileName) {
//                this.fileName = fileName;
//            }
//        }
//
//        public static class SeckillproductListBean {
//            /**
//             * id : 1
//             * name : 花椒
//             * description : 花椒
//             * price : 100
//             * stock : 100
//             * fileName : \baby_1.jpg
//             */
//
//            private int id;
//            private String name;
//            private String description;
//            private int price;
//            private int stock;
//            private String fileName;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getDescription() {
//                return description;
//            }
//
//            public void setDescription(String description) {
//                this.description = description;
//            }
//
//            public int getPrice() {
//                return price;
//            }
//
//            public void setPrice(int price) {
//                this.price = price;
//            }
//
//            public int getStock() {
//                return stock;
//            }
//
//            public void setStock(int stock) {
//                this.stock = stock;
//            }
//
//            public String getFileName() {
//                return fileName;
//            }
//
//            public void setFileName(String fileName) {
//                this.fileName = fileName;
//            }
//        }
//
//        public static class RecommendproductListBean {
//            /**
//             * id : 1
//             * name : 花椒
//             * description : 花椒
//             * price : 100
//             * stock : 100
//             * fileName : \baby_1.jpg
//             */
//
//            private int id;
//            private String name;
//            private String description;
//            private int price;
//            private int stock;
//            private String fileName;
//
//            @Override
//            public String toString() {
//                return "RecommendproductListBean{" +
//                        "id=" + id +
//                        ", name='" + name + '\'' +
//                        ", description='" + description + '\'' +
//                        ", price=" + price +
//                        ", stock=" + stock +
//                        ", fileName='" + fileName + '\'' +
//                        '}';
//            }
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getDescription() {
//                return description;
//            }
//
//            public void setDescription(String description) {
//                this.description = description;
//            }
//
//            public int getPrice() {
//                return price;
//            }
//
//            public void setPrice(int price) {
//                this.price = price;
//            }
//
//            public int getStock() {
//                return stock;
//            }
//
//            public void setStock(int stock) {
//                this.stock = stock;
//            }
//
//            public String getFileName() {
//                return fileName;
//            }
//
//            public void setFileName(String fileName) {
//                this.fileName = fileName;
//            }
//        }
//    }
//}
public class ResultBeanData implements Serializable {
    /**
     * msg : 请求数据成功
     * success : true
     * details : {"bannerproductList":[{"id":1,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":2,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":3,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":4,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":5,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"}],"seckillproductList":[{"id":1,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":2,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":3,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":4,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":5,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"}],"recommendproductList":[{"id":1,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":2,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":3,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":4,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":5,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"}],"hotproduct":{"start_time":0,"end_time":3600,"hotproductList":[{"id":1,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":2,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":3,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":4,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":5,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"}]}}
     */

    private String msg;
    private boolean success;
    private DetailsBean details;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DetailsBean getDetails() {
        return details;
    }

    public void setDetails(DetailsBean details) {
        this.details = details;
    }

    public static class DetailsBean {
        /**
         * bannerproductList : [{"id":1,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":2,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":3,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":4,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":5,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"}]
         * seckillproductList : [{"id":1,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":2,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":3,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":4,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":5,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"}]
         * recommendproductList : [{"id":1,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":2,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":3,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":4,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":5,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"}]
         * hotproduct : {"start_time":0,"end_time":3600,"hotproductList":[{"id":1,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":2,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":3,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":4,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":5,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"}]}
         */

        private HotproductBean hotproduct;
        private List<BannerproductListBean> bannerproductList;
        private List<SeckillproductListBean> seckillproductList;
        private List<RecommendproductListBean> recommendproductList;

        public HotproductBean getHotproduct() {
            return hotproduct;
        }

        public void setHotproduct(HotproductBean hotproduct) {
            this.hotproduct = hotproduct;
        }

        public List<BannerproductListBean> getBannerproductList() {
            return bannerproductList;
        }

        public void setBannerproductList(List<BannerproductListBean> bannerproductList) {
            this.bannerproductList = bannerproductList;
        }

        public List<SeckillproductListBean> getSeckillproductList() {
            return seckillproductList;
        }

        public void setSeckillproductList(List<SeckillproductListBean> seckillproductList) {
            this.seckillproductList = seckillproductList;
        }

        public List<RecommendproductListBean> getRecommendproductList() {
            return recommendproductList;
        }

        public void setRecommendproductList(List<RecommendproductListBean> recommendproductList) {
            this.recommendproductList = recommendproductList;
        }

        public static class HotproductBean {
            /**
             * start_time : 0
             * end_time : 3600
             * hotproductList : [{"id":1,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":2,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":3,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":4,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":5,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"}]
             */

            private int start_time;
            private int end_time;
            private List<HotproductListBean> hotproductList;

            public int getStart_time() {
                return start_time;
            }

            public void setStart_time(int start_time) {
                this.start_time = start_time;
            }

            public int getEnd_time() {
                return end_time;
            }

            public void setEnd_time(int end_time) {
                this.end_time = end_time;
            }

            public List<HotproductListBean> getHotproductList() {
                return hotproductList;
            }

            public void setHotproductList(List<HotproductListBean> hotproductList) {
                this.hotproductList = hotproductList;
            }

            public static class HotproductListBean {
                /**
                 * id : 1
                 * name : 花椒
                 * description : 花椒
                 * price : 100
                 * stock : 100
                 * fileName : /baby_1.jpg
                 */

                private int id;
                private String name;
                private String description;
                private int price;
                private int stock;
                private String fileName;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public int getStock() {
                    return stock;
                }

                public void setStock(int stock) {
                    this.stock = stock;
                }

                public String getFileName() {
                    return fileName;
                }

                public void setFileName(String fileName) {
                    this.fileName = fileName;
                }
            }
        }

        public static class BannerproductListBean {
            /**
             * id : 1
             * name : 花椒
             * description : 花椒
             * price : 100
             * stock : 100
             * fileName : /baby_1.jpg
             */

            private int id;
            private String name;
            private String description;
            private int price;
            private int stock;
            private String fileName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public String getFileName() {
                return fileName;
            }

            public void setFileName(String fileName) {
                this.fileName = fileName;
            }
        }

        public static class SeckillproductListBean {
            /**
             * id : 1
             * name : 花椒
             * description : 花椒
             * price : 100
             * stock : 100
             * fileName : /baby_1.jpg
             */

            private int id;
            private String name;
            private String description;
            private int price;
            private int stock;
            private String fileName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public String getFileName() {
                return fileName;
            }

            public void setFileName(String fileName) {
                this.fileName = fileName;
            }
        }

        public static class RecommendproductListBean {
            /**
             * id : 1
             * name : 花椒
             * description : 花椒
             * price : 100
             * stock : 100
             * fileName : /baby_1.jpg
             */

            private int id;
            private String name;
            private String description;
            private int price;
            private int stock;
            private String fileName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public String getFileName() {
                return fileName;
            }

            public void setFileName(String fileName) {
                this.fileName = fileName;
            }
        }
    }

//    /**
//     * msg : 请求数据成功
//     * success : true
//     * details : {"bannerproductList":[{"id":1,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":2,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":3,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":4,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":5,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"}],"seckillproductList":[{"id":1,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":2,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":3,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":4,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":5,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"}],"recommendproductList":[{"id":1,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":2,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":3,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":4,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":5,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"}],"hotproductList":{"start_time":0,"end_time":3600,"hotproductList":[{"id":1,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":2,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":3,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":4,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":5,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"}]}}
//     */
//
//    private String msg;
//    private boolean success;
//    private DetailsBean details;
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public boolean isSuccess() {
//        return success;
//    }
//
//    public void setSuccess(boolean success) {
//        this.success = success;
//    }
//
//    public DetailsBean getDetails() {
//        return details;
//    }
//
//    public void setDetails(DetailsBean details) {
//        this.details = details;
//    }
//
//    public static class DetailsBean {
//        /**
//         * bannerproductList : [{"id":1,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":2,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":3,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":4,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":5,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"}]
//         * seckillproductList : [{"id":1,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":2,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":3,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":4,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":5,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"}]
//         * recommendproductList : [{"id":1,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":2,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":3,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":4,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":5,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"}]
//         * hotproductList : {"start_time":0,"end_time":3600,"hotproductList":[{"id":1,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":2,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":3,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":4,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":5,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"}]}
//         */
//
//        private HotproductListBeanX hotproducX;
//        private List<BannerproductListBean> bannerproductList;
//        private List<SeckillproductListBean> seckillproductList;
//        private List<RecommendproductListBean> recommendproductList;
//
//        public HotproductListBeanX getHotproductX() {
//            return hotproducX;
//        }
//
//        public void setHotproductX(HotproductListBeanX hotproducX) {
//            this.hotproducX = hotproducX;
//        }
//
//        public List<BannerproductListBean> getBannerproductList() {
//            return bannerproductList;
//        }
//
//        public void setBannerproductList(List<BannerproductListBean> bannerproductList) {
//            this.bannerproductList = bannerproductList;
//        }
//
//        public List<SeckillproductListBean> getSeckillproductList() {
//            return seckillproductList;
//        }
//
//        public void setSeckillproductList(List<SeckillproductListBean> seckillproductList) {
//            this.seckillproductList = seckillproductList;
//        }
//
//        public List<RecommendproductListBean> getRecommendproductList() {
//            return recommendproductList;
//        }
//
//        public void setRecommendproductList(List<RecommendproductListBean> recommendproductList) {
//            this.recommendproductList = recommendproductList;
//        }
//
//        public static class HotproductListBeanX {
//            /**
//             * start_time : 0
//             * end_time : 3600
//             * hotproductList : [{"id":1,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":2,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":3,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":4,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"},{"id":5,"name":"花椒","description":"花椒","price":100,"stock":100,"fileName":"/baby_1.jpg"}]
//             */
//
//            private int start_time;
//            private int end_time;
//            private List<HotproductListBean> hotproductList;
//
//            public int getStart_time() {
//                return start_time;
//            }
//
//            public void setStart_time(int start_time) {
//                this.start_time = start_time;
//            }
//
//            public int getEnd_time() {
//                return end_time;
//            }
//
//            public void setEnd_time(int end_time) {
//                this.end_time = end_time;
//            }
//
//            public List<HotproductListBean> getHotproductList() {
//                return hotproductList;
//            }
//
//            public void setHotproductList(List<HotproductListBean> hotproductList) {
//                this.hotproductList = hotproductList;
//            }
//
//            public static class HotproductListBean {
//                /**
//                 * id : 1
//                 * name : 花椒
//                 * description : 花椒
//                 * price : 100
//                 * stock : 100
//                 * fileName : /baby_1.jpg
//                 */
//
//                private int id;
//                private String name;
//                private String description;
//                private int price;
//                private int stock;
//                private String fileName;
//
//                public int getId() {
//                    return id;
//                }
//
//                public void setId(int id) {
//                    this.id = id;
//                }
//
//                public String getName() {
//                    return name;
//                }
//
//                public void setName(String name) {
//                    this.name = name;
//                }
//
//                public String getDescription() {
//                    return description;
//                }
//
//                public void setDescription(String description) {
//                    this.description = description;
//                }
//
//                public int getPrice() {
//                    return price;
//                }
//
//                public void setPrice(int price) {
//                    this.price = price;
//                }
//
//                public int getStock() {
//                    return stock;
//                }
//
//                public void setStock(int stock) {
//                    this.stock = stock;
//                }
//
//                public String getFileName() {
//                    return fileName;
//                }
//
//                public void setFileName(String fileName) {
//                    this.fileName = fileName;
//                }
//            }
//        }
//
//        public static class BannerproductListBean {
//            /**
//             * id : 1
//             * name : 花椒
//             * description : 花椒
//             * price : 100
//             * stock : 100
//             * fileName : /baby_1.jpg
//             */
//
//            private int id;
//            private String name;
//            private String description;
//            private int price;
//            private int stock;
//            private String fileName;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getDescription() {
//                return description;
//            }
//
//            public void setDescription(String description) {
//                this.description = description;
//            }
//
//            public int getPrice() {
//                return price;
//            }
//
//            public void setPrice(int price) {
//                this.price = price;
//            }
//
//            public int getStock() {
//                return stock;
//            }
//
//            public void setStock(int stock) {
//                this.stock = stock;
//            }
//
//            public String getFileName() {
//                return fileName;
//            }
//
//            public void setFileName(String fileName) {
//                this.fileName = fileName;
//            }
//        }
//
//        public static class SeckillproductListBean {
//            /**
//             * id : 1
//             * name : 花椒
//             * description : 花椒
//             * price : 100
//             * stock : 100
//             * fileName : /baby_1.jpg
//             */
//
//            private int id;
//            private String name;
//            private String description;
//            private int price;
//            private int stock;
//            private String fileName;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getDescription() {
//                return description;
//            }
//
//            public void setDescription(String description) {
//                this.description = description;
//            }
//
//            public int getPrice() {
//                return price;
//            }
//
//            public void setPrice(int price) {
//                this.price = price;
//            }
//
//            public int getStock() {
//                return stock;
//            }
//
//            public void setStock(int stock) {
//                this.stock = stock;
//            }
//
//            public String getFileName() {
//                return fileName;
//            }
//
//            public void setFileName(String fileName) {
//                this.fileName = fileName;
//            }
//        }
//
//        public static class RecommendproductListBean {
//            /**
//             * id : 1
//             * name : 花椒
//             * description : 花椒
//             * price : 100
//             * stock : 100
//             * fileName : /baby_1.jpg
//             */
//
//            private int id;
//            private String name;
//            private String description;
//            private int price;
//            private int stock;
//            private String fileName;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getDescription() {
//                return description;
//            }
//
//            public void setDescription(String description) {
//                this.description = description;
//            }
//
//            public int getPrice() {
//                return price;
//            }
//
//            public void setPrice(int price) {
//                this.price = price;
//            }
//
//            public int getStock() {
//                return stock;
//            }
//
//            public void setStock(int stock) {
//                this.stock = stock;
//            }
//
//            public String getFileName() {
//                return fileName;
//            }
//
//            public void setFileName(String fileName) {
//                this.fileName = fileName;
//            }
//        }
//    }




}