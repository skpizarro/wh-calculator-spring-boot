package com.ias.handyman.whcalculatorspringboot.model;

import lombok.Getter;
import lombok.Setter;


public class Extra {

    private float h;
    private float aux;

    public Extra() {
    }

    public Extra(float h, float aux) {
        this.h = h;
        this.aux = aux;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public float getAux() {
        return aux;
    }

    public void setAux(float aux) {
        this.aux = aux;
    }
}
