package com.jd.endfinite.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jd.endfinite.models.Path;

@Repository
public interface PathRepository extends CrudRepository<Path, Long>{

}
