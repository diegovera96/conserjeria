package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.*;

import cl.ucn.disc.as.model.Departamento;
import cl.ucn.disc.as.model.Persona;

import javax.persistence.Entity;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@ToString
@AllArgsConstructor
@Builder
@Entity
@Getter

public class Contrato extends BaseModel {
    @NotNull
    @Setter
    private Persona persona;

    @NotNull
    @Setter
    private Departamento departamento;

    /**
     *The Contador
     */
    @NotNull
    @Setter
    private Instant fechaPago;

    /*@NotNull
    @Setter
    private Long idDuenio;

    @NotNull
    @Setter
    private Long idDepartamento;*/



    public static class ContratoBuilder {

        public Contrato build() {

            return new Contrato(
                    this.persona,
                    this.departamento,
                    this.fechaPago
            );
        }
        /*public Contrato build() {
            return new Contrato(
                    this.idDuenio,
                    this.idDepartamento,
                    this.fechaPago
            );
        }*/
    }
}
