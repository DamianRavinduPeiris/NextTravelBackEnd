package com.damian.uas.service.impl;
import com.damian.uas.config.JWTService;
import com.damian.uas.dto.UserDTO;
import com.damian.uas.entity.User;
import com.damian.uas.repo.UserRepo;
import com.damian.uas.response.Response;
import com.damian.uas.service.custom.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private Response response;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUserName(username);
        return user.isPresent() ? user.get() : user.orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    @Override
    public ResponseEntity<Response> add(UserDTO userDTO) {
        if (search(userDTO.getUserId()).getBody().getData() == null) {
            userDTO.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));

            userRepo.save(mapper.map(userDTO, User.class));
            return createAndSendResponse(HttpStatus.CREATED.value(), "User Successfully saved and JWT successfully generated!", jwtService.generateToken(mapper.map(userDTO, User.class)));

        }

        return createAndSendResponse(HttpStatus.CONFLICT.value(), "User already exists!", null);
    }

    @Override
    public ResponseEntity<Response> update(UserDTO userDTO) {
        if (search(userDTO.getUserId()).getBody().getData() == null) {
            return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "User not found!", null);

        }
        userRepo.save(mapper.map(userDTO, User.class));
        return createAndSendResponse(HttpStatus.OK.value(), "User successfully updated!", null);


    }

    @Override
    public ResponseEntity<Response> delete(String s) {
        if (search(s).getBody().getData() == null) {
            return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "User not found!", null);

        }
        userRepo.deleteById(s);
        return createAndSendResponse(HttpStatus.OK.value(), "User successfully deleted!", null);

    }

    @Override
    public ResponseEntity<Response> search(String userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isPresent()) {
            return createAndSendResponse(HttpStatus.OK.value(), "User successfully retrieved!", mapper.map(user.get(), UserDTO.class));

        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "User not found!", null);


    }

    @Override
    public ResponseEntity<Response> getAll(UserDTO userDTO) {
        List<User> users = userRepo.findAll();
        if (users.isEmpty()) {
            return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "Users not found!", null);

        }
        List<UserDTO> usersList = new ArrayList<>();
        users.forEach((user) -> {
            usersList.add(mapper.map(user, UserDTO.class));

        });
        return createAndSendResponse(HttpStatus.OK.value(), "Users successfully retrieved!", usersList);

    }

    @Override
    public ResponseEntity<Response> createAndSendResponse(int statusCode, String message, Object data) {
        response.setMessage(message);
        response.setData(data);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(statusCode));
    }

    @Override
    public String handleUploads(MultipartFile imageFile) {
        // Getting the file name.
        String fileName = imageFile.getOriginalFilename();

        // Specify the destination directory.In this case it is downloads.
        String destinationDirectory = System.getProperty("user.home") + "/Downloads";
        // Create the directory if it doesn't exist.
        File directory = new File(destinationDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Create the file path.
        String filePath = destinationDirectory + "/" + fileName;

        // Save the image file.
        try {
            imageFile.transferTo(Paths.get(filePath));
            return filePath;
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while saving the image :" + e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseEntity<Response> getUserByUserName(String username,String password) {
        Optional<User> user = userRepo.findByUserName(username);
        if(user.isPresent()){
            UserDTO userDTO = mapper.map(user.get(), UserDTO.class);
            userDTO.setAuthenticated(passwordValidator(password,user.get().getUserPassword()));
            return createAndSendResponse(HttpStatus.OK.value(),"User successfully retrieved!",userDTO);

        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(),"User not found!",null);
    }

    @Override
    public Boolean passwordValidator(String password,String storedHashedPassword) {
        return passwordEncoder.matches(password, storedHashedPassword);

    }
}
