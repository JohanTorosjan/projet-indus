package com.developper.projetindus.repository;
import com.developper.projetindus.entity.UserEntity;
import java.util.Optional;

import  org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmailAndPassword(String email,String password);

    @Modifying
    @Query(value = "INSERT INTO databaseschema.rating_user (id_user,id_category,note) VALUES (:id_user,:id_category,50)",nativeQuery = true)
    int createAssociation(long id_user,long id_category);

    @Query(value = "SELECT * from databaseschema.user u where u.id IN (SELECT ue.id_user from databaseschema.event_users ue where ue.id_event =id_event );",nativeQuery = true)
    List<UserEntity> getUsersFromEvent(@Param("id_event")long id_event);

    @Modifying
    @Query(value = "UPDATE databaseschema.user set has_active_session = true where id = :id_user",nativeQuery = true)
    void activateSession(@Param("id_user") long id_user);

    @Query(value = "SELECT * FROM databaseschema.user where firebase_id=:firebase_id ;",nativeQuery = true)
    UserEntity findByFirebase_Id(@Param("firebase_id") String firebase_id);

    @Query(value = "select u.id,u.answered_questions,u.confirmed_account,u.dob,u.doc,u.email,u.has_active_session,u.instagram,u.name,u.password,u.surname,u.firebase_id from databaseschema.user u inner join databaseschema.friends f on (u.id=f.id_user2 AND f.id_user1 = :id) OR (u.id = f.id_user1 and f.id_user2 = :id) where f.accepted =true AND u.id <>:id",nativeQuery = true)
    List<UserEntity> getFriends(@Param("id") long id);
}


