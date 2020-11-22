
package com.ias.handyman.whcalculatorspringboot.validator;

import com.ias.handyman.whcalculatorspringboot.model.ServiceReport;


//Interface para la validación de los datos recibidos para la generar un reporte de servicio

public interface IServiceReportValidator {

    String dateValidator(ServiceReport serviceReport);
    String serviceReportValidator(ServiceReport serviceReport);
    String serviceReportValidatorHours(ServiceReport serviceReport);
    String hoursAvailableValidator(Boolean flag);
}
