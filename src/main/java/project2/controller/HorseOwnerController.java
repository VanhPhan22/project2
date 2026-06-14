package project2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project2.entity.HorseOwner;
import project2.repository.HorseOwnerRepo;

@RestController
@RequestMapping("/api/horse-owners")
public class HorseOwnerController extends AbstractCrudController<HorseOwner> {

    public HorseOwnerController(HorseOwnerRepo horseOwnerRepo) {
        super(horseOwnerRepo);
    }
}
