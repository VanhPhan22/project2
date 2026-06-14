package project2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project2.entity.Spectator;
import project2.repository.SpectatorRepo;

@RestController
@RequestMapping("/api/spectators")
public class SpectatorController extends AbstractCrudController<Spectator> {

    public SpectatorController(SpectatorRepo spectatorRepo) {
        super(spectatorRepo);
    }
}
