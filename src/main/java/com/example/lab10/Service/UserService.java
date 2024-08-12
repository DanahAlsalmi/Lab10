package com.example.lab10.Service;

import com.example.lab10.Model.User;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //Get all Users
    public List<User> getAll() {
        return userRepository.findAll();
    }

    //Add a new User
    public void addUser(User user) {
        userRepository.save(user);
    }

    //Update a User
    public boolean updateUser(Integer id ,User user) {
        User u = userRepository.findById(id).get();
        if(u!=null) {
            u.setName(user.getName());
            u.setAge(user.getAge());
            u.setEmail(user.getEmail());
            u.setPassword(user.getPassword());
            u.setRole(user.getRole());
            userRepository.save(u);
            return true;
        }
        return false;
    }

    //Delete a User
    public boolean deleteUser(Integer id) {
        User u = userRepository.findById(id).get();
        if(u!=null) {
            userRepository.delete(u);
            return true;
        }
        return false;
    }
}
