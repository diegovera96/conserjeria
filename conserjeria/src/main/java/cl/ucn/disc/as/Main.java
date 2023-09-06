package cl.ucn.disc.as;


import cl.ucn.disc.as.dao.PersonaFinder;
import cl.ucn.disc.as.model.Contrato;
import cl.ucn.disc.as.model.Persona;
import io.ebean.DB;
import io.ebean.Database;
import lombok.extern.slf4j.Slf4j;

import java.sql.DatabaseMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 *The Main
 *@author Profesor Diego Urrutia-Astorga
  */
@Slf4j
public class Main {
    /**
    *The Main
     *
     * @param args to use.
     */
    public static void main(String[] args){

        log.debug("Starting Main..");

        log.debug("Starting Main..");

        //get the database
        Database db = DB.getDefault();

        Persona persona = Persona.builder()
                .rut("19100636-4")
                .nombre("Diego")
                .apellidos("Vera Santis")
                .email("diego@gmail.com")
                .telefono("123456789")
                .build();

        log.debug("The Persona before db: ${}", persona);

        //save into the database
        db.save(persona);

        log.debug("The Persona after db: ${}", persona);

        //finder de personas
        PersonaFinder pf = new PersonaFinder();
        Optional<Persona> oPersona = pf.byRut("19100636-4");
        oPersona.ifPresentOrElse(p -> log.debug("Persona from db: {}", p),
                                () -> log.debug("Persona no encontrada")
                                );

        log.debug("Done. :)");

        log.debug("********************************************************************");

        //Date fecha = new Date();
        //DateFormat formateador= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        //try {
        //    fecha = formateador.parse("31/08/2023 15:50:00");
        //    System.out.println("Fecha parseada: " + fecha);
        //} catch (java.text.ParseException e) {
        //    System.out.println("Error al parsear la fecha: " + e.getMessage());
        //}
        //Contrato contrato = Contrato.builder()
        //                    .fechaPago(fecha)
        //                    .build();

        //System.out.println("Diferencia: " + contrato.diferenciaDeDias(fecha));
        //log.debug("The Contrato: ${}", contrato);
    }
}
