package com.ias.handyman.whcalculatorspringboot.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ias.handyman.whcalculatorspringboot.model.ErrorMessage;
import com.ias.handyman.whcalculatorspringboot.model.ServiceReport;
import com.ias.handyman.whcalculatorspringboot.services.IServiceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/services")
public class ServiceReportController {

    @Autowired
    private IServiceReportService iServiceReportService;

    @PostMapping(value = "/report")
    public ResponseEntity<ServiceReport> createReport(@Valid @RequestBody ServiceReport serviceReport, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
        }
        ServiceReport serviceReportDB = iServiceReportService.createServiceReport(serviceReport);
        return ResponseEntity.ok(serviceReportDB);
    }


    //public ResponseEntity<List<ServiceReport>> calculateW(){}


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
