package com.example.bbacr.ddw.bean;

import java.util.List;

/**
 * Created by WangYeTong on 2017/9/21.
 * Email:wytaper495@qq.com
 */

public class JsonHome {

    /**
     * floorModels : [{"DefaultTabName":"首页","Id":0,"Index":0,"Name":"首页","Products":[],"StyleLevel":1,"SubName":null,"Tabs":[{"Detail":[{"ImagePath":"/Storage/Shop/1/Products/36","Name":"进口纯牛奶","Price":200,"ProductId":36},{"ImagePath":"/Storage/Shop/1/Products/37","Name":"进口坚果","Price":5,"ProductId":37},{"ImagePath":"/Storage/Shop/1/Products/38","Name":"进口曲奇","Price":15,"ProductId":38},{"ImagePath":"/Storage/Shop/1/Products/39","Name":"进口橄榄油","Price":10,"ProductId":39},{"ImagePath":"/Storage/Shop/1/Products/40","Name":"洗面奶","Price":50,"ProductId":40},{"ImagePath":"/Storage/Shop/1/Products/41","Name":"漱口水","Price":50,"ProductId":41},{"ImagePath":"/Storage/Shop/1/Products/42","Name":"护垫","Price":20,"ProductId":42},{"ImagePath":"/Storage/Shop/1/Products/43","Name":"男士洗面奶","Price":50,"ProductId":43},{"ImagePath":"/Storage/Shop/1/Products/44","Name":"剪刀","Price":5,"ProductId":44},{"ImagePath":"/Storage/Shop/1/Products/45","Name":"枕头","Price":25,"ProductId":45},{"ImagePath":"/Storage/Shop/1/Products/46","Name":"电饭煲","Price":40,"ProductId":46},{"ImagePath":"/Storage/Shop/1/Products/47","Name":"衣架","Price":2,"ProductId":47}],"Id":0,"Name":"首页tab"}]}]
     * SiteName : 智慧园区
     * slideImage : [{"Description":null,"DisplaySequence":7,"Id":36,"ImageUrl":"/Storage/Plat/ImageAd/201705171625544679420.jpg","ShopId":0,"TypeId":1,"Url":"/"},{"Description":null,"DisplaySequence":9,"Id":35,"ImageUrl":"/Storage/Plat/ImageAd/201706061334254295330.jpg","ShopId":0,"TypeId":1,"Url":"/"}]
     * Title : 智慧园区
     */

    private String SiteName;
    private String Title;
    private List<FloorModelsBean> floorModels;
    private List<SlideImageBean> slideImage;

    public String getSiteName() {
        return SiteName;
    }

