package wangbo.bawei.com.wangbo20171113.entity;

/**
 * author:Created by Wangbo on 2017/11/15.
 */

public class NewsotherBean {
    private String img;
    private String title;
    private String price;

    public NewsotherBean(String img, String title, String price) {
        this.img = img;
        this.title = title;
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
