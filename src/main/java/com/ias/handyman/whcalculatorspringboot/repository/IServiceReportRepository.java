package com.ias.handyman.whcalculatorspringboot.repository;

import com.ias.handyman.whcalculatorspringboot.model.ServiceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IServiceReportRepository extends JpaRepository<ServiceReport,Long> {

    List<ServiceReport> findByIdTechnician(Long idTechnician);

    List<ServiceReport> findByIdServiceAndIdTechnicianAndStartServiceAndEndService(Long idService, Long idTechnician, Date startService, Date endService);

}
