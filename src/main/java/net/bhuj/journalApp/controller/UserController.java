package net.bhuj.journalApp.controller;

import net.bhuj.journalApp.entity.JournalEntry;
import net.bhuj.journalApp.entity.User;
import net.bhuj.journalApp.service.JournalEntryService;
import net.bhuj.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName) {
        return Optional.of(userService.getByUserName(userName))
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping
    public boolean createUser(@RequestBody User user) {
        userService.saveEntry(user);
        return true;
    }

    @PutMapping("{userName}")
    public User updateByUserName(@PathVariable String userName, @RequestBody User user) {
        return userService.updateByUserName(userName, user);
    }

    @DeleteMapping("{userName}")
    public boolean deleteById(@PathVariable String userName) {
        return userService.deleteUser(userName);
    }

    @DeleteMapping
    public boolean deleteAll() {
        return userService.deleteAll();
    }
}
