package project2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project2.entity.Horse;
import project2.repository.HorseRepo;

@RestController
@RequestMapping("/api/horses")
public class HorseController extends AbstractCrudController<Horse> {

    public HorseController(HorseRepo horseRepo) {
        super(horseRepo);
    }
}
