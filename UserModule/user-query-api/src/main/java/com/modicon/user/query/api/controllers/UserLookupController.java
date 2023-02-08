package com.modicon.user.query.api.controllers;

import com.modicon.user.query.api.dto.UserLookupResponse;
import com.modicon.user.query.api.queries.FindAllUsersQuery;
import com.modicon.user.query.api.queries.FindUserByIdQuery;
import com.modicon.user.query.api.queries.SearchUsersQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/userLookup")
public class UserLookupController {

    private final QueryGateway queryGateway;

    @GetMapping
    public ResponseEntity<UserLookupResponse> getAllUsers() {
        UserLookupResponse response = queryGateway.query(new FindAllUsersQuery(),
                ResponseTypes.instanceOf(UserLookupResponse.class)).join();

        if (response == null || response.isUsersNullOrEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<UserLookupResponse> getUserById(@PathVariable String id) {
        UserLookupResponse response = queryGateway.query(new FindUserByIdQuery(id),
                ResponseTypes.instanceOf(UserLookupResponse.class)).join();

        if (response == null || response.isUsersNullOrEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/byFilter/{regex}")
    public ResponseEntity<UserLookupResponse> searchUsersByFilter(@PathVariable String regex) {
        UserLookupResponse response = queryGateway.query(new SearchUsersQuery(regex),
                ResponseTypes.instanceOf(UserLookupResponse.class)).join();

        if (response == null || response.isUsersNullOrEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
