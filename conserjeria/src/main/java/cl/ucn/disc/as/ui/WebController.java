/*
 * CopyRight (c) 2023. Arquitectura de Software, Disc, UCN
 */

package cl.ucn.disc.as.ui;

import cl.ucn.disc.as.model.Persona;
import cl.ucn.disc.as.services.Sistema;
import cl.ucn.disc.as.services.SistemaImpl;
import io.ebean.DB;
import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;

import java.util.Optional;

public final class WebController implements RoutesConfigurator{

    /**
     * The Sistema
     */
    private final Sistema sistema;

    /**
     * the webController
     */
    public WebController() {
        this.sistema = new SistemaImpl(DB.getDefault());
        // FIXME: only populate in case of new database
        //this.sistema.populate();
    }

    @Override
    public void configure(final Javalin app) {
        app.get("/", ctx->{
            ctx.result("Welcome to Conserjeria API REST");
        });
        app.get("/persona/rut/{rut}", ctx->{
            String rut = ctx.pathParam("rut");
            Optional<Persona> oPersona = this.sistema.getPersona();
            ctx.json(oPersona.orElseThrow(()-> new NotFoundResponse("Can't find Persona with rut:"+ rut)));
        });
    }
}