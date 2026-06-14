package project2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project2.entity.RaceReferee;
import project2.repository.RaceRefereeRepo;

@RestController
@RequestMapping("/api/race-referees")
public class RaceRefereeController extends AbstractCrudController<RaceReferee> {

    public RaceRefereeController(RaceRefereeRepo raceRefereeRepo) {
        super(raceRefereeRepo);
    }
}
