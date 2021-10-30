package ir.maktab.University.repository;

import ir.maktab.University.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager,Long> {
}
