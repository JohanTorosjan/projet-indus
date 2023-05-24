package com.developper.projetindus.repository;

import com.developper.projetindus.entity.EventEntity;
import com.developper.projetindus.entity.InfrastructureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity,Long> {
    @Modifying
    @Query(value = "INSERT INTO databaseschema.event_users(id_user,id_event,percentage_of_matching,is_a_new_event) values (:id_user,:id_event,:percentage_of_matching,:is_a_new_event)",nativeQuery = true)
    int insertUserIntoEvent(@Param("id_user") long id_user,@Param("id_event") long id_event,@Param("percentage_of_matching") int percentage_of_matching,boolean is_a_new_event);

    @Query(value = "SELECT * from databaseschema.event where id_infrastructure = :id_infrastructure",nativeQuery = true)
    EventEntity loadEvent(@Param("id_infrastructure") long id_infrastructure);

    @Query(value = "SELECT * from databaseschema.event e where e.id IN (SELECT id_event from databaseschema.event_users where id_user=:id_user );",nativeQuery = true)
    EventEntity getEvent(@Param("id_user")long id_user);

    @Query(value = "SELECT is_a_new_event from databaseschema.event_users where id_event = :id_event",nativeQuery = true)
    boolean getNewEventBool(@Param("id_event") long id_event);

    @Query(value = "SELECT percentage_of_matching from databaseschema.event_users where id_event = :id_event",nativeQuery = true)
    int getPercentageOfMatching(@Param("id_event") long id_event);




}
