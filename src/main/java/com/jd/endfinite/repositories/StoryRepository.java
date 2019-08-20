package com.jd.endfinite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jd.endfinite.models.Story;
import com.jd.endfinite.models.User;

@Repository
public interface StoryRepository extends CrudRepository<Story, Long>{

	@Query("SELECT s FROM Story s WHERE author = ?1 Order by s.createdAt ASC")
	List<Story> findAllByUser(User user);
	
	@Query("SELECT s FROM Story s WHERE publish = true")
	List<Story> findPublishedStories();

	@Query("SELECT s FROM Story s WHERE collab = true")
	List<Story> findCollabStory();
}
