/**
 * copyright (c) 2023, Arquitectura de Software, Disc, UCN
 */

package cl.ucn.disc.as.ui;

// Imports
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import io.javalin.Javalin;
import io.javalin.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;

@Slf4j
public final class ApiRestServer{

    /**
     * Nothing here
     */
    private ApiRestServer(){
        //nothing
    }

    /**
     * @Return confiugured Gson
     */
    private static Gson createAndConfiguredGson(){
        // Instant serializer
        TypeAdapter<Instant> instantTypeAdapter  = new TypeAdapter<>(){
            /**
             * Write one Json value
             * for {@code value}
             *
             * @param out
             * @param instant
             */
            @Override
            public void write(JsonWriter out, Instant instant) throws IOException{
                if (instant==null) {
                    out.nullValue();
                } else {
                    out.value(instant.toEpochMilli());

                }
            }

            /**
             *  Reads one Json Value
             *
             * @param in
             * @return the convert java object
             */
            @Override
            public Instant read(JsonReader in) throws IOException{
                if (in.peek() == JsonToken.NULL){
                    in.nextNull();
                    return null;
                }
                return Instant.ofEpochMilli(in.nextLong());
            }
        };
        // the Gson serializer
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Instant.class, instantTypeAdapter)
                .create();
    }
    private static Javalin createAndConfigureJavalin(){
        JsonMapper jsonMapper = new JsonMapper(){
            // the gson config
            private static final Gson GSON = createAndConfiguredGson();
            /**
             * json to object
             */
            @NotNull
            @Override
            public <T> T fromJsonString(@NotNull String json, @NotNull Type targetType){
                return GSON.fromJson(json, targetType);
            }
            /**
             * json to object
             */
            @NotNull
            @Override
            public String toJsonString(@NotNull Object obj, @NotNull Type type){

                return GSON.toJson(obj, type);
            }
        };

        //configure the server
        return Javalin.create(config->{
            config.jsonMapper(jsonMapper);

            config.compression.gzipOnly(9);

            config.requestLogger.http((ctx, ms)->{
                log.debug("servered: {} in {} ms.", ctx.fullUrl(), ms);
            });
            //config.pluggins.enableDevLoggin();
        });
    }
    /**
     * Starting the server
     *
     * @param port to use
     */
    public static Javalin start(final Integer port, final RoutesConfigurator routesConfigurator){
        if (port < 1024 || port > 65535){
            log.error("BAD port:" + port);
            throw new IllegalArgumentException("BAD port:"+port);
        }
        log.debug("Starting api rest server in port {} .."+port);

        // the server
        Javalin app = createAndConfigureJavalin();

        //configure the paths
        routesConfigurator.configure(app);

        //the hookup thread
        Runtime.getRuntime().addShutdownHook(new Thread(app::stop));

        // hooks to detect to shutdown
        app.events(event->{
            event.serverStarting(()->{
                log.debug("starting the Javaling server");
            });
            event.serverStarted(()->{
                log.debug("Server started!");
            });
            event.serverStopping(()->{
                log.debug("Server stopping");
            });
            event.serverStopped(()->{
                log.debug("Server Stop!");
            });
        });

        //Start
        return app.start(port);

    }
}