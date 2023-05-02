package com.developper.projetindus.service;

import com.developper.projetindus.entity.AnswerUsagesQuestionsEntity;
import com.developper.projetindus.entity.TypeInfrastructureEntity;
import com.developper.projetindus.entity.UsagesQuestionsEntity;
import com.developper.projetindus.model.UsagesQuestions;
import com.developper.projetindus.repository.TypeInfrastructureRepository;
import com.developper.projetindus.repository.UsagesQuestionsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsagesQuestionsServiceImpl implements UsagesQuestionsService {
    private final UsagesQuestionsRepository usagesQuestionsRepository;
    private final TypeInfrastructureRepository typeInfrastructureRepository;

    @Autowired
    public UsagesQuestionsServiceImpl(UsagesQuestionsRepository usagesQuestionsRepository, TypeInfrastructureRepository typeInfrastructureRepository) {
        this.usagesQuestionsRepository = usagesQuestionsRepository;
        this.typeInfrastructureRepository = typeInfrastructureRepository;
    }

    @Override
    public List<UsagesQuestions> getUsagesQuestions() {
        List<UsagesQuestions> usagesQuestionsList = new ArrayList<>();
        ///
        UsagesQuestionsEntity startingHourQuestionEntity = usagesQuestionsRepository.findById(Long.valueOf(1)).orElseThrow(() -> new EntityNotFoundException("no usages questions matching"));
        UsagesQuestions startingHourQuestion = new UsagesQuestions();
        startingHourQuestion.setQuestion(startingHourQuestionEntity);
        List<AnswerUsagesQuestionsEntity> startingHoursAnswers = new ArrayList<>();
        long j=0;
        for(int i=0;i<5;i++){
            AnswerUsagesQuestionsEntity startingHour = new AnswerUsagesQuestionsEntity();
            LocalDateTime currentDate = LocalDateTime.now();
            LocalDateTime date = currentDate.plusHours(j);
            startingHour.setLabel(date.toString());
        //    startingHour.setUsagesQuestions(startingHourQuestionEntity);
            startingHoursAnswers.add(startingHour);
            j=j+2;
        }
        startingHourQuestion.setAnswers(startingHoursAnswers);
        // Ajout de la question debut heure
        usagesQuestionsList.add(startingHourQuestion);
        //
        UsagesQuestionsEntity typeQuestionEntity = usagesQuestionsRepository.findById(Long.valueOf(2)).orElseThrow(() -> new EntityNotFoundException("no usages questions matching"));
        UsagesQuestions typeQuestion = new UsagesQuestions();
        typeQuestion.setQuestion(typeQuestionEntity);
        List<TypeInfrastructureEntity> typeAnswersEntity = typeInfrastructureRepository.findAll();
        List<AnswerUsagesQuestionsEntity> typeAnswers = new ArrayList<>();
        for(TypeInfrastructureEntity typeEntity : typeAnswersEntity){
            AnswerUsagesQuestionsEntity typeAnswer = new AnswerUsagesQuestionsEntity();
            typeAnswer.setLabel(typeEntity.getName());
            typeAnswer.setId(typeEntity.getId());
            //typeAnswer.setUsagesQuestions(typeQuestionEntity);
            typeAnswers.add(typeAnswer);
        }
        typeQuestion.setAnswers(typeAnswers);
        // Ajout de la question type
        usagesQuestionsList.add(typeQuestion);
        //

        UsagesQuestionsEntity endingHourQuestionEntity = usagesQuestionsRepository.findById(Long.valueOf(3)).orElseThrow(() -> new EntityNotFoundException("no usages questions matching"));
        List<AnswerUsagesQuestionsEntity> endingHourAnswerEntity = usagesQuestionsRepository.getAnswers(Long.valueOf(3));
        UsagesQuestions endingHourQuestion = new UsagesQuestions();
        endingHourQuestion.setQuestion(endingHourQuestionEntity);
        endingHourQuestion.setAnswers(endingHourAnswerEntity);

        // Ajout de la question fin heure
        usagesQuestionsList.add(endingHourQuestion);
        //

        // Ajout de 5 questions random
        List<UsagesQuestionsEntity> bunchOfUsagesQuestionsEntity = usagesQuestionsRepository.getBunchOfUsagesQuestions();

        for(UsagesQuestionsEntity bunchOfUsagesQuestions: bunchOfUsagesQuestionsEntity){
            List<AnswerUsagesQuestionsEntity> bunchOfUsagesQuestionsAnswers = usagesQuestionsRepository.getAnswers(Long.valueOf(bunchOfUsagesQuestions.getId()));
            UsagesQuestions singleRandomUsageQuestion = new UsagesQuestions();
            singleRandomUsageQuestion.setQuestion(bunchOfUsagesQuestions);
            singleRandomUsageQuestion.setAnswers(bunchOfUsagesQuestionsAnswers);
            usagesQuestionsList.add(singleRandomUsageQuestion);
        }
        return usagesQuestionsList;
    }
}
