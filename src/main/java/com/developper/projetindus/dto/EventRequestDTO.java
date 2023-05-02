package com.developper.projetindus.dto;

import java.util.Arrays;
import java.util.Date;

public class EventRequestDTO {
    public String[] users_id;

    //TODO ? JSON FORMAT
    public Date starting_hour;
    public int type_infrastructure_id;
    public int[] usages_questions_ids;
    public int[] usages_questions_answers_ids;

    public EventRequestDTO(String[] users_id, Date starting_hour, int type_infrastructure_id, int[] usages_questions_ids, int[] usages_questions_answers_ids) {
        this.users_id = users_id;
        this.starting_hour = starting_hour;
        this.type_infrastructure_id = type_infrastructure_id;
        this.usages_questions_ids = usages_questions_ids;
        this.usages_questions_answers_ids = usages_questions_answers_ids;
    }

    public String[] getUsers_id() {
        return users_id;
    }

    public void setUsers_id(String[] users_id) {
        this.users_id = users_id;
    }

    public Date getStarting_hour() {
        return starting_hour;
    }

    public void setStarting_hour(Date starting_hour) {
        this.starting_hour = starting_hour;
    }

    public int getType_infrastructure_id() {
        return type_infrastructure_id;
    }

    public void setType_infrastructure_id(int type_infrastructure_id) {
        this.type_infrastructure_id = type_infrastructure_id;
    }

    public int[] getUsages_questions_ids() {
        return usages_questions_ids;
    }

    public void setUsages_questions_ids(int[] usages_questions_ids) {
        this.usages_questions_ids = usages_questions_ids;
    }

    public int[] getUsages_questions_answers_ids() {
        return usages_questions_answers_ids;
    }

    public void setUsages_questions_answers_ids(int[] usages_questions_answers_ids) {
        this.usages_questions_answers_ids = usages_questions_answers_ids;
    }
}
