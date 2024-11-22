package com.br.zod.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_JV_ZOD_RELATORIO")
public class Relatorio {

    @Id
    @GeneratedValue
    @Column(name = "ID_RELATORIO", length = 8)
    private Long id;

    @Column(name = "DT_REFERENCIA")
    private LocalDate dataRerencia;

    @Column(name = "DS_TEMPO_USO")
    private String tempo;

    @Column(name = "DS_CONSUMO")
    private String consumo;

    @Column(name = "DT_REGISTRO")
    private LocalDateTime dataRegistro;

    @ManyToOne
    @JoinColumn(name = "ID_DISPOSITIVO", nullable = false)
    private Dispositivo dipositivo;



}
