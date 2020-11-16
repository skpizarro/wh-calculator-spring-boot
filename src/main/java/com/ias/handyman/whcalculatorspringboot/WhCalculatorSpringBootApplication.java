package com.ias.handyman.whcalculatorspringboot;

import com.ias.handyman.whcalculatorspringboot.model.Calculate;
import org.apache.tomcat.jni.Local;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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

        /**
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date fechaHoy = new Date();
        System.out.println(fechaHoy);
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaHoy);
        System.out.println(cal.getTime());
        */

        /**
        LocalDateTime lt = LocalDateTime.now();
        System.out.println("Numero de la semana del año forma 1: "+ lt.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
        */


        //System.out.println(ZonedDateTime.now());


    }



}
