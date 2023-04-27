package com.developper.projetindus.repository;
import com.developper.projetindus.entity.UserEntity;
import java.util.Optional;
import  org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmailAndPassword(String email,String password);
    //Optional<UserEntity> findByEmail(String email) throws UsernameNotFoundException;

    @Modifying
    @Query(value = "INSERT INTO databaseschema.rating_user (id_user,id_category,note) VALUES (:id_user,:id_category,50)",nativeQuery = true)
    int createAssociation(long id_user,long id_category);

}


