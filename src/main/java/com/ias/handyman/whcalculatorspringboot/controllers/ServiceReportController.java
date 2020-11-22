package com.ias.handyman.whcalculatorspringboot.controllers;



import com.ias.handyman.whcalculatorspringboot.model.ServiceReport;
import com.ias.handyman.whcalculatorspringboot.services.IServiceReportService;
import com.ias.handyman.whcalculatorspringboot.utils.IFormatErrorMessages;
import com.ias.handyman.whcalculatorspringboot.validator.IServiceReportValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;


@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping(value = "/api/services")
public class ServiceReportController {

    @Autowired
    private IServiceReportService iServiceReportService;

    @Autowired
    private IServiceReportValidator iServiceReportValidator;

    @Autowired
    private IFormatErrorMessages iFormatErrorMessages;

    @PostMapping(value = "/report")
    public ResponseEntity<?> createReport(@Valid @RequestBody ServiceReport serviceReport, BindingResult result){
        if(result.hasErrors()){// Validaciones del modelo
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,iFormatErrorMessages.formatMessage(result));
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
            boolean flag = listServicesTech.stream().anyMatch(service -> ((serviceReport.getStartService().isAfter(service.getStartService()) && serviceReport.getStartService().isBefore(service.getEndService()))
                    || (serviceReport.getEndService().isAfter(service.getStartService()) && serviceReport.getEndService().isBefore(service.getEndService()))));
            System.out.println(flag);
            res = iServiceReportValidator.hoursAvailableValidator(flag);
            if (res!=null) throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,res);
        }

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



}
