package com.ias.handyman.whcalculatorspringboot.model;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
@Table(name = "tbl_service_report")
public class ServiceReport {

    @Id
    @NotNull(message = "La identificación del técnico es obligatoria")
    @Positive(message = "La identificación del técnico debe ser mayor que 0")
    private Long idTechnician;

    @Column(name = "id_service")
    @NotNull(message = "La identificación del servicio es obligatoria")
    @Positive(message = "La identificación del servicio debe ser mayor que 0")
    private Long idService;

    @Column(name = "start_service")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "La fecha de inicio del servicio es obligatoria")
    private Date startService;

    @Column(name = "end_service")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "La fecha fin del servicio es obligatoria")
    private Date endService;

    public ServiceReport() {
    }

    public ServiceReport(Long idTechnician) {
        this.idTechnician = idTechnician;
    }

    public ServiceReport(Long idTechnician, Long idService, Date startService, Date endService) {
        this.idTechnician = idTechnician;
        this.idService = idService;
        this.startService = startService;
        this.endService = endService;
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

    public Date getStartService() {
        return startService;
    }

    public void setStartService(Date startService) {
        this.startService = startService;
    }

    public Date getEndService() {
        return endService;
    }

    public void setEndService(Date endService) {
        this.endService = endService;
    }


}
