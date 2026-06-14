package project2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project2.entity.Prediction;
import project2.repository.PredictionRepo;

@RestController
@RequestMapping("/api/predictions")
public class PredictionController extends AbstractCrudController<Prediction> {

    public PredictionController(PredictionRepo predictionRepo) {
        super(predictionRepo);
    }
}
