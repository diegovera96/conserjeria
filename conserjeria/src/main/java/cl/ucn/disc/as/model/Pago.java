package cl.ucn.disc.as.model;

import cl.ucn.disc.as.model.exceptions.IllegalDomainException;
import cl.ucn.disc.as.utils.ValidationUtils;
import io.ebean.annotation.NotNull;
import lombok.*;
import cl.ucn.disc.as.model.Persona;

import javax.persistence.Entity;
import java.time.Instant;

/**
 * Class Edificio
 *
 * @autor Arquitectura de sistemas
 */

@ToString(callSuper = true)
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Pago extends BaseModel{

    /**
     * The fecha de pago
     */
    @NotNull
    private Instant fechaPago;

    @NotNull
    private Integer monto;

    public static class PagoBuilder {
        public Pago build() {
            return new Pago(
                    this.fechaPago,
                    this.monto
            );
        }
    }
}
