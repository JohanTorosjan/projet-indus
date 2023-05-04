package com.developper.projetindus.model;

import com.developper.projetindus.entity.InfrastructureEntity;
import com.developper.projetindus.entity.UserEntity;

import java.util.List;

public class Event {
    public InfrastructureEntity infrastructure;

    public List<UserEntity> participants;

    public float percentage_of_matching;

    public boolean is_a_new_event;


}
