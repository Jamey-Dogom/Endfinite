package com.jd.endfinite.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jd.endfinite.models.Stage;

@Repository
public interface StageRepository extends CrudRepository<Stage, Long>{

}
