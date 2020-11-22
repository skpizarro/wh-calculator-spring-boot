package com.ias.handyman.whcalculatorspringboot.services;

import com.ias.handyman.whcalculatorspringboot.model.Calculate;
import com.ias.handyman.whcalculatorspringboot.model.Extra;
import com.ias.handyman.whcalculatorspringboot.model.ServiceReport;
import com.ias.handyman.whcalculatorspringboot.model.WorkHours;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ICalWorkHours {

    // metodo principal para el calculo
    WorkHours calculate(List<ServiceReport> services, int weekNumber);

    // logica para el calculo de horas trabajadas
    Calculate calculateWorkH(LocalDateTime startS, LocalDateTime endS, float normalH, float nightH, float sundayH, float auxNormal, float auxNight, float auxSun, float weekH, boolean flag, float maxH);

    // para las horas extra en un solo turno
    Extra extraType1 (float weekH, float aux, float maxH, float h);

    // para las horas extra con cambio de turno
    Extra extraType2 (float weekH, float aux, float aux2,float maxH,float h);

    Float formatToH(LocalDateTime dateTime, LocalTime time);

}
