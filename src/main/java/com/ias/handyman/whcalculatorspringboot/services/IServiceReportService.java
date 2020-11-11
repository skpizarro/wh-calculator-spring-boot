package com.ias.handyman.whcalculatorspringboot.services;

import com.ias.handyman.whcalculatorspringboot.model.ServiceReport;

import java.util.Date;
import java.util.List;

public interface IServiceReportService {
    ServiceReport createServiceReport(ServiceReport serviceReport);
    List<ServiceReport> getServicesByTechnician(Long idTechnician);
    List<ServiceReport> getServiceReport(Long idService, Long idTechnician, Date startService, Date endService);
}
