package project2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project2.entity.Admin;
import project2.repository.AdminRepo;

@RestController
@RequestMapping("/api/admins")
public class AdminController extends AbstractCrudController<Admin> {

    public AdminController(AdminRepo adminRepo) {
        super(adminRepo);
    }
}
