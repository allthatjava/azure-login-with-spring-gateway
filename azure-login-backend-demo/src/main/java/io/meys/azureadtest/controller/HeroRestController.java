package io.meys.azureadtest.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Arrays.asList;

//@CrossOrigin(value = {"http://localhost:4200"})
@RestController
public class HeroRestController {

    public static final List<HeroDto> HEROES = asList(
            new HeroDto(0, "Tornado"),
            new HeroDto(1, "Dr Nice"),
            new HeroDto(2, "Narco"),
            new HeroDto(3, "Bombasto"),
            new HeroDto(4, "Celeritas"),
            new HeroDto(5, "Magneta"),
            new HeroDto(6, "RubberMan"),
            new HeroDto(7, "Dynama"),
            new HeroDto(8, "Dr IQ"),
            new HeroDto(9, "Magma")
    );

    @GetMapping(value = "/api/heroes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HeroDto> getHeroes() {
        System.out.println("called /api/heroes");

        return HEROES;
    }
}

class HeroDto {
    private long id;
    private String name;

    HeroDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
