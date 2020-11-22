package com.ias.handyman.whcalculatorspringboot.repository;

import com.ias.handyman.whcalculatorspringboot.model.ServiceReport;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IServiceReportRepository extends JpaRepository<ServiceReport,Long> {

    List<ServiceReport> findByIdTechnician(Long idTechnician);

    Optional<ServiceReport> findByIdServiceAndIdTechnicianAndStartServiceAndEndService(Long idService, Long idTechnician, LocalDateTime startService, LocalDateTime endService);

    Optional<ServiceReport> findByIdTechnicianAndStartServiceAndEndService(Long idTechnician, LocalDateTime startService, LocalDateTime endService);

}
