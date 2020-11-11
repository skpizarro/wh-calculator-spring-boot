package com.ias.handyman.whcalculatorspringboot.services;

import com.ias.handyman.whcalculatorspringboot.model.ServiceReport;
import com.ias.handyman.whcalculatorspringboot.repository.IServiceReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ServiceReportService implements IServiceReportService{

    @Autowired
    private IServiceReportRepository iServiceReportRepository;

    // Generamos el reporte del servicio
    @Override
    public ServiceReport createServiceReport(ServiceReport serviceReport) {
        System.out.println(serviceReport.getIdService());
        return iServiceReportRepository.save(serviceReport);
    }

    // Obtenemos todos los servicios de un técnico
    @Override
    public List<ServiceReport> getServicesByTechnician(Long idTechnician) {
        return iServiceReportRepository.findByIdTechnician(idTechnician);
    }

    // Obtenemos un servicio específico
    @Override
    public List<ServiceReport> getServiceReport(Long idService, Long idTechnician, Date startService, Date endService) {
        return iServiceReportRepository.findByIdServiceAndIdTechnicianAndStartServiceAndEndService(idService,idTechnician,startService,endService);
    }
}
