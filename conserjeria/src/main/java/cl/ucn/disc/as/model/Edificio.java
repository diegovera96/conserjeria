package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

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
public class Edificio extends BaseModel {

    /**
     * The Name
     */
    @NotNull
    private String nombre;

    /**
     * The direccion
     */
    @NotNull
    private String direccion;

    public static class EdificioBuilder {
        public Edificio build() {
            return new Edificio(
                    this.nombre,
                    this.direccion
            );
        }
    }
}