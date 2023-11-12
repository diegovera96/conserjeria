package cl.ucn.disc.as.services;

import cl.ucn.disc.as.model.Departamento;
import cl.ucn.disc.as.model.Edificio;
import cl.ucn.disc.as.model.Persona;
import cl.ucn.disc.as.model.Contrato;
import cl.ucn.disc.as.model.Pago;
import io.ebean.annotation.NotNull;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import java.time.Instant;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;


/**
 * System Operations
 *
 * @autor Arquitectura de sistema
 */
public interface Sistema {
    /**
     * Agregar un edificio al sistema
     *
     * @param edificio a agregar
     */
   Edificio add(Edificio edificio);
   /*
   * Agregar una persona al sistema
   *
   * @param edificio a agregar
   * */
   Persona add(Persona persona);

   Departamento addDepartamento(Departamento departamento, Edificio edificio);
   //Departamento addDepartamento(Departamento departamento, Long idEdificio);

   Contrato realizarContrato(Persona duenio, Departamento departamento, Instant fechaPago);
   //Contrato realizarContrato(Long idDuenio, Long idDepartamento, Instant fechaPago);

   List<Persona> getPersona();
   Optional<Persona> getPersona(String rut);
   List<Contrato> getContrato();
   List<Pago> getPago(String rut);
   void populate();
}
