package com.ias.handyman.whcalculatorspringboot.model;




public class WorkHours {

    private float weekH;
    private float normalH;
    private float nightH;
    private float sundayH;
    private float normalExtraH;
    private float nightExtraH;
    private float sundayExtraH;

    public WorkHours() {
    }

    public WorkHours(float weekH, float normalH, float nightH, float sundayH, float normalExtraH, float nightExtraH, float sundayExtraH) {
        this.weekH = weekH;
        this.normalH = normalH;
        this.nightH = nightH;
        this.sundayH = sundayH;
        this.normalExtraH = normalExtraH;
        this.nightExtraH = nightExtraH;
        this.sundayExtraH = sundayExtraH;
    }

    public float getWeekH() {
        return weekH;
    }

    public void setWeekH(float weekH) {
        this.weekH = weekH;
    }

    public float getNormalH() {
        return normalH;
    }

    public void setNormalH(float normalH) {
        this.normalH = normalH;
    }

    public float getNightH() {
        return nightH;
    }

    public void setNightH(float nightH) {
        this.nightH = nightH;
    }

    public float getSundayH() {
        return sundayH;
    }

    public void setSundayH(float sundayH) {
        this.sundayH = sundayH;
    }

    public float getNormalExtraH() {
        return normalExtraH;
    }

    public void setNormalExtraH(float normalExtraH) {
        this.normalExtraH = normalExtraH;
    }

    public float getNightExtraH() {
        return nightExtraH;
    }

    public void setNightExtraH(float nightExtraH) {
        this.nightExtraH = nightExtraH;
    }

    public float getSundayExtraH() {
        return sundayExtraH;
    }

    public void setSundayExtraH(float sundayExtraH) {
        this.sundayExtraH = sundayExtraH;
    }
}
