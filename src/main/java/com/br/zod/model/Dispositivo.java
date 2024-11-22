package com.br.zod.model;

import com.br.zod.dto.dispositivo.NovoDispositivoDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_JV_ZOD_DISPOSITIVO")
@SequenceGenerator(name = "seq_dispositivo", sequenceName = "SEQ_TB_JV_ZOD_DISPOSITIVO", allocationSize = 1)
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_dispositivo")
    @Column(name = "ID_DISPOSITIVO", length = 8)
    private Long id;
    @Column(name = "NM_DISPOSITIVO", nullable = false)
    private String nome;
    @Column(name = "DS_DISPOSITIVO", nullable = false)
    private String descricao;
    @Column(name = "NR_WATTS", nullable = false)
    private String watts;
    @Column(name = "DS_STATUS")
    private char status;

    @OneToMany(mappedBy = "dipositivo", cascade = CascadeType.ALL)
    private List<Relatorio>relatorios;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    public Dispositivo(@Valid NovoDispositivoDto dto, Usuario usuario) {
        this.nome = dto.nome();
        this.descricao = dto.descricao();
        this.watts = dto.watts();
        this.status = dto.status();
        this.usuario = usuario;
    }
}
