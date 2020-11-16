package com.ias.handyman.whcalculatorspringboot.model;


public class Calculate {

    float weekH=0,
            normalH=0,
            nightH=0,
            sundayH=0,
            normalExtraH= 0,
            nightExtraH=0,
            sundayExtraH=0,
            auxNormal=0,
            auxNight=0,
            auxSun=0,
            maxH = 48;
    Boolean flag = true;


    public Calculate() {

    }

    public Calculate(float weekH, float normalH, float nightH, float sundayH, float normalExtraH, float nightExtraH, float sundayExtraH, float auxNormal, float auxNight, float auxSun, float maxH, Boolean flag) {
        this.weekH = weekH;
        this.normalH = normalH;
        this.nightH = nightH;
        this.sundayH = sundayH;
        this.normalExtraH = normalExtraH;
        this.nightExtraH = nightExtraH;
        this.sundayExtraH = sundayExtraH;
        this.auxNormal = auxNormal;
        this.auxNight = auxNight;
        this.auxSun = auxSun;
        this.maxH = maxH;
        this.flag = flag;
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

    public float getAuxNormal() {
        return auxNormal;
    }

    public void setAuxNormal(float auxNormal) {
        this.auxNormal = auxNormal;
    }

    public float getAuxNight() {
        return auxNight;
    }

    public void setAuxNight(float auxNight) {
        this.auxNight = auxNight;
    }

    public float getAuxSun() {
        return auxSun;
    }

    public void setAuxSun(float auxSun) {
        this.auxSun = auxSun;
    }

    public float getMaxH() {
        return maxH;
    }

    public void setMaxH(float maxH) {
        this.maxH = maxH;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
