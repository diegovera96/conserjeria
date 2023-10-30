/*
 * Copyright (c) 2023. Arquitectura de Sistemas, DISC, UCN.
 */

package cl.ucn.disc.as.dao;

import cl.ucn.disc.as.model.Departamento;
import cl.ucn.disc.as.model.query.QDepartamento;
import io.ebean.Finder;
import org.jetbrains.annotations.NotNull;
import java.util.Optional;
/**
 * The Finder of Persona.
 *
 * @author Diego Urrutia-Astorga.
 */
public class DepartamentoFinder extends Finder<Long, Departamento> {
    /**
     * The Constructor.
     */
    public DepartamentoFinder() {
        super(Departamento.class);
    }

    /**
     * Find a Persona by rut.
     *
     * @param numero to use.
     * @return the Departamento.
     */
    public Optional<Departamento> byNumero(@NotNull Integer numero) {
        return new QDepartamento().numero.eq(numero).findOneOrEmpty();
    }
}