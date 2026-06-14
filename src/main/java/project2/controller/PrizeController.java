package project2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project2.entity.Prize;
import project2.repository.PrizeRepo;

@RestController
@RequestMapping("/api/prizes")
public class PrizeController extends AbstractCrudController<Prize> {

    public PrizeController(PrizeRepo prizeRepo) {
        super(prizeRepo);
    }
}
