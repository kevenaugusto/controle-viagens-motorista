package br.ufms.apsoo.controlador.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "MOTORISTA")
public class Motorista implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "NOME_COMPLETO")
    private String nomeCompleto;

    @Column(name = "CARTEIRA_DE_HABILITACAO")
    private String carteiraDeHabilitacao;

}
