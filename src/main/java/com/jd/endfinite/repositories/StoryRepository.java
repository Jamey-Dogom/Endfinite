package com.jd.endfinite.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jd.endfinite.models.Story;

@Repository
public interface StoryRepository extends CrudRepository<Story, Long>{

}
