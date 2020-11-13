package com.ias.handyman.whcalculatorspringboot.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ias.handyman.whcalculatorspringboot.model.ErrorMessage;
import com.ias.handyman.whcalculatorspringboot.model.ServiceReport;
import com.ias.handyman.whcalculatorspringboot.services.IServiceReportService;
import com.ias.handyman.whcalculatorspringboot.validator.IServiceReportValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/services")
public class ServiceReportController {

    @Autowired
    private IServiceReportService iServiceReportService;

    @Autowired
    private IServiceReportValidator iServiceReportValidator;

    @PostMapping(value = "/report")
    public ResponseEntity<?> createReport(@Valid @RequestBody ServiceReport serviceReport, BindingResult result){
        if(result.hasErrors()){// Validaciones del modelo
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST,result.getFieldErrors().toString());
        }else{//Ahora las personalizadas

            //Fecha de inicio >= que la fecha fin
            String res = iServiceReportValidator.dateValidator(serviceReport);
            if(res!=null) throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,res);

            // validación servicio ya reportado
            ServiceReport s = iServiceReportService.getServiceReport(serviceReport);
            res = iServiceReportValidator.serviceReportValidator(s);
            if(res!=null) throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,res);

            // un servicio diferente con la misma hora reportada
            s = iServiceReportService.getServiceReportByTechAndDate (serviceReport);
            res = iServiceReportValidator.serviceReportValidatorHours (s);
            if(res!=null) throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,res);


            // Validación horas disponibles para reportar servicio
            List<ServiceReport> listServicesTech = iServiceReportService.getServicesByTechnician(serviceReport.getIdTechnician()); // Obtenemos todos los servicios que ha realizado el técnico

            // si la bandera devuelve true, entonces la fecha ya ha sido registrada en algún servicio y se lanza la excepción
            boolean flag = listServicesTech.stream().anyMatch(service -> ((serviceReport.getStartService().after(service.getStartService()) && serviceReport.getStartService().before(service.getEndService()))
                    || (serviceReport.getEndService().after(service.getStartService()) && serviceReport.getEndService().before(service.getEndService()))));
            System.out.println(flag);
            res = iServiceReportValidator.hoursAvailableValidator(flag);
            if (res!=null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, res);

            /**
            for (ServiceReport service:listServicesTech) {
                if(
                        ((serviceReport.getStartService().after(service.getStartService()) && serviceReport.getStartService().before(service.getEndService()))
                                || (serviceReport.getEndService().after(service.getStartService()) && serviceReport.getEndService().before(service.getEndService())))){
                    System.out.println("Misma hora");
                }
                System.out.println("Holaaa");

            }
             */

            /**
            if(!listServicesTech.isEmpty()) {
                res = iServiceReportValidator.hoursAvailableValidator(s);
                if (flag==true) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, res);
            }
            */

            /**
            if(!listServicesTech.isEmpty()){
                listServicesTech.forEach(service ->{
                    if((serviceReport.getStartService().after(service.getStartService()) && serviceReport.getStartService().before(service.getEndService()))
                            || (serviceReport.getEndService().after(service.getStartService()) && serviceReport.getEndService().before(service.getEndService()))){
                        //entonces la fecha ya ha sido registrada en algun servicio
                    }
                });
            }
             */


        }

        /**
        SimpleDateFormat df = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss"); // Formato en que guardaremos la fecha y la hora en formato 24
        df.format(serviceReport.getStartService());// Formateamos la fecha de inicio del servicio;
        df.format(serviceReport.getEndService());// Formateamos la fecha de fin del servicio;
         */

        ServiceReport serviceReportDB = iServiceReportService.createServiceReport(serviceReport);
        return ResponseEntity.ok(serviceReportDB);
    }


    //public ResponseEntity<List<ServiceReport>> calculateW(){}
    @GetMapping(value = "/{id}")
    public ResponseEntity<List<ServiceReport>> getServicesByTechnician(@PathVariable("id") Long id){
        if(id !=null){
            List<ServiceReport> technicianServices =iServiceReportService.getServicesByTechnician(id);
            return ResponseEntity.ok(technicianServices);
        }
        return ResponseEntity.badRequest().build();
    }


    private String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> { // mapeamos la lista de errores
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage()); // obtenemos el tipo de error y el mensaje
                    return error;
                }).collect(Collectors.toList()); // como retorna un string toca convertirlo a una lista

        ErrorMessage errorMessage = new ErrorMessage("01",errors);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }


}
