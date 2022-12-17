package com.photgraphy.studio.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.photgraphy.studio.model.Users;
import com.photgraphy.studio.repository.UsersRepository;
import com.photgraphy.studio.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    UsersRepository usersRepository;

    @PostMapping("/create")
    public String createCategory(@RequestBody Users users) {
        usersService.saveUser(users);
        return "success created category";
    }

//    @PostMapping("/create")
//    public ResponseEntity<?> createUser(@RequestBody Map<String, String> request) throws FirebaseAuthException {
//        String idToken = request.get("idToken");
//        FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(idToken);
//        String uid = token.getUid();
//        UserRecord userRecord = FirebaseAuth.getInstance().getUser(uid);
//
//        Users user = new Users();
//        user.setEmail(userRecord.getEmail());
//        user.setDisplayName(userRecord.getDisplayName());
//        user.setPhotoUrl(userRecord.getPhotoUrl());
//        user.setProvider(userRecord.getTenantId());
//
//        usersRepository.save(user);
//        return ResponseEntity.ok().build();
//    }
}
