package com.example.bbacr.ddw.eventbus;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class EventLocation {
    String location;
    String city;
    String detail;

    public EventLocation(String location, String city, String detail) {
        this.location = location;
        this.city = city;
        this.detail = detail;
    }

    public String getLocation() {

        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
