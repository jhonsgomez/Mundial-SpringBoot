package mundial.app.repository;

import mundial.app.Entity.Partido;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PartidoRepository extends MongoRepository<Partido, String> {
}
