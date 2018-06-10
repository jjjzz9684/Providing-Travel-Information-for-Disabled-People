package com.hnsle.webaaa.model;

public class DetailedModel {
    private String publictransport; //접근로(경사로?)
    private String restroom; //장애인 화장실
    private String wheelchair;  //휠체어
    private String exit;    //출입통로
    private String elevator;    //엘리베이터
    private String brailblock;  //점자블록
    private String helpdog; //안내견동반
    private String parking; //주차장

    public String getPublictransport() {
        return publictransport;
    }

    public void setPublictransport(String publictransport) {
        this.publictransport = publictransport;
    }

    public DetailedModel() {
    }

    public String getWheelchair() {
        return wheelchair;
    }

    public void setWheelchair(String wheelchair) {
        this.wheelchair = wheelchair;
    }

    public String getExit() {
        return exit;
    }

    public void setExit(String exit) {
        this.exit = exit;
    }

    public String getElevator() {
        return elevator;
    }

    public void setElevator(String elevator) {
        this.elevator = elevator;
    }

    public String getBrailblock() {
        return brailblock;
    }

    public void setBrailblock(String brailblock) {
        this.brailblock = brailblock;
    }

    public String getHelpdog() {
        return helpdog;
    }

    public void setHelpdog(String helpdog) {
        this.helpdog = helpdog;
    }

    public String getRestroom() {
        return restroom;
    }

    public void setRestroom(String restroom) {
        this.restroom = restroom;
    }

    public String getParking() {
        return parking;

    }

    public void setParking(String parking) {
        this.parking = parking;
    }
}
