package com.developper.projetindus.repository;

import com.developper.projetindus.entity.AnswerUsagesQuestionsEntity;
import com.developper.projetindus.entity.UsagesQuestionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsagesQuestionsRepository extends JpaRepository<UsagesQuestionsEntity,Long> {
    @Query("SELECT a FROM AnswerUsagesQuestionsEntity a JOIN a.usagesQuestions q WHERE q.id = :questionId")
    List<AnswerUsagesQuestionsEntity> getAnswers(@Param("questionId") long questionId);

    @Query(value = "SELECT * from databaseschema.usages_questions where id NOT IN(1,2,3) ORDER BY random() limit 5",nativeQuery = true)
    List<UsagesQuestionsEntity> getBunchOfUsagesQuestions();
}
