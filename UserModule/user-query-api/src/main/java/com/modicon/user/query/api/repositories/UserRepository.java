package com.modicon.user.query.api.repositories;

import com.modicon.user.core.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{'$or' :  [{'firstName': {$regex: ?0, $options: 'i'}}, {'lastName': {$regex: ?0, $options: 'i'}}, {'email': {$regex: ?0, $options: 'i'}}, {'account.username': {$regex: ?0, $options: 'i'}}]}")
    List<User> findUsersByFilterRegex(String filter);
}
