package com.developper.projetindus.repository;

import com.developper.projetindus.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
    @Query(value = "SELECT c.id, c.name FROM databaseschema.answer_usages_questions q INNER JOIN databaseschema.answer_usages_questions_category " +
            "qc ON q.id = qc.id_answer_usages_questions "+
            "INNER JOIN databaseschema.category c ON c.id = qc.id_category "+
            "WHERE q.id =:id_auq AND qc.boost IS NOT NULL;",nativeQuery = true)
    List<CategoryEntity> getBoostedCategories(@Param("id_auq")long id_auq);

    @Query(value = "SELECT c.id, c.name\n" +
            "FROM databaseschema.user u\n" +
            "INNER JOIN databaseschema.rating_user ru ON u.id = ru.id_user\n" +
            "INNER JOIN databaseschema.category c ON c.id = ru.id_category\n" +
            "WHERE u.id = :id_user\n" +
            "group by c.id,c.name\n" +
            "ORDER BY avg(ru.note) DESC\n",nativeQuery = true)
    List<CategoryEntity> getUserMainCategories(@Param("id_user")long id_user);

    @Query(value = "SELECT c.id,c.name\n" +
            "FROM databaseschema.infrastructure i\n" +
            "INNER JOIN databaseschema.rating_infrastructure ri ON i.id = ri.id_infrastructure\n" +
            "INNER JOIN databaseschema.category c ON c.id = ri.id_category\n" +
            "WHERE i.id = :id_infrastructure\n" +
            "GROUP BY c.id, c.name\n" +
            "ORDER BY avg(ri.note) DESC\n" +
            "LIMIT 10;",nativeQuery = true)
    List<CategoryEntity> getInfrastructureMainCategories(@Param("id_infrastructure") long id_infrastructure);
}
