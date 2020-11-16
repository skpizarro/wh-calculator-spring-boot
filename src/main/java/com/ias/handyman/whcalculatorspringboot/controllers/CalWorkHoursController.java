package com.ias.handyman.whcalculatorspringboot.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ias.handyman.whcalculatorspringboot.model.ErrorMessage;
import com.ias.handyman.whcalculatorspringboot.model.ServiceReport;
import com.ias.handyman.whcalculatorspringboot.model.WorkHours;
import com.ias.handyman.whcalculatorspringboot.services.ICalWorkHours;
import com.ias.handyman.whcalculatorspringboot.services.IServiceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping(value = "/api/calculate")
public class CalWorkHoursController {

    @Autowired
    private IServiceReportService iServiceReportService;
    @Autowired
    private ICalWorkHours iCalWorkHours;

    @GetMapping(value = "/")
    public ResponseEntity<WorkHours> getWorkHours(@RequestParam("idTechnician")
                                                  @NotNull(message = "La identificacion del técnico es requerida") Long idTechnician,
                                                  @RequestParam("weekNumber")
                                                  @NotNull(message = "El número de la semana del año es requerido") int weekNumber) {

        // Obtenemos los servicios que ha repotado el técnico especificado
        List<ServiceReport> services = iServiceReportService.getServicesByTechnician(idTechnician);

        WorkHours resp = iCalWorkHours.calculate(services,weekNumber);


        return ResponseEntity.ok(resp);

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
