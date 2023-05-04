package com.developper.projetindus.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

public class EventRequestDTO {
    public int[] users_id;

    public int[] usages_questions_ids;
    public int[] usages_questions_answers_ids;

    public EventRequestDTO(int[] users_id, int[] usages_questions_ids, int[] usages_questions_answers_ids) {
        this.users_id = users_id;
        this.usages_questions_ids = usages_questions_ids;
        this.usages_questions_answers_ids = usages_questions_answers_ids;
    }

    public int[] getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int[] users_id) {
        this.users_id = users_id;
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

    @Override
    public String toString() {
        return "EventRequestDTO{" +
                "users_id=" + Arrays.toString(users_id) +
                ", usages_questions_ids=" + Arrays.toString(usages_questions_ids) +
                ", usages_questions_answers_ids=" + Arrays.toString(usages_questions_answers_ids) +
                '}';
    }
}
