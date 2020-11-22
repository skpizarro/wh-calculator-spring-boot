package com.ias.handyman.whcalculatorspringboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}
