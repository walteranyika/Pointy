package com.walter.pointend;

/**
 * Created by walter on 5/5/17.
 */

public class Item {
    String names, address, longitude, latitude, time,date, code, bizName,bizTelPhone,physicalLocation,region, town, landmark;

    public Item(String address, String longitude, String latitude, String time, String date, String code, String bizName, String bizTelPhone, String physicalLocation, String region, String town, String landmark) {
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.time = time;
        this.date = date;
        this.code = code;
        this.bizName = bizName;
        this.bizTelPhone = bizTelPhone;
        this.physicalLocation = physicalLocation;
        this.region = region;
        this.town = town;
        this.landmark = landmark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getBizTelPhone() {
        return bizTelPhone;
    }

    public void setBizTelPhone(String bizTelPhone) {
        this.bizTelPhone = bizTelPhone;
    }

    public String getPhysicalLocation() {
        return physicalLocation;
    }

    public void setPhysicalLocation(String physicalLocation) {
        this.physicalLocation = physicalLocation;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }
}
