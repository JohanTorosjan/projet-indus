package com.developper.projetindus.service;

import com.developper.projetindus.dto.EventRequestDTO;
import com.developper.projetindus.entity.CategoryEntity;
import com.developper.projetindus.entity.UserEntity;
import com.developper.projetindus.model.Event;
import com.developper.projetindus.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService{

    private final UserRepository userRepository;


    @Autowired
    public EventServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Main algo
    @Override
    public Event searchEvent(EventRequestDTO eventRequestDTO) {
        List<CategoryEntity> user_categories = new ArrayList<>();


       //for(int i =0;i<eventRequestDTO.getUsages_questions_answers().length;i++){

        //}



        //
        //
        //
        //
        //
        //  a la fin : integrer dans event + hasactive session devient true pendant 24h

        return null;
    }
}
