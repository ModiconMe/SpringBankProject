package com.modicon.user.query.api.controllers;

import com.modicon.user.query.api.abstractions.AbstractQueryController;
import com.modicon.user.query.api.dto.UserLookupResponse;
import com.modicon.user.query.api.queries.FindAllUsersQuery;
import com.modicon.user.query.api.queries.FindUserByIdQuery;
import com.modicon.user.query.api.queries.SearchUsersQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public interface UserQueryController {

    String BASE_URL_V1 = "/api/v1/userLookup";

    interface Fetch {
        @GetMapping
        ResponseEntity<UserLookupResponse> getAllUsers();

        @GetMapping("/byId/{id}")
        ResponseEntity<UserLookupResponse> getUserById(@PathVariable String id);

        @GetMapping("/byFilter/{regex}")
        ResponseEntity<UserLookupResponse> searchUsersByFilter(@PathVariable String regex);
    }

    @RestController
    @RequestMapping(BASE_URL_V1)
    class Base extends AbstractQueryController implements Fetch {

        public Base(QueryGateway queryGateway) {
            super(queryGateway);
        }

        @Override
        public ResponseEntity<UserLookupResponse> getAllUsers() {
            UserLookupResponse response = queryGateway.query(new FindAllUsersQuery(),
                    ResponseTypes.instanceOf(UserLookupResponse.class)).join();

            if (response == null || response.isUsersNullOrEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @Override
        public ResponseEntity<UserLookupResponse> getUserById(String id) {
            UserLookupResponse response = queryGateway.query(new FindUserByIdQuery(id),
                    ResponseTypes.instanceOf(UserLookupResponse.class)).join();

            if (response == null || response.isUsersNullOrEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @Override
        public ResponseEntity<UserLookupResponse> searchUsersByFilter(String regex) {
            UserLookupResponse response = queryGateway.query(new SearchUsersQuery(regex),
                    ResponseTypes.instanceOf(UserLookupResponse.class)).join();

            if (response == null || response.isUsersNullOrEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
