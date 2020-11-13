package com.ias.handyman.whcalculatorspringboot.repository;

import com.ias.handyman.whcalculatorspringboot.model.ServiceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface IServiceReportRepository extends JpaRepository<ServiceReport,Long> {

    List<ServiceReport> findByIdTechnician(Long idTechnician);

    Optional<ServiceReport> findByIdServiceAndIdTechnicianAndStartServiceAndEndService(Long idService, Long idTechnician, Date startService, Date endService);

    Optional<ServiceReport> findByIdTechnicianAndStartServiceAndEndService(Long idTechnician, Date startService, Date endService);

}
