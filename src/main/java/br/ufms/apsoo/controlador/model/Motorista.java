package br.ufms.apsoo.controlador.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "MOTORISTA")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Motorista extends Usuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "VALIDADE_CNH")
    private Date validadeCnh;

    public Motorista(UUID uuid, String nomeCompleto, String telefone, String cpf, Date validadeCnh) {
        super(uuid, nomeCompleto, telefone, cpf);
        this.validadeCnh = validadeCnh;
    }

    @Override
    public String toString() {
        return super.getNomeCompleto();
    }

}
