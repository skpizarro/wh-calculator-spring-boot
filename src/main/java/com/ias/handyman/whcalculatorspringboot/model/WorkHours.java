package com.ias.handyman.whcalculatorspringboot.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkHours {

    private float weekH;
    private float normalH;
    private float nightH;
    private float sundayH;
    private float normalExtraH;
    private float nightExtraH;
    private float sundayExtraH;
}
