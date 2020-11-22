package com.ias.handyman.whcalculatorspringboot.model;



import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    
}
