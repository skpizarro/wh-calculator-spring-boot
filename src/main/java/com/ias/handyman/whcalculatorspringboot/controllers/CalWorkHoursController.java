package com.ias.handyman.whcalculatorspringboot.controllers;


import com.ias.handyman.whcalculatorspringboot.model.ServiceReport;
import com.ias.handyman.whcalculatorspringboot.model.WorkHours;
import com.ias.handyman.whcalculatorspringboot.services.ICalWorkHours;
import com.ias.handyman.whcalculatorspringboot.services.IServiceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




import javax.validation.constraints.NotNull;

import java.util.List;


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
}
