package com.developper.projetindus.service;

import com.developper.projetindus.dto.EventRequestDTO;
import com.developper.projetindus.model.Event;
import org.springframework.stereotype.Service;

@Service
public interface EventService {
    Event searchEvent(EventRequestDTO eventRequestDTO);
    Event getEvent(long id);
}
