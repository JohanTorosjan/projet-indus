package com.developper.projetindus.controller;

import com.developper.projetindus.model.UsagesQuestions;
import com.developper.projetindus.service.UsagesQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usages_questions")
public class UsagesQuestionsController {

    private UsagesQuestionsService usagesQuestionsService;

    @Autowired

    public UsagesQuestionsController(UsagesQuestionsService usagesQuestionsService) {
        this.usagesQuestionsService = usagesQuestionsService;
    }

    @GetMapping
    public List<UsagesQuestions> getUsagesQuestions(){
        return usagesQuestionsService.getUsagesQuestions();
    }
}
