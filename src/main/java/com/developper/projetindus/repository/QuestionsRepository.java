package com.developper.projetindus.repository;

import com.developper.projetindus.entity.QuestionsEntity;
import com.developper.projetindus.model.QuestionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionsRepository extends JpaRepository<QuestionsEntity,Long> {


    @Query(value = "SELECT * from databaseschema.questions limit 10",nativeQuery = true)
    List<QuestionsEntity> getStarters();

    @Modifying
    @Query(value = "INSERT INTO databaseschema.answered_questions(id_user,id_question) VALUES (:id_user,:id_question);",nativeQuery = true)
    void userAnswerSingleQuestion(@Param("id_user") int id_user,@Param("id_question") int id_question);

    @Query(value = "SELECT qc.id_category, c.name, qc.weight " +
            "FROM databaseschema.questions q " +
            "JOIN databaseschema.question_category qc ON q.id = qc.id_question " +
            "JOIN databaseschema.category c ON qc.id_category = c.id " +
            "WHERE q.id = :id", nativeQuery = true)
    List<Object[]> getWeight(@Param("id") int id);



    @Query(value = "select r.note from databaseschema.rating_user r where id_user=:id_user and id_category=:id_category",nativeQuery = true)
    int getNote(@Param("id_user") long id_user,@Param("id_category") long id_category);


    @Modifying
    @Query(value = "UPDATE databaseschema.rating_user set note=:user_new_rate where id_user=:id_user and id_category=:id_category ",nativeQuery = true)
    int updateNote(@Param("id_user") long id_user,@Param("id_category") long id_category,@Param("user_new_rate") int user_new_rate);

    @Query(value = "SELECT q.id,q.label,q.choice0,q.choice1 FROM databaseschema.questions q\n" +
            "WHERE q.id NOT IN (\n" +
            "  SELECT aq.id_question\n" +
            "  FROM databaseschema.answered_questions aq\n" +
            "  WHERE aq.id_user = :id_user\n" +
            ")",nativeQuery = true)
    List<QuestionsEntity> getBunchOfQuestions(@Param("id_user") int id_user);
}