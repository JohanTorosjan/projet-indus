package com.developper.projetindus.service;

import com.developper.projetindus.model.UsagesQuestions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsagesQuestionsService {
    public List<UsagesQuestions> getUsagesQuestions();
}