    public void setSiteName(String SiteName) {
        this.SiteName = SiteName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public List<FloorModelsBean> getFloorModels() {
        return floorModels;
    }

    public void setFloorModels(List<FloorModelsBean> floorModels) {
        this.floorModels = floorModels;
    }

    public List<SlideImageBean> getSlideImage() {
        return slideImage;
    }

    public void setSlideImage(List<SlideImageBean> slideImage) {
        this.slideImage = slideImage;
    }

    public static class FloorModelsBean {
        /**
         * DefaultTabName : 首页
         * Id : 0
         * Index : 0
         * Name : 首页
         * Products : []
         * StyleLevel : 1
         * SubName : null
         * Tabs : [{"Detail":[{"ImagePath":"/Storage/Shop/1/Products/36","Name":"进口纯牛奶","Price":200,"ProductId":36},{"ImagePath":"/Storage/Shop/1/Products/37","Name":"进口坚果","Price":5,"ProductId":37},{"ImagePath":"/Storage/Shop/1/Products/38","Name":"进口曲奇","Price":15,"ProductId":38},{"ImagePath":"/Storage/Shop/1/Products/39","Name":"进口橄榄油","Price":10,"ProductId":39},{"ImagePath":"/Storage/Shop/1/Products/40","Name":"洗面奶","Price":50,"ProductId":40},{"ImagePath":"/Storage/Shop/1/Products/41","Name":"漱口水","Price":50,"ProductId":41},{"ImagePath":"/Storage/Shop/1/Products/42","Name":"护垫","Price":20,"ProductId":42},{"ImagePath":"/Storage/Shop/1/Products/43","Name":"男士洗面奶","Price":50,"ProductId":43},{"ImagePath":"/Storage/Shop/1/Products/44","Name":"剪刀","Price":5,"ProductId":44},{"ImagePath":"/Storage/Shop/1/Products/45","Name":"枕头","Price":25,"ProductId":45},{"ImagePath":"/Storage/Shop/1/Products/46","Name":"电饭煲","Price":40,"ProductId":46},{"ImagePath":"/Storage/Shop/1/Products/47","Name":"衣架","Price":2,"ProductId":47}],"Id":0,"Name":"首页tab"}]
         */

        private String DefaultTabName;
        private int Id;
        private int Index;
        private String Name;
        private int StyleLevel;
        private Object SubName;
        private List<?> Products;
        private List<TabsBean> Tabs;

        public String getDefaultTabName() {
            return DefaultTabName;
        }

        public void setDefaultTabName(String DefaultTabName) {
            this.DefaultTabName = DefaultTabName;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getIndex() {
            return Index;
        }

        public void setIndex(int Index) {
            this.Index = Index;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getStyleLevel() {
            return StyleLevel;
        }

        public void setStyleLevel(int StyleLevel) {
            this.StyleLevel = StyleLevel;
        }

        public Object getSubName() {
            return SubName;
        }

        public void setSubName(Object SubName) {
            this.SubName = SubName;
        }

        public List<?> getProducts() {
            return Products;
        }

        public void setProducts(List<?> Products) {
            this.Products = Products;
        }

        public List<TabsBean> getTabs() {
            return Tabs;
        }

        public void setTabs(List<TabsBean> Tabs) {
            this.Tabs = Tabs;
        }

        public static class TabsBean {
            /**
             * Detail : [{"ImagePath":"/Storage/Shop/1/Products/36","Name":"进口纯牛奶","Price":200,"ProductId":36},{"ImagePath":"/Storage/Shop/1/Products/37","Name":"进口坚果","Price":5,"ProductId":37},{"ImagePath":"/Storage/Shop/1/Products/38","Name":"进口曲奇","Price":15,"ProductId":38},{"ImagePath":"/Storage/Shop/1/Products/39","Name":"进口橄榄油","Price":10,"ProductId":39},{"ImagePath":"/Storage/Shop/1/Products/40","Name":"洗面奶","Price":50,"ProductId":40},{"ImagePath":"/Storage/Shop/1/Products/41","Name":"漱口水","Price":50,"ProductId":41},{"ImagePath":"/Storage/Shop/1/Products/42","Name":"护垫","Price":20,"ProductId":42},{"ImagePath":"/Storage/Shop/1/Products/43","Name":"男士洗面奶","Price":50,"ProductId":43},{"ImagePath":"/Storage/Shop/1/Products/44","Name":"剪刀","Price":5,"ProductId":44},{"ImagePath":"/Storage/Shop/1/Products/45","Name":"枕头","Price":25,"ProductId":45},{"ImagePath":"/Storage/Shop/1/Products/46","Name":"电饭煲","Price":40,"ProductId":46},{"ImagePath":"/Storage/Shop/1/Products/47","Name":"衣架","Price":2,"ProductId":47}]
             * Id : 0
             * Name : 首页tab
             */

            private int Id;
            private String Name;
            private List<DetailBean> Detail;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public List<DetailBean> getDetail() {
                return Detail;
            }

            public void setDetail(List<DetailBean> Detail) {
                this.Detail = Detail;
            }

            public static class DetailBean {
                /**
                 * ImagePath : /Storage/Shop/1/Products/36
                 * Name : 进口纯牛奶
                 * Price : 200
                 * ProductId : 36
                 */

                private String ImagePath;
                private String Name;
                private int Price;
                private int ProductId;

                public String getImagePath() {
                    return ImagePath;
                }

                public void setImagePath(String ImagePath) {
                    this.ImagePath = ImagePath;
                }

                public String getName() {
                    return Name;
                }

                public void setName(String Name) {
                    this.Name = Name;
                }

                public int getPrice() {
                    return Price;
                }

                public void setPrice(int Price) {
                    this.Price = Price;
                }

                public int getProductId() {
                    return ProductId;
                }

                public void setProductId(int ProductId) {
                    this.ProductId = ProductId;
                }
            }
        }
    }

    public static class SlideImageBean {
        /**
         * Description : null
         * DisplaySequence : 7
         * Id : 36
         * ImageUrl : /Storage/Plat/ImageAd/201705171625544679420.jpg
         * ShopId : 0
         * TypeId : 1
         * Url : /
         */

        private Object Description;
        private int DisplaySequence;
        private int Id;
        private String ImageUrl;
        private int ShopId;
        private int TypeId;
        private String Url;

        public Object getDescription() {
            return Description;
        }

        public void setDescription(Object Description) {
            this.Description = Description;
        }

        public int getDisplaySequence() {
            return DisplaySequence;
        }

        public void setDisplaySequence(int DisplaySequence) {
            this.DisplaySequence = DisplaySequence;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(String ImageUrl) {
            this.ImageUrl = ImageUrl;
        }

        public int getShopId() {
            return ShopId;
        }

        public void setShopId(int ShopId) {
            this.ShopId = ShopId;
        }

        public int getTypeId() {
            return TypeId;
        }

        public void setTypeId(int TypeId) {
            this.TypeId = TypeId;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }
    }
}
