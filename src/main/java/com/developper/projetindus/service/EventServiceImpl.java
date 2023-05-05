package com.developper.projetindus.service;

import com.developper.projetindus.dto.EventRequestDTO;
import com.developper.projetindus.entity.CategoryEntity;
import com.developper.projetindus.entity.InfrastructureEntity;
import com.developper.projetindus.model.Event;
import com.developper.projetindus.repository.CategoryRepository;
import com.developper.projetindus.repository.InfrastructureRepository;
import com.developper.projetindus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.interfaces.DSAKey;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;

    private final InfrastructureRepository infrastructureRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public EventServiceImpl(UserRepository userRepository, InfrastructureRepository infrastructureRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.infrastructureRepository = infrastructureRepository;
        this.categoryRepository = categoryRepository;
    }

    //Main algo
    @Override
    public Event searchEvent(EventRequestDTO eventRequestDTO) {
        List<InfrastructureEntity> existingInfrastructureEvent = getMatchingInfrastructure(eventRequestDTO.getUsages_questions_answers_ids()[0], eventRequestDTO.getUsages_questions_answers_ids()[2], eventRequestDTO.getUsages_questions_answers_ids()[1]);
        System.out.println("Nombre d'infrastructures proposant un événement matchant :" + existingInfrastructureEvent.size());
        if (existingInfrastructureEvent.isEmpty()) {
            return createEvent(eventRequestDTO);
        }
        List<CategoryEntity> user_categories = getUserMainCategories(eventRequestDTO);
        List<Float> percentagesOfMatching = getPercentagesOfMatching(existingInfrastructureEvent,user_categories,false);
        System.out.println(percentagesOfMatching);
        if(Collections.max(percentagesOfMatching)<50.0){
            return createEvent(eventRequestDTO);
        }
        List<Integer> bestPercentages = getMaxIndexes(percentagesOfMatching);
        System.out.println(bestPercentages);
        if(bestPercentages.size() == 1){
            return associateToEvent(eventRequestDTO.getUsers_id(),existingInfrastructureEvent.get(bestPercentages.get(0)),percentagesOfMatching.get(bestPercentages.get(0)));
        }
        else{
            percentagesOfMatching = getPercentagesOfMatching(existingInfrastructureEvent,user_categories,true);
            bestPercentages = getMaxIndexes(percentagesOfMatching);
            return associateToEvent(eventRequestDTO.getUsers_id(),existingInfrastructureEvent.get(bestPercentages.get(0)),percentagesOfMatching.get(bestPercentages.get(0)));
        }
        //  a la fin :hasactive session devient true pendant 24h
    }

    private List<Float> getPercentagesOfMatching(List<InfrastructureEntity> existingInfrastructureEvent, List<CategoryEntity> user_categories,boolean moreAccuratePercentage) {
        List<Float> percentagesOfMatching = new ArrayList<>();
        for (InfrastructureEntity infrastructureEvent : existingInfrastructureEvent) {
            float percentageOfMatchingTemp;
            List<CategoryEntity> infrastructure_categories = categoryRepository.getInfrastructureMainCategories(infrastructureEvent.getId());
            if(moreAccuratePercentage){
                 percentageOfMatchingTemp = calculatePercentageOfMatching(user_categories, infrastructure_categories) + calculatePrecisePercentageOfMatching(user_categories, infrastructure_categories);
            }
            else{
                percentageOfMatchingTemp = calculatePercentageOfMatching(user_categories, infrastructure_categories);
            }
            percentagesOfMatching.add(percentageOfMatchingTemp);
        }
        System.out.println("PERCENTAGE OF MATCHING : "+percentagesOfMatching +"is THE BOOLEAN : "+moreAccuratePercentage);
        return percentagesOfMatching;
    }
    //PRIVATES FUNCTIONS NEEDED FOR MAIN ALGO
    private List<CategoryEntity> getUserMainCategories(EventRequestDTO eventRequestDTO) {
        List<CategoryEntity> userMainCategories = new ArrayList<>();
        for (int i = 3; i < eventRequestDTO.getUsages_questions_answers_ids().length; i++) {
            List<CategoryEntity> boostedCategories = categoryRepository.getBoostedCategories((long) eventRequestDTO.getUsages_questions_answers_ids()[i]);
            for (CategoryEntity singleBoostedCategory : boostedCategories) {
                userMainCategories.add(singleBoostedCategory);
            }
        }
        // Ajoute les categories boostées
        List<CategoryEntity> userOwnCategories = categoryRepository.getUserMainCategories((long) eventRequestDTO.getUsers_id()[0]);
        int i = 0;
        while (userMainCategories.size() < 10) {
            if (!userMainCategories.contains(userOwnCategories.get(i))) {
                userMainCategories.add(userOwnCategories.get(i));
            }
            i++;
        }
        return userMainCategories;
    }

    //TODO
    private Event createEvent(EventRequestDTO eventRequestDTO) {
        System.out.println("Creating new event");
        return null;
    }

    private List<InfrastructureEntity> getMatchingInfrastructure(int starting_hour_id, int ending_answer_id, int type_infrastructure_id) {
        LocalDateTime starting_hour = LocalDateTime.now();
        starting_hour = starting_hour.withMinute(0).withSecond(0).withNano(0).plusHours(1);
        starting_hour = switch (starting_hour_id) {
            case 2 -> starting_hour.plusHours(2);
            case 4 -> starting_hour.plusHours(4);
            case 6 -> starting_hour.plusHours(6);
            default -> starting_hour;
        };
        LocalDateTime ending_hour = switch (ending_answer_id) {
            case 2 -> starting_hour.plusHours(4);
            case 3 -> starting_hour.plusHours(6);
            case 4 -> starting_hour.plusHours(8);
            default -> starting_hour.plusHours(2);
        };
        Timestamp starting_hour_date_timestamp = Timestamp.valueOf(starting_hour);
        Timestamp ending_hour_date_timestamp = Timestamp.valueOf(ending_hour);
        System.out.println(starting_hour_date_timestamp);
        System.out.println(ending_hour_date_timestamp);
        return infrastructureRepository.getMatchingInfrastructure(starting_hour_date_timestamp, ending_hour_date_timestamp, (long) type_infrastructure_id);
    }
    private float calculatePercentageOfMatching(List<CategoryEntity> userCategories, List<CategoryEntity> infrastructureCategories) {
        float percentage = 0;
        int i = 0;
        for (CategoryEntity userCategory : userCategories) {
            if (infrastructureCategories.contains(userCategory)) {
                percentage += 10;
            }
        }
        return percentage;
    }
    private float calculatePrecisePercentageOfMatching(List<CategoryEntity> userCategories, List<CategoryEntity> infrastructureCategories) {
        float percentage = 0;
        int i = 0;
        for (CategoryEntity userCategory : userCategories) {
            for (int j = 0; j < 10; j++) {
                if (userCategory.equals(infrastructureCategories.get(j))) {
                    if (i == j) {
                        percentage = percentage + 10;
                    } else {
                        percentage = percentage + (10 - Math.abs(i - j));
                    }
                }
            }
            i++;
        }
        return percentage;
    }
    public static List<Integer> getMaxIndexes(List<Float> list) {
        float max = Collections.max(list);
        List<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == max) {
                indexes.add(i);
            }
        }
        return indexes;
    }
    //TODO
    private Event associateToEvent(int[] usersId, InfrastructureEntity infrastructureEntity,float percentageOfMatching) {
        System.out.println("Associating "+usersId+"to"+infrastructureEntity.getName());
        return null;
    }
}
