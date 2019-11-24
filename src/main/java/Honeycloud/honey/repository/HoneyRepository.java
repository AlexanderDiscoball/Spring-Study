package Honeycloud.honey.repository;

import Honeycloud.honey.entity.Honey;
import org.springframework.data.repository.CrudRepository;

public interface HoneyRepository extends CrudRepository<Honey,String> {
}
