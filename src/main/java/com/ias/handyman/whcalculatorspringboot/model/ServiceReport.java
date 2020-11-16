package com.ias.handyman.whcalculatorspringboot.model;



import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.jfr.Timestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.Date;

//@ServiceReportDateTime // validador de la clase
@Entity
@Table(name = "tbl_service_report")
public class ServiceReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServiceReport;

    @NotNull(message = "La identificación del técnico es obligatoria")
    @Positive(message = "La identificación del técnico debe ser mayor que 0")
    private Long idTechnician;

    @Column(name = "id_service")
    @NotNull(message = "La identificación del servicio es obligatoria")
    @Positive(message = "La identificación del servicio debe ser mayor que 0")
    private Long idService;

    @Column(name = "start_service")
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    //@Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "La fecha de inicio del servicio es obligatoria")
    private LocalDateTime startService;

    @Column(name = "end_service")
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    //@Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "La fecha fin del servicio es obligatoria")
    private LocalDateTime endService;

    public ServiceReport() {
    }

    public ServiceReport(Long idTechnician) {
        this.idTechnician = idTechnician;
    }

    public ServiceReport(Long idServiceReport, @NotNull(message = "La identificación del técnico es obligatoria") @Positive(message = "La identificación del técnico debe ser mayor que 0") Long idTechnician, @NotNull(message = "La identificación del servicio es obligatoria") @Positive(message = "La identificación del servicio debe ser mayor que 0") Long idService, @NotNull(message = "La fecha de inicio del servicio es obligatoria") LocalDateTime startService, @NotNull(message = "La fecha fin del servicio es obligatoria") LocalDateTime endService) {
        this.idServiceReport = idServiceReport;
        this.idTechnician = idTechnician;
        this.idService = idService;
        this.startService = startService;
        this.endService = endService;
    }

    public Long getIdServiceReport() {
        return idServiceReport;
    }

    public void setIdServiceReport(Long idServiceReport) {
        this.idServiceReport = idServiceReport;
    }

    public Long getIdTechnician() {
        return idTechnician;
    }

    public void setIdTechnician(Long idTechnician) {
        this.idTechnician = idTechnician;
    }

    public Long getIdService() {
        return idService;
    }

    public void setIdService(Long idService) {
        this.idService = idService;
    }

    public LocalDateTime getStartService() {
        return startService;
    }

    public void setStartService(LocalDateTime startService) {
        this.startService = startService;
    }

    public LocalDateTime getEndService() {
        return endService;
    }

    public void setEndService(LocalDateTime endService) {
        this.endService = endService;
    }
}
