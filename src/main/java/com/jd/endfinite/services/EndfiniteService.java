package com.jd.endfinite.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.endfinite.models.Path;
import com.jd.endfinite.models.Stage;
import com.jd.endfinite.models.Story;
import com.jd.endfinite.models.User;
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

	// register user and hash their password
	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return userRepo.save(user);
	}

	// find user by email
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	// find user by id
	public User findUserById(Long id) {
		Optional<User> u = userRepo.findById(id);

		if (u.isPresent()) {
			return u.get();
		} else {
			return null;
		}
	}

	// authenticate user
	public boolean authenticateUser(String email, String password) {
		// first find the user by email
		User user = userRepo.findByEmail(email);
		// if we can't find it by email, return false
		if (user == null) {
			return false;
		} else {
			// if the passwords match, return true, else, return false
			if (BCrypt.checkpw(password, user.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
	}

//	public Story initializeStory(String title, String firstStage, String collab, String pathOne, String pathTwo,
//			String pathThree, User user) {
//		// create a new story object
//		Story newStory = new Story();
//		// set the user in session as the author
//		newStory.setAuthor(user);
//		// set title of new story
//		newStory.setTitle(title);
//		// if user selects to not have collaborators set att to null
//		if (collab == null) {
//			collab = "off";
//			newStory.setCollaborators(null);
//		}
//
//		// create 3 new paths and assign them to the stage
//		Path path1 = new Path();
//		path1.setChoice(pathOne);
//
//		Path path2 = new Path();
//		path2.setChoice(pathTwo);
//
//		Path path3 = new Path();
//		path3.setChoice(pathThree);
//
//		// 0 index represents previous stage - 1 represents next stage
//
//		// create a new stage object
//		Stage initStage = new Stage();
//
//		// set description of stage to user info from form
//		initStage.setDescription(firstStage);
//
//		// set the path to the stage
//		List<Path> paths = new ArrayList<>();
//		paths.add(path1);
//		paths.add(path2);
//		paths.add(path3);
//		initStage.setPaths(paths);
//
//		// create a new list of stages to set for Story
//		List<Stage> stages = new ArrayList<>();
//		stages.add(initStage);
//
//		// add stage to Story
//		newStory.setStages(stages);
//
//		// set the story to the user
//		List<Story> stories = user.getStories();
//		stories.add(newStory);
//		user.setStories(stories);
//
//		List<Story> authors = user.getAuthors();
//		authors.add(newStory);
//		user.setAuthors(authors);
//		
//		// return newly created story
//		return newStory;
//
//	}

	public Story findByStoryId(Long id) {
		return storyRepo.findById(id).get();
	}

	public void createPath(Path path) {
		pathRepo.save(path);
		
	}

	public void createStage(Stage stage) {
		stageRepo.save(stage);
	}
	
	public void createStory(Story story) {
		storyRepo.save(story);
	}

	public void saveUser(User user) {
		userRepo.save(user);
		
	}

	public List<Story> findUserStories(User user) {
		return storyRepo.findAllByUser(user);
	}

	public Path findPathById(Long id) {
		return pathRepo.findById(id).get();
	}

	public List<Story> findPublishedStories() {
		return storyRepo.findPublishedStories();
	}
	
	public List<Story> findCollabStory(){
		return storyRepo.findCollabStory();
	}


}
