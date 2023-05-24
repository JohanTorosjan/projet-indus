package com.developper.projetindus.repository;

import com.developper.projetindus.entity.InfrastructureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface InfrastructureRepository extends JpaRepository<InfrastructureEntity,Long> {
    @Query(value = "SELECT i.id,i.name,i.adresse, i.id_schedule,i.id_type " +
            "FROM databaseschema.event e JOIN databaseschema.infrastructure " +
            "i on i.id = e.id_infrastructure WHERE (:starting_hour>=starting_acceptation_beginning_hour AND :starting_hour<= starting_acceptation_ending_hour) AND (:ending_hour>=stopping_acceptation_beginning_hour AND :ending_hour<=stopping_acceptation_ending_hour) " +
            "AND i.id_type =:id_type",nativeQuery = true)
    List<InfrastructureEntity> getMatchingInfrastructure(@Param("starting_hour") Timestamp starting_hour, @Param("ending_hour") Timestamp ending_hour, @Param("id_type") Long id_type);

    @Query(value = "SELECT i.id AS infrastructure_id, c.id AS category_id, AVG(r.note) AS avg_note\n" +
            "FROM databaseschema.infrastructure i\n" +
            "JOIN databaseschema.rating_infrastructure r ON i.id = r.id_infrastructure\n" +
            "JOIN databaseschema.category c ON c.id = r.id_category\n" +
            "WHERE i.id NOT IN (SELECT event.id_infrastructure FROM databaseschema.event)\n" +
            "AND c.id IN (\n" +
            "  SELECT c2.id\n" +
            "  FROM databaseschema.rating_infrastructure r2\n" +
            "  JOIN databaseschema.category c2 ON r2.id_category = c2.id\n" +
            "  WHERE r2.id_infrastructure = i.id\n" +
            "  GROUP BY c2.id\n" +
            "  ORDER BY AVG(r2.note) DESC\n" +
            "  LIMIT 10\n" +
            ")\n" +
            "GROUP BY i.id, c.id\n" +
            "ORDER BY i.id, AVG(r.note) DESC;",nativeQuery = true)
    List<Object[]> getFreeInfrastrcutures();

    @Query(value = "SELECT * from databaseschema.infrastructure i where i.id_type=:id_type AND i.id NOT IN (SELECT id_infrastructure from databaseschema.event);",nativeQuery = true)
    List<InfrastructureEntity> getFreeInfrastructuresIds(@Param("id_type") long id_type);
}
