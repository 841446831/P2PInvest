package com.chinosoft.p2pinvest.bean;

import java.util.List;

/**
 * Created by cai on 2016/7/27.
 */
public class Home {

    private Product product;

    private List<Notice> notices;

    public Product getProduct() {

        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Notice> getNotices() {
        return notices;
    }

    public void setNotices(List<Notice> notices) {
        this.notices = notices;
    }
}
