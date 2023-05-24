package com.developper.projetindus.service;

import com.developper.projetindus.dto.RatingDTO;
import com.developper.projetindus.dto.SuccesMessageDTO;
import com.developper.projetindus.entity.QuestionsEntity;
import com.developper.projetindus.model.QuestionCategory;
import com.developper.projetindus.repository.QuestionsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuestionsServiceImpl implements QuestionsService {

    private final QuestionsRepository questionsRepository;


    @Autowired
    public QuestionsServiceImpl(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }




    @Override
    public QuestionsEntity create(QuestionsEntity question) {
        return questionsRepository.save(question);
    }

    @Override
    public List<QuestionsEntity> getAll() {
       return questionsRepository.findAll();
    }

    @Override
    public List<QuestionsEntity> getStarters() {
        System.out.println(questionsRepository.getStarters().get(0).toString());
        return questionsRepository.getStarters();
    }



    // Doit retourner ceux auxquels il n'a pas encore répondu uniquement
    @Override
    public List<QuestionsEntity> getBunchOfQuestions(String idUser) {
       return questionsRepository.getBunchOfQuestions(Integer.parseInt(idUser));
    }

    @Transactional
    @Override
    public SuccesMessageDTO rateUser(String iduser, RatingDTO ratingDTO){
        System.out.println(ratingDTO.toString());
        int coeff = -1;
        if(ratingDTO.getChoice()){
            coeff = 1;
        }
        int id_question=ratingDTO.getIdQuestion();
        List<Object[]> weightsObjects = questionsRepository.getWeight(id_question);
        System.out.println(weightsObjects.get(0).toString());
        List<QuestionCategory> categoriesRating = new ArrayList<>();
        for (Object[] result : weightsObjects) {
            QuestionCategory categoryRating = new QuestionCategory();
            categoryRating.setId_category((Integer)result[0]);
            categoryRating.setName((String) result[1]);
            categoryRating.setWeight((Integer) result[2]);
            categoriesRating.add(categoryRating);
        }
        for(QuestionCategory rates :categoriesRating){
            int user_rate = questionsRepository.getNote(Long.parseLong(iduser),(long)rates.getId_category());
            int user_new_rate = user_rate + ( rates.weight * coeff );
            System.out.println(Long.parseLong(iduser)+" "+(long)rates.getId_category()+" "+user_new_rate);
            questionsRepository.updateNote(Long.parseLong(iduser),(long)rates.getId_category(),user_new_rate);
        }
        questionsRepository.userAnswerSingleQuestion(Integer.parseInt(iduser),id_question);
        questionsRepository.incrementAnsweredQuestions(Long.parseLong(iduser));
        return new SuccesMessageDTO("success");

    }
}
