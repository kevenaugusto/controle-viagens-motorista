package br.ufms.apsoo.controlador.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "VIAGEM")
public class Viagem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Motorista motoristaDesignado;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Veiculo veiculoDesignado;

    @Column(name = "HORA_INICIAL")
    private Date horaInicial;

    @Column(name = "HORA_FINAL")
    private Date horaFinal;

    @Column(name = "QUILOMETRAGEM_FINAL")
    private int quilometragemFinal;

    @Column(name = "HORA_CRIACAO")
    private Date horaCriacao;

}
