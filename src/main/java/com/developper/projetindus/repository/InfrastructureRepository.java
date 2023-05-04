package com.developper.projetindus.repository;

import com.developper.projetindus.entity.InfrastructureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface InfrastructureRepository extends JpaRepository<InfrastructureEntity,Long> {
    @Query(value = "SELECT i.id,i.name,i.adresse, i.id_schedule,i.id_type FROM databaseschema.event e JOIN databaseschema.infrastructure i on i.id = e.id_infrastructure WHERE (:starting_hour>=starting_acceptation_beginning_hour AND :starting_hour<= starting_acceptation_ending_hour) AND (:ending_hour>=stopping_acceptation_beginning_hour AND :ending_hour<=stopping_acceptation_ending_hour) AND i.id_type =:id_type",nativeQuery = true)
    List<InfrastructureEntity> getMatchingInfrastructure(@Param("starting_hour") Timestamp starting_hour, @Param("ending_hour") Timestamp ending_hour, @Param("id_type") Long id_type);
}
