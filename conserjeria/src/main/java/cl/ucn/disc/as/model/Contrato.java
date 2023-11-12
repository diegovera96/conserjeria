package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.*;

import cl.ucn.disc.as.dao.PersonaFinder;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import java.time.Instant;
import java.util.Optional;

@ToString
//@AllArgsConstructor
@Entity
@Getter
@Slf4j
public class Contrato extends BaseModel {

    @NotNull
    @Setter
    private Persona persona;

    @NotNull
    @Setter
    private Departamento departamento;

    @NotNull
    @Setter
    private Instant fechaPago;

    // Constructor que toma Persona y Departamento
    public Contrato(Persona persona, Departamento departamento, Instant fechaPago) {
        this.persona = persona;
        this.departamento = departamento;
        this.fechaPago = fechaPago;
    }
}
