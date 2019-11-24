package Honeycloud.honey.repository;

import Honeycloud.honey.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository <Order, Long> {
}
