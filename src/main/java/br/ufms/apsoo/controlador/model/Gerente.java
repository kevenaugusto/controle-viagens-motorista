package br.ufms.apsoo.controlador.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "GERENTE")
@SuppressWarnings("unused")
public class Gerente extends Motorista {

    public Gerente(UUID uuid, String nomeCompleto, String telefone, String cpf, Date validadeCnh) {
        super(uuid, nomeCompleto, telefone, cpf, validadeCnh);
    }

}
