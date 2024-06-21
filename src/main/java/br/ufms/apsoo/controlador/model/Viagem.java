package br.ufms.apsoo.controlador.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "VIAGEM")
public class Viagem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinTable(name = "MOTORISTA", joinColumns = {@JoinColumn(name = "ID_MOTORISTA", referencedColumnName = "ID")})
    @Column(name = "ID_MOTORISTA")
    private UUID idMotorista;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinTable(name = "VEICULO", joinColumns = {@JoinColumn(name = "ID_VEICULO", referencedColumnName = "ID")})
    @Column(name = "ID_VEICULO")
    private UUID idVeiculo;

    @Column(name = "HORA_INICIAL")
    private Date horaInicial;

    @Column(name = "HORA_FINAL")
    private Date horaFinal;

    @Column(name = "QUILOMETRAGEM_FINAL")
    private int quilometragemFinal;

}
