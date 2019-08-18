package com.jd.endfinite.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jd.endfinite.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}
