package net.bhuj.journalApp.service;


import net.bhuj.journalApp.entity.User;
import net.bhuj.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public boolean deleteUser(String userName) {
//        userRepository.deleteById(id);
        return true;
    }

    public boolean deleteAll() {
        userRepository.deleteAll();
        return true;
    }

    public User updateByUserName(String userName, User newEntry) {
        User old = userRepository.findByUserName(userName);
        if (old != null) {
            if(newEntry.getUserName() != null && !newEntry.getUserName().isEmpty()) {
                old.setUserName(newEntry.getUserName());
            }
            if(newEntry.getPassword() != null && !newEntry.getPassword().isEmpty()) {
                old.setPassword(newEntry.getPassword());
            }
        }
        return userRepository.save(old);
    }
}
