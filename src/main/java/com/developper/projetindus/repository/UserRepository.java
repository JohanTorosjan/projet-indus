package com.developper.projetindus.repository;
import com.developper.projetindus.entity.UserEntity;
import  org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

}


