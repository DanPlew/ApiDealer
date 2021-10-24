package daniel.plewinski.apidealer.chucknorisjokes.database.repositories;

import daniel.plewinski.apidealer.chucknorisjokes.database.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
