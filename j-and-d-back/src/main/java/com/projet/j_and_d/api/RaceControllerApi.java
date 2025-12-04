package com.projet.j_and_d.api;

import com.projet.j_and_d.model.Race;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/race")
@CrossOrigin(origins = "http://localhost:4200") // autorise Angular
public class RaceControllerApi {

    @GetMapping
    public List<Race> getAllRaces() {
        return Arrays.asList(Race.values());
    }
}