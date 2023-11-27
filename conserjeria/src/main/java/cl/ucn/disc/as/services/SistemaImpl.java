package cl.ucn.disc.as.services;
import cl.ucn.disc.as.exceptions.SistemaException;
import cl.ucn.disc.as.model.Edificio;
import cl.ucn.disc.as.model.Persona;
import cl.ucn.disc.as.model.Departamento;
import cl.ucn.disc.as.model.Contrato;
import cl.ucn.disc.as.model.Pago;
import io.ebean.Database;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.PersistenceException;
import java.time.Instant;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Locale;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValues;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;


@Slf4j
@Getter
@Setter
public class SistemaImpl implements Sistema {
    public SistemaImpl(Database database){
        this.database = database;
    }
    private final Database database;
    private final List<Contrato> contratos = new ArrayList<>();
    private final List<Persona> personas = new ArrayList<>();
    private final List<Pago> pagos = new ArrayList<>();

    /**
     *{@inheritDoc}
     */
    @Override
    public Edificio add(@NotNull Edificio edificio){
        try {
            this.database.save(edificio);
            return edificio;
        } catch (PersistenceException ex) {
            //TODO: save the exception
            log.error("Error", ex);
            throw new SistemaException("Error al agregar un edificio", ex);
        }
    }

    @Override
    public Persona add(@NotNull Persona persona){
        try{
            this.database.save(persona);
            return persona;
        } catch (PersistenceException ex){
            log.error("Error", ex);
            throw new SistemaException("Error al agregar una persona", ex);
        }
    }
    @Override
    public Departamento addDepartamento(@NotNull Departamento departamento, @NotNull Edificio edificio){
        try{
            this.database.save(departamento);
            this.database.save(edificio);
            return departamento;
        } catch (PersistenceException ex){
            log.error("Error", ex);
            throw new SistemaException("Error al agregar un departamento", ex);
        }
    }
    /**@Override
    public Departamento addDepartamento(@NotNull Departamento departamento, @NotNull Long idEdificio){
        try{
            this.database.save(departamento);
            this.database.save(idEdificio);
            return departamento;
        } catch (PersistenceException ex){
            log.error("Error", ex);
            throw new SistemaException("Error al agregar un departamento", ex);
        }
    }**/
    @Override
    public Contrato realizarContrato(@NotNull Persona duenio, @NotNull Departamento departamento, @NotNull Instant fechaPago){
        try{
            Contrato contrato = new Contrato(duenio, departamento, fechaPago);
            contrato.setFechaPago(fechaPago);
            this.database.save(contrato);
            return contrato;
        } catch (PersistenceException ex){
            log.error("Error", ex);
            throw new SistemaException("Error al agregar un contrato", ex);
        }
    }
    /*@Override
    public Contrato realizarContrato(@NotNull Long idDuenio, @NotNull Long idDepartamento, @NotNull Instant fechaPago){
        try{
            Contrato contrato = new Contrato(idDuenio, idDepartamento, fechaPago);
            contrato.setFechaPago(fechaPago);
            this.database.save(contrato);
            return contrato;
        } catch (PersistenceException ex){
            log.error("Error", ex);
            throw new SistemaException("Error al agregar un contrato", ex);
        }
    }*/

    @Override
    public List<Persona> getPersona() {
        List<Persona> personas = database.find(Persona.class).findList();
        return personas;
    }

    @Override
    public List<Contrato> getContrato() {
        List<Contrato> contratos = database.find(Contrato.class).findList();
        return contratos;
    }

    @Override
    public Optional<Persona> getPersona(String rut) {
        Persona persona = database.find(Persona.class)
                .where()
                .eq("rut", rut)
                .findOne();

        return Optional.ofNullable(persona);
    }

    @Override
    public List<Pago> getPago(String rut) {
        List<Pago> pagos = database.find(Pago.class)
                .where()
                .eq("rut", rut)
                .findList();
        return pagos;
    }


    @Override
    public void populate() {
        {
            Persona persona = Persona.builder()
                    .rut("19100636-4")
                    .nombre("Diego")
                    .apellidos("Vera")
                    .email("diego@gmail.com")
                    .telefono("11111111")
                    .build();
            this.database.save(persona);
        }

        Locale locale = new Locale("es-CL");
        FakeValuesService fvs = new FakeValuesService(locale, new RandomService());
        Faker faker = new Faker(locale);

        for (int i = 0; i < 1000; i++) {

            Persona persona = Persona.builder()
                    .rut(fvs.bothify("#######-#"))
                    .nombre(faker.name().firstName())
                    .apellidos(faker.name().lastName())
                    .email(fvs.bothify("???###@gmail.com"))
                    .telefono(fvs.bothify("+569########"))
                    .build();
            this.database.save(persona);
        }
    }
}
