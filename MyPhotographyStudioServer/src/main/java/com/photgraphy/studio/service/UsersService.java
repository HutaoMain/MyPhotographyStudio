package com.photgraphy.studio.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.photgraphy.studio.model.Users;
import com.photgraphy.studio.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public Users saveUser(Users users)  {
        Users user = new Users();
        user.setEmail(users.getEmail());
        user.setDisplayName(users.getDisplayName());
        user.setPhotoUrl(users.getPhotoUrl());
        // Save user information to MongoDB
        return usersRepository.save(user);
    }
}
