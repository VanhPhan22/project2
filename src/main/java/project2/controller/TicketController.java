package project2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project2.entity.Ticket;
import project2.repository.TicketRepo;

@RestController
@RequestMapping("/api/tickets")
public class TicketController extends AbstractCrudController<Ticket> {

    public TicketController(TicketRepo ticketRepo) {
        super(ticketRepo);
    }
}
