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
        } catch (PersistenceException ex) {
            //TODO: save the exception
            log.error("Error", ex);
            throw new SistemaException("Error al agregar un edificio", ex);
        }
        return edificio;
    }

    @Override
    public Persona add(@NotNull Persona persona){
        try{
            this.database.save(persona);
        } catch (PersistenceException ex){
            log.error("Error", ex);
            throw new SistemaException("Error al agregar una persona", ex);
        }
        return persona;
    }

    public Departamento addDepartamento(@NotNull Departamento departamento, @NotNull Edificio edificio){
        try{
            this.database.save(departamento);
            this.database.save(edificio);
        } catch (PersistenceException ex){
            log.error("Error", ex);
            throw new SistemaException("Error al agregar un departamento", ex);
        }
        return departamento;
    }

    public Departamento addDepartamento(@NotNull Departamento departamento, @NotNull Long idEdificio){
        try{
            this.database.save(departamento);
            this.database.save(idEdificio);
        } catch (PersistenceException ex){
            log.error("Error", ex);
            throw new SistemaException("Error al agregar un departamento", ex);
        }
        return departamento;
    }

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
    }

    @Override
    public List<Persona> getPersona() {
        this.database.find(Persona.class).findList().addAll(personas);
        return personas;
    }

    @Override
    public List<Contrato> getContrato() {
        this.database.find(Contrato.class).findList().addAll(contratos);
        return contratos;
    }

    @Override
    public List<Pago> getPago() {
        this.database.find(Pago.class).findList().addAll(pagos);
        return pagos;
    }
}
