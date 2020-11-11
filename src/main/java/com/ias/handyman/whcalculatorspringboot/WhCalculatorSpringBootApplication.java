package com.ias.handyman.whcalculatorspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class WhCalculatorSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhCalculatorSpringBootApplication.class, args);

        /**
        // Ensayando con Date
        Date fechaHoy = new Date();
        System.out.println(fechaHoy);
        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
        System.out.println("Fecha Formateada: "+ df.format(fechaHoy));

        //CalendaraDate
        Calendar calendar = Calendar.getInstance(); // Calendar tiene su propia forma de crear objetos (fabrica)
        Date fecha = calendar.getTime(); // lo mismo que el Date,Calendar envuelve a Date
        System.out.println("Calendar : "+ calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH)+1);
        System.out.println(calendar.get(Calendar.DATE));
        */

    }



}
