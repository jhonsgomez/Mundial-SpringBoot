package mundial.app.repository;

import mundial.app.Entity.Equipo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EquipoRepository extends MongoRepository<Equipo, String> { }
