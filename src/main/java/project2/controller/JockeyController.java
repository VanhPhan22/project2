package project2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project2.entity.Jockey;
import project2.repository.JockeyRepo;

@RestController
@RequestMapping("/api/jockeys")
public class JockeyController extends AbstractCrudController<Jockey> {

    public JockeyController(JockeyRepo jockeyRepo) {
        super(jockeyRepo);
    }
}
