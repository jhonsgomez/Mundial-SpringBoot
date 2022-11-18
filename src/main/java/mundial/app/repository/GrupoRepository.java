package mundial.app.repository;

import mundial.app.Entity.Grupo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GrupoRepository extends MongoRepository<Grupo, String> {
}
