package net.fincomp.spring.fincomp.repository;

import net.fincomp.spring.fincomp.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProducerRepository extends JpaRepository<Producer,Long> {
    List<Producer> findAll();
    List<Producer> findByNameContaining(String filter);
}
