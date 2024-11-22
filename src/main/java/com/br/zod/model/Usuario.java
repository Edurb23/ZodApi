package com.br.zod.model;

import com.br.zod.dto.usuarioDto.AtualizacaoUsuario;
import com.br.zod.dto.usuarioDto.CadastroUsuarioDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_JV_ZOD_USUARIO")
@SequenceGenerator(name = "seq_usuario", sequenceName = "SEQ_TB_JV_ZOD_USUARIO", allocationSize = 1)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_usuario")
    @Column(name = "ID_USUARIO", length = 8)
    private Long id;

    @Column(name = "NM_USUARIO")
    private String nome;

    @Column(name = "NR_CPF")
    private String cpf ;

    @Column(name = "NR_RG")
    private String rg;

    @Column(name = "DT_DATANASCIMENTO")
    private LocalDate dataDeNascimento;

    @OneToOne
    @JoinColumn(name = "ID_LOGIN", nullable = false, unique = true)
    private Login login;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Telefone>telefones;

    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL)
    private List<Dispositivo>dipositivos;


    public Usuario(@Valid CadastroUsuarioDto dto, Login login) {
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.rg = dto.rg();
        this.dataDeNascimento = dto.dataDeNascimento();
        this.login = login;
    }

    public void atualizarUsuario(AtualizacaoUsuario dto) {
        if(dto.nome() != null)
            nome = dto.nome();
        if(dto.cpf() != null)
            cpf = dto.cpf();
        if(dto.rg() != null)
            rg = dto.rg();
        if(dto.dataDeNascimento() != null)
            dataDeNascimento = dto.dataDeNascimento();
    }
}
