package extranet.proyecto;

import extranet.proyecto.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@SpringBootApplication
public class ProyectoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoApplication.class, args);
    }

}

@Configuration
// @Profile("actuator-endpoints") /* if you want: register bean only if profile is set */
class HttpTraceActuatorConfiguration {

    @Bean
    public HttpTraceRepository httpTraceRepository() {

        return new InMemoryHttpTraceRepository();
    }

}


@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

//    @Bean
//    CommandLineRunner initDatabase(UserRepository repository) {
//
//        return args -> {
////			log.info("Preloading " + repository.save(new User("Sergio","sergio@gmail.com","6400103006")));  // Descomentar para añadir usuario hardcoded
//        };
//    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
//				.groupName("User-api") // Este metodo añade a la ruta de api-docs lo que le pases
                .select()
				.apis(RequestHandlerSelectors.any()) // No se aun muy bien como funciona
                .paths(regex("/api.*")) // Este metodo obtiene todos los paths posibles de la ruta que se indique
                .build()
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        return new ApiInfo(
                "spring-mvc-rest",
                "Spring user api with SpringFOX",
                "",
                "",
                null,
                "",
                "",
                Collections.emptyList());
    }
}
