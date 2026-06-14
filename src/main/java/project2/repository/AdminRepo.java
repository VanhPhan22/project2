package project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project2.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
}
