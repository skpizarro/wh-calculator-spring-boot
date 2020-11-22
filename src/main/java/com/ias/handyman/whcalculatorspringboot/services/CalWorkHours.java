package com.ias.handyman.whcalculatorspringboot.services;

import com.ias.handyman.whcalculatorspringboot.model.Calculate;
import com.ias.handyman.whcalculatorspringboot.model.Extra;
import com.ias.handyman.whcalculatorspringboot.model.ServiceReport;
import com.ias.handyman.whcalculatorspringboot.model.WorkHours;
import com.ias.handyman.whcalculatorspringboot.utils.ICalculateMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class CalWorkHours implements ICalWorkHours{

    @Autowired
    private ICalculateMethods iCalculateMethods;

    @Override
    public WorkHours calculate(List<ServiceReport> services, int weekNumber) {
        return iCalculateMethods.calculate(services,weekNumber);
    }

    @Override
    public Calculate calculateWorkH(LocalDateTime startS, LocalDateTime endS, float normalH, float nightH, float sundayH,
                                    float auxNormal, float auxNight, float auxSun, float weekH, boolean flag,
                                    float maxH) {
        return iCalculateMethods.calculateWorkH(startS,endS,normalH,nightH,sundayH,auxNormal,auxNight,auxSun,
                                                weekH,flag,maxH);
    }

    @Override
    public Extra extraType1(float weekH, float aux, float maxH, float h) {
        return iCalculateMethods.extraType1(weekH,aux,maxH,h);
    }

    @Override
    public Extra extraType2(float weekH, float aux, float aux2, float maxH, float h) {
        return iCalculateMethods.extraType2(weekH,aux,aux2,maxH,h);
    }

    @Override
    public Float formatToH(LocalDateTime dateTime, LocalTime time) {
        return iCalculateMethods.formatToH(dateTime,time);
    }
}
