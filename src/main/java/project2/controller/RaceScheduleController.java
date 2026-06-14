package project2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project2.entity.RaceSchedule;
import project2.repository.RaceScheduleRepo;

@RestController
@RequestMapping("/api/race-schedules")
public class RaceScheduleController extends AbstractCrudController<RaceSchedule> {

    public RaceScheduleController(RaceScheduleRepo raceScheduleRepo) {
        super(raceScheduleRepo);
    }
}
