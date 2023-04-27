package com.developper.projetindus.service;

import com.developper.projetindus.dto.RatingDTO;
import com.developper.projetindus.dto.SuccesMessageDTO;
import com.developper.projetindus.entity.QuestionsEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionsService {
    QuestionsEntity create(QuestionsEntity question);
    List<QuestionsEntity> getAll();
    List<QuestionsEntity> getStarters();
    List<QuestionsEntity> getBunchOfQuestions(String idUser);

    SuccesMessageDTO rateUser(String iduser, RatingDTO ratingDTO);

}
