package com.developper.projetindus.repository;

import com.developper.projetindus.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity,Long> {


}
