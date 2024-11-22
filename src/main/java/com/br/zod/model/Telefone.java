package com.br.zod.model;


import com.br.zod.dto.telefoneDto.CadastroTelefoneDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_JV_ZOD_TELEFONE")
public class Telefone {

   @Id
   @GeneratedValue
   @Column(name = "ID_TELEFONE", length = 8)
    private Long id;

    @Column(name = "NR_TELEFONE",nullable = false, unique = true)
    private String numero;

    @Column(name = "NR_DDD", length = 2, nullable = false)
    private String ddd;

    @Column(name = "DS_TIPO",  nullable = false)
    private String descricao;


    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;


    public Telefone(@Valid CadastroTelefoneDto dto, Usuario usuario) {
        this.numero = dto.numero();
        this.ddd = dto.ddd();
        this.descricao = dto.descricao();
        this.usuario = usuario;

    }
}
