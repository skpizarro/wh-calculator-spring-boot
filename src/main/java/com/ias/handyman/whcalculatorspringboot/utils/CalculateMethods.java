package com.ias.handyman.whcalculatorspringboot.utils;

import com.ias.handyman.whcalculatorspringboot.model.Calculate;
import com.ias.handyman.whcalculatorspringboot.model.Extra;
import com.ias.handyman.whcalculatorspringboot.model.ServiceReport;
import com.ias.handyman.whcalculatorspringboot.model.WorkHours;
import com.ias.handyman.whcalculatorspringboot.services.CalWorkHours;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.IsoFields;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CalculateMethods implements ICalculateMethods{

    @Override
    public WorkHours calculate(List<ServiceReport> services, int weekNumber) {

        // Instanciamos un objeto Calculate para almacenar los calculos de las horas
        Calculate respCalculate = new Calculate();

        // Para cada servicio vamos a calcular las horas trabajadas
        services.stream().forEach(s->{
            var startS = s.getStartService();
            var endS = s.getEndService();

            System.out.println("Obtenemos fehca inicio DB: "+startS);

            System.out.println("Obtenemos fecha fin DB: "+endS);


            // obtenemos la semana del año para cada servicio   /// ISO 8601 Forma vieja y menos adecuada
            //Calendar calStart = Calendar.getInstance();
            //calStart.setTime(startS);
            //int weekOfYearS = calStart.get(Calendar.WEEK_OF_YEAR);

            //Calendar calEnd = Calendar.getInstance();
            //calEnd.setTime(endS);

            //int weekOfYearE = calEnd.get(Calendar.WEEK_OF_YEAR);

            // numero de la semana del año usando java.time
            int weekOfYearS = startS.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
            int weekOfYearE = endS.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);


            // si la hora de inicio y hora fin están en la misma semana
            if(weekOfYearS == weekNumber && weekOfYearE == weekNumber){

                // Instanciamos un objeto para la respusta de cada servicio
                Calculate resp ;

                // Si las horas semanales son menores al maximo de horas semanales definidas
                if(respCalculate.getWeekH() < respCalculate.getMaxH()){//Calculamos horas normales

                    resp = calculateWorkH(startS,endS,respCalculate.getNormalH(),respCalculate.getNightH(),
                            respCalculate.getSundayH(),respCalculate.getAuxNormal(),respCalculate.getAuxNight(),
                            respCalculate.getAuxSun(),respCalculate.getWeekH(),respCalculate.getFlag(),
                            respCalculate.getMaxH());

                    respCalculate.setWeekH(resp.getWeekH());
                    respCalculate.setNormalH(resp.getNormalH());
                    respCalculate.setNightH(resp.getNightH());
                    respCalculate.setSundayH(resp.getSundayH());
                    respCalculate.setNormalExtraH(resp.getAuxNormal());
                    respCalculate.setNightExtraH(resp.getAuxNight());
                    respCalculate.setSundayExtraH(resp.getAuxSun());

                }else{
                    // Se calculan solo las horas extras
                    respCalculate.setFlag(false);
                    System.out.println("SOLO HORAS EXTRAS");
                    resp = calculateWorkH(
                            startS,endS,respCalculate.getNormalExtraH(),respCalculate.getNightExtraH(),
                            respCalculate.getSundayExtraH(),respCalculate.getAuxNormal(),respCalculate.getAuxNight(),
                            respCalculate.getAuxSun(),respCalculate.getWeekH(),respCalculate.getFlag(),
                            respCalculate.getMaxH());

                    respCalculate.setWeekH(resp.getWeekH());
                    respCalculate.setNormalExtraH(resp.getAuxNormal());
                    respCalculate.setNightExtraH(resp.getAuxNight());
                    respCalculate.setSundayExtraH(resp.getAuxSun());
                }



            }else if(weekOfYearS == weekNumber && weekOfYearE > weekNumber){ // si empieza el servicio Domingo y termina un Lunes de la otra semana
                System.out.println("Inicia Domingo y finaliza Lunes de la siguiente semana");

                // obtenemos las horas del domingo
                float onlySunH = 24 - formatToH(endS,null); // convertimos todo a horas para operar

                if(respCalculate.getWeekH() < respCalculate.getMaxH()){// Si las horas semanales son menores al maximo de horas semanales definidas

                    respCalculate.setSundayH(respCalculate.getSundayH() + onlySunH); // acumulamos las horas del domingo
                    respCalculate.setWeekH(respCalculate.getWeekH() + onlySunH ); // acumulamos el total de horas en la semana

                }else{
                    //Horas extras dominicales
                    respCalculate.setSundayExtraH(respCalculate.getSundayExtraH() + onlySunH); // acumulamos las horas extras dominicales
                    respCalculate.setWeekH(respCalculate.getWeekH() + onlySunH ); // acumulamos el total de horas en la semana

                }

            }


        });

        //RESPUESTA FINAL DEL TOTAL DE CALCULOS
        WorkHours totalWH = new WorkHours(
                respCalculate.getWeekH(), respCalculate.getNormalH(),
                respCalculate.getNightH(),respCalculate.getSundayH(), respCalculate.getNormalExtraH(),
                respCalculate.getNightExtraH(),respCalculate.getSundayExtraH());

        return totalWH;
    }


    @Override
    public Calculate calculateWorkH(LocalDateTime startS, LocalDateTime endS, float normalH, float nightH, float sundayH, float auxNormal, float auxNight, float auxSun, float weekH, boolean flag, float maxH) {

        // Definimos horarios, solo me importa la hora

        //Forma nueva

        LocalTime noI = LocalTime.of(7,0,0),
                  noF = LocalTime.of(20,0,0),
                  niF = LocalTime.from(noI),
                  niI = LocalTime.from(noF);


        //Forma vieja
        /**
        Calendar noI = Calendar.getInstance(),
                 noF= Calendar.getInstance(),
                 niI = Calendar.getInstance(),
                 niF= Calendar.getInstance();

        noI.set(Calendar.HOUR_OF_DAY,7);
        noI.set(Calendar.MINUTE,0);
        noI.set(Calendar.SECOND,0);

        noF.set(Calendar.HOUR_OF_DAY,20);
        noF.set(Calendar.MINUTE,0);
        noF.set(Calendar.SECOND,0);

        niF = noI;
        niI = noF;
        */


        //Formateamos todo a horas
        float normI = formatToH(null,noI),
        normF = formatToH(null,noF),
        nightI = formatToH(null,niI),
        nightF = formatToH(null,niF);

        ////////////////////////////////
        // Transformamos la fecha de inicio y fin en horas

        float startH = formatToH(startS,null),
                endH = formatToH(endS,null);

        /////////////////////////////////////////////////////

        var hours = endH - startH; // Horas de trabajo en cada servicio

        /** Por si ingresa varios dias seguidos (NO SE IMPLEMENTÓ)
        var days=(endS-startS)/(1000* 60* 60* 24); // dias que trabajó
        */

        if(startS.getDayOfWeek().getValue() > 0 && endS.getDayOfWeek().getValue() > 0){ // Lunes a Sabado

            //Horario normal
            if((startH >= normI && startH < normF) && (endH > normI && endH <= normF)){ /// 7AM a 8 PM
                System.out.println("Horario normal  7AM a 8 PM");
                normalH=normalH + hours;
                weekH = weekH + hours; // aumentamos las horas semanales

                if(flag){// significa que las semanas no habian llegado a 48 pero pudieron llegarlo en esta iteracion, entoces se confirma y si es positivo se efectua la operación de horas extras
                    var resp = extraType1(weekH,auxNormal,maxH,normalH);
                    auxNormal = resp.getAux();
                    normalH = resp.getH();
                }


            }else if((startH >= normI && startH < normF) && endH >= nightI){ // // empezó hora normal y termino en nocturna del mismo dia (como atienden emergencias, se puede dar el caso)

                // 7AM a 8PM + 8pm a 11pm
                System.out.println("Horario 7AM a 8PM + 8pm a 11pm");

                // separamos las horas normales de las nocturnas
                normalH = normalH + (nightI - startH);
                weekH = weekH + (nightI - startH);

                if(flag){
                    var resp = extraType1(weekH,auxNormal,maxH,normalH);
                    auxNormal = resp.getAux();
                    normalH = resp.getH();
                }

                //Normal Nocturnas
                nightH= nightH + (endH - nightI);
                weekH = weekH + (endH - nightI);

                if(flag){
                    var resp = extraType2(weekH,auxNormal,auxNight,maxH,nightH);
                    auxNight = resp.getAux();
                    nightH = resp.getH();
                }

            }
            else if((startH >= normI && startH < normF) && endH <= nightF){ // empezó hora hormal y terminó hora nocturna del dia siguiente de la misma semana
                System.out.println("7AM a 8PM + 8pm a 12am + 12am a 7am");
                // 7AM a 8PM + 8pm a 12am + 12am a 7am

                // separamos las horas normales de las nocturnas
                normalH = normalH + (nightI - startH);// normales
                weekH = weekH + (nightI - startH);

                if(flag){
                    var resp = extraType1(weekH,auxNormal,maxH,normalH);
                    auxNormal = resp.getAux();
                    normalH = resp.getH();
                }

                //Normal Nocturnas
                nightH= nightH + (4 + endH); // se suman las primeras nocturnas (8pm hasta las 12pm) y de 12am hasta las 7am;
                weekH = weekH + (4 + endH);

                if(flag){
                    var resp = extraType2(weekH,auxNormal,auxNight,maxH,nightH);
                    auxNight = resp.getAux();
                    nightH = resp.getH();
                }
            }

            else if((startH >= nightI && endH > nightI) || (startH >= nightI && endH == 0)){ // Hora nocturna
                // de 8pm a 12am
                System.out.println("8pm a 12am");

                if(hours<0){
                    nightH = nightH + (24+hours);
                    weekH = weekH + (24+hours);

                    if(flag){
                        var resp = extraType1(weekH,auxNight,maxH,nightH);
                        auxNight = resp.getAux();
                        nightH = resp.getH();
                    }

                }else{
                    nightH = nightH + hours;
                    weekH = weekH + hours;
                    if(flag){
                        var resp = extraType1(weekH,auxNight,maxH,nightH);
                        auxNight = resp.getAux();
                        nightH = resp.getH();
                    }
                }

            }else if(startH >= nightI && endH <= nightF ){
                System.out.println("De 8pm a 7 am");
                // de 8pm a 7am
                nightH = nightH + (24 - startH) +(endH); // de 8pm a 12am + de 12am a 7am
                        weekH = weekH + (24 - startH) +(endH);
                if(flag){
                    var resp = extraType1(weekH,auxNight,maxH,nightH);
                    auxNight = resp.getAux();
                    nightH = resp.getH();
                }
            }

            else if(startH >= nightI && (endH >= normI && endH <= nightI )){
                // de 8pm a 8pm del dia sig
                System.out.println("De 8pm a 8pm del dia sig");
                nightH = nightH + (24 - startH) + 7; // horas nocturnas del dia anterior mas todas del siguiente dia
                weekH = weekH + (24 - startH) + 7;

                if(flag){
                    var resp = extraType1(weekH,auxNight,maxH,nightH);
                    auxNight = resp.getAux();
                    nightH = resp.getH();
                }

                normalH = normalH + (endH - 7); // las horas normales del dia siguiente
                weekH = weekH + (endH - 7);
                if(flag){
                    var resp = extraType2(weekH,auxNight,auxNormal,maxH,normalH);
                    auxNormal = resp.getAux();
                    normalH = resp.getH();
                }

            }
            else if((startH >= 0 && startH <= normI) && (endH > 0 && endH <= normI)){ // inició y termino de 12 am a 7 am del mismo dia
                //Nocturnas de 12am a 7am
                System.out.println("De 12am a 7am");
                nightH = nightH + hours;
                weekH = weekH + hours;
                if(flag){
                    var resp = extraType1(weekH,auxNight,maxH,nightH);
                    auxNight = resp.getAux();
                    nightH = resp.getH();
                }
            }
            else if((startH >= 0 && startH <= normI) && (endH > normI && endH <= normF)){ //inició de 12 am y terminó al dia siguiente en hora normal
                System.out.println("De 12 am a 8pm");
                // nocturnas
                nightH = nightH + (nightF - startH);
                weekH = weekH + (nightF - startH);

                if(flag){
                    var resp = extraType1(weekH,auxNight,maxH,nightH);
                    auxNight = resp.getAux();
                    nightH = resp.getH();
                }

                // normales
                normalH = normalH + (endH-normI);
                weekH = weekH + (endH-normI);
                if(flag){
                    var resp = extraType2(weekH,auxNight,auxNormal,maxH,normalH);
                    auxNormal = resp.getAux();
                    normalH = resp.getH();
                }

            }
            else if((startH >= 0 && startH <= normI) && (endH >=nightI || endH == 0 )){
                System.out.println("12am a 12am del sig dia");
                nightH = nightH + (nightF - startH);// nocturnas de 12am hasta 7am
                weekH = weekH + (nightF - startH);

                if(flag){
                    var resp = extraType1(weekH,auxNight,maxH,nightH);
                    auxNight = resp.getAux();
                    nightH = resp.getH();
                }

                normalH = normalH + (nightI - normI); // 7am a 8pm
                weekH = weekH + (nightI - normI);

                if(flag){
                    var resp = extraType2(weekH,auxNight,auxNormal,maxH,normalH);
                    auxNormal = resp.getAux();
                    normalH = resp.getH();
                }

                // las nocturnas de 8pm a 12am
                if(endH == 0){
                    nightH = nightH + 4;
                    weekH = weekH + 4;

                    if(flag){
                        var resp = extraType1(weekH,auxNight,maxH,nightH);
                        auxNight = resp.getAux();
                        nightH = resp.getH();
                    }

                }else{
                    nightH = nightH + (endH-nightI);
                    weekH = weekH + (endH-nightI);
                    if(flag){
                        var resp = extraType1(weekH,auxNight,maxH,nightH);
                        auxNight = resp.getAux();
                        nightH = resp.getH();
                    }
                }

            }

        }else{
            //Domingos cuando la hora fin finaliza ,(misma semana)
            sundayH= sundayH + hours;
            weekH = weekH + hours;

            if(flag){
                var resp = extraType1(weekH,auxSun,maxH,sundayH);
                auxSun = resp.getAux();
                sundayH = resp.getH();

            }
        }

        Calculate calcResp = new Calculate(
                weekH,
                normalH,
                nightH,
                sundayH,
                0,
                0,
                0,
                auxNormal,
                auxNight,
                auxSun,0,null);

        return calcResp;

    }

    @Override
    public Extra extraType1(float weekH, float aux, float maxH, float h) { // se asigna las horas extra si solo hay un turno (norma o nocturno)
        // si las horas semanales > 48 en este caso
        if(weekH > maxH){
            aux = weekH - maxH; // obtenemos las extras
            h = h - aux; // le resto las extras a las normales
            return new Extra(h,aux);
        }
        return new Extra(h,aux);
    }

    @Override
    public Extra extraType2(float weekH, float aux, float aux2, float maxH, float h) { // se usa cuando pasamos de normal a noctuno o al contrario. para asignar las horas extra donde correspondan
        if((weekH > maxH) && aux == 0){
            aux2 = weekH - maxH; // obtenemos las extras
            h = h - aux2; // le resto las extras a las normales

            return new Extra(h,aux2);
        }else if((weekH > maxH) && aux > 0){
            //solo se tienen en cuenta las extras
            aux2 = weekH - aux - maxH; // las semanas menos las extras anteriores menos el tope(48)
            return new Extra(h,aux2);
        }
        // los devuelve como estaban
        return new Extra(h,aux2);
    }


    @Override
    public Float formatToH(LocalDateTime dateTime,LocalTime time) {

        if(dateTime != null){
            return  ((dateTime.getHour()) + (float)(dateTime.getMinute()/60) + (float)(dateTime.getSecond()/3600));
        }

        return  ((time.getHour()) + (float)(time.getMinute()/60) + (float)(time.getSecond()/3600));
    }
}
