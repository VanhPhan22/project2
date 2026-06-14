package project2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project2.entity.RaceParticipation;
import project2.repository.RaceParticipationRepo;

@RestController
@RequestMapping("/api/race-participations")
public class RaceParticipationController extends AbstractCrudController<RaceParticipation> {

    public RaceParticipationController(RaceParticipationRepo raceParticipationRepo) {
        super(raceParticipationRepo);
    }
}
