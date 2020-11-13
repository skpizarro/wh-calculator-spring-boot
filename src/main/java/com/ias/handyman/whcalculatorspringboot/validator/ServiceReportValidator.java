package com.ias.handyman.whcalculatorspringboot.validator;

import com.ias.handyman.whcalculatorspringboot.model.ServiceReport;
import org.springframework.stereotype.Service;

@Service
public class ServiceReportValidator implements IServiceReportValidator {
    @Override
    public String dateValidator(ServiceReport serviceReport) {

        if(serviceReport.getStartService().after(serviceReport.getEndService()) || serviceReport.getStartService().equals(serviceReport.getEndService())){
            return("La fecha de inicio del servicio no puede ser mayor o igual que la fecha de fin del servicio");
        }

        return null;

    }

    @Override
    public String serviceReportValidator(ServiceReport serviceReport) {
        if(serviceReport != null){
            return("El servicio ya se habia reportado anteriormente");
        }
        return null;
    }

    @Override
    public String serviceReportValidatorHours(ServiceReport serviceReport) {
        if(serviceReport != null){
            return("Ya hay un servicio registrado en éstas horas de trabajo");
        }
        return null;
    }

    @Override
    public String hoursAvailableValidator(Boolean flag) {

        if(flag==true){
            return("No es posible reportar un servicio en un mismo rango de tiempo en el que ya ha trabajado");
        }

        return null;
    }


}
