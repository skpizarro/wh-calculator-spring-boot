package com.ias.handyman.whcalculatorspringboot.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class ReqCalc {

    @NotNull(message = "El id del técnico es requerido")
    private Byte id;


    @NotNull(message = "El numero de la semana del año es requerido")
    private Byte weekNumber;

}
