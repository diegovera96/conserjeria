package cl.ucn.disc.as.model;

import cl.ucn.disc.as.dao.DepartamentoFinder;
import io.ebean.annotation.NotNull;
import lombok.*;

import cl.ucn.disc.as.model.Departamento;
import cl.ucn.disc.as.model.Persona;
import cl.ucn.disc.as.dao.PersonaFinder;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@ToString
//@AllArgsConstructor
@Builder
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

    // Constructor que toma identificadores (Long)
    public Contrato(Long idPersona, Long idDepartamento, Instant fechaPago) {
        // Aquí deberías buscar las instancias reales de Persona y Departamento
        // basadas en los identificadores proporcionados
        //this.persona = buscarPersonaPorId(idPersona);
        Optional<Persona> pf = new PersonaFinder();
        this.persona = pf.byId(idPersona);
        //this.departamento = buscarDepartamentoPorId(idDepartamento);
        Optional<Departamento> df = new DepartamentoFinder();
        this.departamento = df.byId(idDepartamento);
        this.fechaPago = fechaPago;
    }
}
