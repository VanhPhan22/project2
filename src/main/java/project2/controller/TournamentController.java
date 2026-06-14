package project2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project2.entity.Tournament;
import project2.repository.TournamentRepo;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController extends AbstractCrudController<Tournament> {

    public TournamentController(TournamentRepo tournamentRepo) {
        super(tournamentRepo);
    }
}
