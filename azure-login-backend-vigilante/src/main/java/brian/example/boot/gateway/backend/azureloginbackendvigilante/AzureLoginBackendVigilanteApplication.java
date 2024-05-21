package brian.example.boot.gateway.backend.azureloginbackendvigilante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.util.Arrays.asList;

@CrossOrigin
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
@EnableMethodSecurity
@RestController
@RequestMapping("/bad-guys")
public class AzureLoginBackendVigilanteApplication {

    public static final List<Vigilante> VIGILANTES = asList(
            new Vigilante(0, "Tornado"),
            new Vigilante(1, "Dr Nice"),
            new Vigilante(2, "Narco"),
            new Vigilante(3, "Bombasto"),
            new Vigilante(4, "Celeritas"),
            new Vigilante(5, "Magneta"),
            new Vigilante(6, "RubberMan"),
            new Vigilante(7, "Dynama"),
            new Vigilante(8, "Dr IQ"),
            new Vigilante(9, "Magma")
    );

    //    @PreAuthorize("has('SCOPE_User.Read')")
//    @PreAuthorize("#vars.claimSet.groups=='b4626ad4-ef2a-4148-9c33-0d3e5604aca0'")
//    @PreAuthorize("#auth.tokenAttributes['name'] == 'Brian Heo'")
    @PreAuthorize("#authentication.tokenAttributes[@groupBean.getClaimField()].contains(@groupBean.getGroup1()) " +
            " || #authentication.tokenAttributes[@groupBean.getClaimField()].contains(@groupBean.getGroup2())")
    @GetMapping(value = "/vigilante/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vigilante> getHeroes(@PathVariable("id") int id
            , JwtAuthenticationToken authentication
            , HttpServletRequest request
    ) {
        System.out.println("called /bad-guys/vigilante/"+id);
//        if( authentication == null)
//            return ResponseEntity.badRequest().build();
        System.out.println("Auth:"+authentication.getTokenAttributes().get("name"));
        System.out.println("Origin:"+request.getHeader(HttpHeaders.ORIGIN));

        return ResponseEntity.ok(VIGILANTES.get(id));
    }

    public static void main(String[] args) {
        SpringApplication.run(AzureLoginBackendVigilanteApplication.class, args);
    }

}
