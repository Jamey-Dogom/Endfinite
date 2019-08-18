package com.jd.endfinite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.endfinite.repositories.PathRepository;
import com.jd.endfinite.repositories.StageRepository;
import com.jd.endfinite.repositories.StoryRepository;
import com.jd.endfinite.repositories.UserRepository;

@Service
public class EndfiniteService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private StoryRepository storyRepo;
	@Autowired
	private StageRepository stageRepo;
	@Autowired
	private PathRepository pathRepo;
	

}
