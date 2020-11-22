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
    }
}
