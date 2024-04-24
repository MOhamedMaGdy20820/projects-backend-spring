package com.example.springredditclone.service;

import com.example.springredditclone.exceptions.SubredditNotFoundException;
import com.example.springredditclone.model.User;
import com.example.springredditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository ;

    public User findUserByName(String userName) {
       Optional<User> user =  userRepository.findByUsername(userName) ;
       log.info("\n\n\n ------------- "+user.toString()+" --------------------\n\n\n");
       return user.orElseThrow(() -> new SubredditNotFoundException("Not found user with userName -> " + userName)) ;
    }
}
