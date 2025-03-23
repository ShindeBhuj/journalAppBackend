package net.bhuj.journalApp.repository;

import net.bhuj.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);
}
