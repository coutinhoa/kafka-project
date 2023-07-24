package kafka.repositories;

import kafka.entities.ProductMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductMessageRepository extends JpaRepository<ProductMessage, Long> {

    List<ProductMessage> findAll();
}
