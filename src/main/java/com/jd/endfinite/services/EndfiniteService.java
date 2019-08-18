package com.jd.endfinite.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    	
    	if(u.isPresent()) {
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
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    
}
