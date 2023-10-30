/*
 * CopyRight (c) 2023. Arquitectura de Software, Disc, UCN
 */

package cl.ucn.disc.as.ui;

import io.javalin.Javalin;

/**
 * The Routes Config
 *
 * @author Arquitectura de Software
 */
public interface RoutesConfigurator {
    /**
     *Configure the routers
     *
     * @param Javalin to use
     */
    void configure(Javalin javalin);
}