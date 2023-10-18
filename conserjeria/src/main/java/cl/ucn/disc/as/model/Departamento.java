package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import javax.persistence.Entity;
import java.time.Instant;

@ToString
@AllArgsConstructor
@Builder
@Entity
public class Departamento extends BaseModel  {
    /**
     * The Apartment Number
     */
    @NotNull
    private Integer numero;

    /**
     * The Piso
     */
    @NotNull
    private String piso;

    public static class DepartamentoBuilder {
        public Departamento build() {
            return new Departamento(
                    this.numero,
                    this.piso
            );
        }
    }
}
