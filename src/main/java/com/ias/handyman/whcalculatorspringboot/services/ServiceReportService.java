package com.ias.handyman.whcalculatorspringboot.services;

import com.ias.handyman.whcalculatorspringboot.model.ServiceReport;
import com.ias.handyman.whcalculatorspringboot.repository.IServiceReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceReportService implements IServiceReportService{

    @Autowired
    private IServiceReportRepository iServiceReportRepository;

    // Generamos el reporte del servicio
    @Override
    public ServiceReport createServiceReport(ServiceReport serviceReport) {
        return iServiceReportRepository.save(serviceReport);
    }

    // Obtenemos todos los servicios de un técnico
    @Override
    public List<ServiceReport> getServicesByTechnician(Long idTechnician) {
        return iServiceReportRepository.findByIdTechnician(idTechnician);
    }


    // Obtenemos un servicio específico que cumpla con todos los campos (para no repetir un mismo servicio)
    @Override
    public ServiceReport getServiceReport(ServiceReport serviceReport) {
       Optional <ServiceReport> s = iServiceReportRepository.findByIdServiceAndIdTechnicianAndStartServiceAndEndService(serviceReport.getIdService(),serviceReport.getIdTechnician(),serviceReport.getStartService(),serviceReport.getEndService());

       if(!s.isPresent()){
           System.out.println("No lo encontró");
            return null;
        }
        return s.get();
    }

    // obtenemos el servicio que ya se reporto en la misma fecha por un técnico
    @Override
    public ServiceReport getServiceReportByTechAndDate(ServiceReport serviceReport) {
        Optional <ServiceReport> s = iServiceReportRepository.findByIdTechnicianAndStartServiceAndEndService(serviceReport.getIdTechnician(),serviceReport.getStartService(),serviceReport.getEndService());

        if(!s.isPresent()){
            System.out.println("No lo encontró");
            return null;
        }
        return s.get();
    }
}
