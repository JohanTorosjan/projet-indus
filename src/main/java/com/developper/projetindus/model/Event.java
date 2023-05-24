package com.developper.projetindus.model;

import com.developper.projetindus.model.Infrastructure;
import com.developper.projetindus.model.Participants;

import java.time.LocalDateTime;
import java.util.List;

public class Event {
    public Infrastructure infrastructure;
    public List<Participants> participants;
    public LocalDateTime starting_hour;
    public float percentage_of_matching;
    public boolean is_a_new_event;
    public Event(Infrastructure infrastructure, List<Participants> participants, LocalDateTime starting_hour, float percentage_of_matching, boolean is_a_new_event) {
        this.infrastructure = infrastructure;
        this.participants = participants;
        this.starting_hour = starting_hour;
        this.percentage_of_matching = percentage_of_matching;
        this.is_a_new_event = is_a_new_event;
    }

    public Infrastructure getInfrastructure() {
        return infrastructure;
    }

    public void setInfrastructure(Infrastructure infrastructure) {
        this.infrastructure = infrastructure;
    }

    public List<Participants> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participants> participants) {
        this.participants = participants;
    }

    public LocalDateTime getStarting_hour() {
        return starting_hour;
    }

    public void setStarting_hour(LocalDateTime starting_hour) {
        this.starting_hour = starting_hour;
    }

    public float getPercentage_of_matching() {
        return percentage_of_matching;
    }

    public void setPercentage_of_matching(float percentage_of_matching) {
        this.percentage_of_matching = percentage_of_matching;
    }

    public boolean isIs_a_new_event() {
        return is_a_new_event;
    }

    public void setIs_a_new_event(boolean is_a_new_event) {
        this.is_a_new_event = is_a_new_event;
    }

    public Event() {
    }

    @Override
    public String toString() {
        return "Event{" +
                "infrastructure=" + infrastructure +
                ", participants=" + participants +
                ", starting_hour=" + starting_hour +
                ", percentage_of_matching=" + percentage_of_matching +
                ", is_a_new_event=" + is_a_new_event +
                '}';
    }
}
