package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.*;

import javax.persistence.Entity;
import java.time.Instant;

@ToString
@AllArgsConstructor
@Builder
@Entity
@Getter
public class Departamento extends BaseModel  {
    /**
     * The Apartment Number
     */
    @NotNull
    @Setter
    private Integer numero;

    /**
     * The Piso
     */
    @NotNull
    @Setter
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
