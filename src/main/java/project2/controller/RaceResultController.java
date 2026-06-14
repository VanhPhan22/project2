package project2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project2.entity.RaceResult;
import project2.repository.RaceResultRepo;

@RestController
@RequestMapping("/api/race-results")
public class RaceResultController extends AbstractCrudController<RaceResult> {

    public RaceResultController(RaceResultRepo raceResultRepo) {
        super(raceResultRepo);
    }
}
