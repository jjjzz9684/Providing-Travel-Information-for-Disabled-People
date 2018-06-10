package com.hnsle.webaaa.model;

public class TourModel {
    private String addr1;
    private String addr2;
    private long contentId;
    private int contentTypeId;
    private String img1;
    private String img2;
    private double mapX;
    private double mapY;
    private String tel;
    private String title;

    public TourModel(){};

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public void setContentTypeId(int contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public void setMapX(double mapX) {
        this.mapX = mapX;
    }

    public void setMapY(double mapY) {
        this.mapY = mapY;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddr1() {
        return addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public long getContentId() {
        return contentId;
    }

    public int getContentTypeId() {
        return contentTypeId;
    }

    public String getImg1() {
        return img1;
    }

    public String getImg2() {
        return img2;
    }

    public double getMapX() {
        return mapX;
    }

    public double getMapY() {
        return mapY;
    }

    public String getTel() {
        return tel;
    }

    public String getTitle() {
        return title;
    }

}


