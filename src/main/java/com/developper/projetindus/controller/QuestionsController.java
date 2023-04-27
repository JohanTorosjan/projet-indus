package com.developper.projetindus.controller;


import com.developper.projetindus.dto.RatingDTO;
import com.developper.projetindus.dto.SuccesMessageDTO;
import com.developper.projetindus.entity.QuestionsEntity;
import com.developper.projetindus.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionsController {

    private final QuestionsService questionsService;

    @Autowired
    public QuestionsController(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }


    @PostMapping
    public QuestionsEntity create(@RequestBody QuestionsEntity question){
        return questionsService.create(question);
    }

    @GetMapping
    public List<QuestionsEntity> getAll(){
        return questionsService.getAll();
    }

    @GetMapping("/getStarters")
    public List<QuestionsEntity> getStarters(){
        return questionsService.getStarters();
    }

    @GetMapping("/getBunchOfQuestions/{id_user}")
    public List<QuestionsEntity> getBunchOfQuestions(@PathVariable String id_user){
        return questionsService.getBunchOfQuestions(id_user);
    }
    @PutMapping("/rating/{id_user}")
    public SuccesMessageDTO rateUser(@PathVariable String id_user, @RequestBody RatingDTO ratingDTO){
        return questionsService.rateUser(id_user,ratingDTO);
    }




}
