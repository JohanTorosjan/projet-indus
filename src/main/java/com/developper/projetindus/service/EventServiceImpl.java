package com.developper.projetindus.service;

import com.developper.projetindus.dto.EventRequestDTO;
import com.developper.projetindus.entity.CategoryEntity;
import com.developper.projetindus.entity.EventEntity;
import com.developper.projetindus.entity.InfrastructureEntity;
import com.developper.projetindus.entity.UserEntity;
import com.developper.projetindus.model.Event;
import com.developper.projetindus.model.EventCreator;
import com.developper.projetindus.model.Infrastructure;
import com.developper.projetindus.model.Participants;
import com.developper.projetindus.repository.CategoryRepository;
import com.developper.projetindus.repository.EventRepository;
import com.developper.projetindus.repository.InfrastructureRepository;
import com.developper.projetindus.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.interfaces.DSAKey;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;

    private final InfrastructureRepository infrastructureRepository;

    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(UserRepository userRepository, InfrastructureRepository infrastructureRepository, CategoryRepository categoryRepository,EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.infrastructureRepository = infrastructureRepository;
        this.categoryRepository = categoryRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public Event getEvent(long id) {
        EventEntity eventEntity = eventRepository.getEvent(id);
        if(eventEntity == null){return new Event();}
        List<UserEntity> users = userRepository.getUsersFromEvent(eventEntity.getId());
        List<Participants> participants = new ArrayList<>();
        for(UserEntity user:users){
            ZoneId defaultZoneId = ZoneId.systemDefault();
            // Conversion de Date en LocalDate
            LocalDate localDate = user.getDob().toInstant().atZone(defaultZoneId).toLocalDate();
            Participants participant = new Participants(user.getId(),user.getName(),user.getConfirmed_account(), Period.between(localDate,LocalDate.now()).getYears());
            participants.add(participant);
        }

        InfrastructureEntity infrastructureEntity = eventEntity.getInfrastructure();
        Event event = new Event();
       // event.setIs_a_new_event(eventRepository.getNewEventBool(eventEntity.getId()));
        event.setPercentage_of_matching(eventRepository.getPercentageOfMatching(eventEntity.getId(),id));
        event.setParticipants(participants);
        event.setStarting_hour(eventEntity.getStarting_acceptation_beginning_hour());
        Infrastructure infrastructure = new Infrastructure(infrastructureEntity.getAdresse(),infrastructureEntity.getName(),infrastructureEntity.getType().getName());
        event.setInfrastructure(infrastructure);
        return event;
    }

    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    //Main algo
    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    @Transactional
    @Override
    public Event searchEvent(EventRequestDTO eventRequestDTO) {
        List<InfrastructureEntity> existingInfrastructureEvent = getMatchingInfrastructure(eventRequestDTO.getUsages_questions_answers_ids()[0], eventRequestDTO.getUsages_questions_answers_ids()[2], eventRequestDTO.getUsages_questions_answers_ids()[1]);
        System.out.println("Nombre d'infrastructures proposant un événement matchant :" + existingInfrastructureEvent.size());
      //  System.out.println(existingInfrastructureEvent.get(0).getName());
        if (existingInfrastructureEvent.isEmpty()) {
            return createEvent(eventRequestDTO);
        }
        List<CategoryEntity> user_categories = getUserMainCategories(eventRequestDTO);
        List<Float> percentagesOfMatching = getPercentagesOfMatching(existingInfrastructureEvent,user_categories,true);
        System.out.println(percentagesOfMatching);
        System.out.println(Collections.max(percentagesOfMatching));
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

    }

    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    //PRIVATES FUNCTIONS NEEDED FOR MAIN ALGO
    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
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

    private Event createEvent(EventRequestDTO eventRequestDTO) {
        LocalDateTime[] StartingAndEndingHour = getStartingAndEndingHour(eventRequestDTO.getUsages_questions_answers_ids()[0],eventRequestDTO.getUsages_questions_answers_ids()[2]);
        InfrastructureEntity infrastructure = getNewInfrastructure(eventRequestDTO);
        List<InfrastructureEntity> infrastructureEntityList = new ArrayList<>();
        infrastructureEntityList.add(infrastructure);
        List<Float> percentages = getPercentagesOfMatching(infrastructureEntityList,getUserMainCategories(eventRequestDTO),true);
        EventEntity newEvent = createNewEventEntity(StartingAndEndingHour,infrastructure);
        for(int i =0;i<eventRequestDTO.getUsers_id().length;i++){
            eventRepository.insertUserIntoEvent(eventRequestDTO.getUsers_id()[i],newEvent.getId(),percentages.get(0).intValue(),true);
            userRepository.activateSession(eventRequestDTO.getUsers_id()[i]);
        }
        Event event = new Event();
        event.setPercentage_of_matching(percentages.get(0));
        event.setIs_a_new_event(true);
        event.setStarting_hour(newEvent.getStarting_acceptation_beginning_hour());
        Infrastructure infrastructure1 = new Infrastructure(infrastructure.getAdresse(),infrastructure.getName(),infrastructure.getType().getName());
        event.setInfrastructure(infrastructure1);

        return event;
    }
    private EventEntity createNewEventEntity(LocalDateTime[] startingAndEndingHour, InfrastructureEntity infrastructure) {
        EventEntity newEvent = new EventEntity();
        newEvent.setStarting_acceptation_beginning_hour(startingAndEndingHour[0]);
        newEvent.setStarting_acceptation_ending_hour(startingAndEndingHour[0].plusHours(2));
        newEvent.setStopping_acceptation_beginning_hour(startingAndEndingHour[1].minusHours(1));
        newEvent.setStopping_acceptation_ending_hour(startingAndEndingHour[1].plusHours(2));
        newEvent.setInfrastructure(infrastructure);
        System.out.println(newEvent.toString());
        return eventRepository.save(newEvent);
    }

    private InfrastructureEntity getNewInfrastructure(EventRequestDTO eventRequestDTO) {
        List<CategoryEntity> user_categories = getUserMainCategories(eventRequestDTO);
        List<InfrastructureEntity> infrastructureEntities = infrastructureRepository.getFreeInfrastructuresIds((long)eventRequestDTO.getUsages_questions_answers_ids()[1]);
        if(infrastructureEntities.isEmpty()){
            return infrastructureRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("error"));
        }
        List<Float> percentagesOfMatching = getPercentagesOfMatching(infrastructureEntities,user_categories,true);
        List<Integer> bestPercentages = getMaxIndexes(percentagesOfMatching);
        return(infrastructureEntities.get(bestPercentages.get(0)));
    }

    private List<InfrastructureEntity> getMatchingInfrastructure(int starting_hour_id, int ending_answer_id, int type_infrastructure_id) {
        LocalDateTime[] dateTimes = getStartingAndEndingHour(starting_hour_id,ending_answer_id);
        Timestamp starting_hour_date_timestamp = Timestamp.valueOf(dateTimes[0]);
        Timestamp ending_hour_date_timestamp = Timestamp.valueOf(dateTimes[1]);
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
    private Event associateToEvent(int[] usersId, InfrastructureEntity infrastructureEntity,float percentageOfMatching) {
        if(percentageOfMatching>100){
            percentageOfMatching = 100;
        }
        EventEntity existingEvent = eventRepository.loadEvent(infrastructureEntity.getId());
        for(int i =0;i<usersId.length;i++){
            eventRepository.insertUserIntoEvent(usersId[i],existingEvent.getId(),(int)percentageOfMatching,false);
            userRepository.activateSession(usersId[i]);
        }
        System.out.println("Associating "+usersId+"to"+infrastructureEntity.getName());
        List<UserEntity> users = userRepository.getUsersFromEvent(existingEvent.getId());
        List<Participants> participants = new ArrayList<>();
        for(UserEntity user:users){
            ZoneId defaultZoneId = ZoneId.systemDefault();
            // Conversion de Date en LocalDate
            LocalDate localDate = user.getDob().toInstant().atZone(defaultZoneId).toLocalDate();
            Participants participant = new Participants(user.getId(),user.getName(),user.getConfirmed_account(), Period.between(localDate,LocalDate.now()).getYears());
            participants.add(participant);
        }
        Event event = new Event();
        event.setIs_a_new_event(false);
        event.setParticipants(participants);
        event.setStarting_hour(existingEvent.getStarting_acceptation_beginning_hour());
        Infrastructure infrastructure = new Infrastructure(infrastructureEntity.getAdresse(),infrastructureEntity.getName(),infrastructureEntity.getType().getName());
        event.setInfrastructure(infrastructure);
        event.setPercentage_of_matching(percentageOfMatching);
        return event;

    }
    private LocalDateTime[] getStartingAndEndingHour(int starting_hour_id, int ending_answer_id){
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
        return new LocalDateTime[]{starting_hour, ending_hour};
    }

}
