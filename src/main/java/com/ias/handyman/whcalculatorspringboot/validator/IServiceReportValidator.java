
package com.ias.handyman.whcalculatorspringboot.validator;

import com.ias.handyman.whcalculatorspringboot.model.ServiceReport;
import org.springframework.stereotype.Service;

//Interface para la validacion de los datos recibidos para la generar un reporte de servicio

public interface IServiceReportValidator {

    String dateValidator(ServiceReport serviceReport);

    String serviceReportValidator(ServiceReport serviceReport);

    String serviceReportValidatorHours(ServiceReport serviceReport);


    String hoursAvailableValidator(Boolean flag);



}

/**
 import com.ias.handyman.whcalculatorspringboot.model.ServiceReport;

 import javax.validation.ConstraintValidator;
 import javax.validation.ConstraintValidatorContext;

 public class ServiceReportValidator implements ConstraintValidator<ServiceReportDateTime, ServiceReport> {

@Override
public boolean isValid(ServiceReport serviceReport, ConstraintValidatorContext constraintValidatorContext) {

if(serviceReport.getStartService().before(serviceReport.getEndService()) || serviceReport.getStartService().equals(serviceReport.getEndService())){ // si la fecha de inicio es mayor que la fecha fin
return false;
}
return true;

}
}
 */