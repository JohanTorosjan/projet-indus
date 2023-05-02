package com.developper.projetindus.controller;

import com.developper.projetindus.dto.EventRequestDTO;
import com.developper.projetindus.model.Event;
import com.developper.projetindus.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public Event searchEvent(@RequestBody EventRequestDTO eventRequestDTO){
        return eventService.searchEvent(eventRequestDTO);
    }



}
