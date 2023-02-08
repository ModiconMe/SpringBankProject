package com.modicon.user.query.api.handlers;

import com.modicon.user.core.models.User;
import com.modicon.user.query.api.dto.UserLookupResponse;
import com.modicon.user.query.api.queries.FindAllUsersQuery;
import com.modicon.user.query.api.queries.FindUserByIdQuery;
import com.modicon.user.query.api.queries.SearchUsersQuery;
import com.modicon.user.query.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserQueryHandlerImpl implements UserQueryHandler {

    private final UserRepository userRepository;

    @QueryHandler
    @Override
    public UserLookupResponse getUserById(FindUserByIdQuery query) {
        List<User> users = new ArrayList<>();
        users.add(Objects.requireNonNull(userRepository.findById(query.getId()).orElse(null)));
        return new UserLookupResponse(users);
    }

    @QueryHandler
    @Override
    public UserLookupResponse searchUsers(SearchUsersQuery query) {
        List<User> usersByFilterRegex = userRepository.findUsersByFilterRegex(query.getFilter());
        return new UserLookupResponse(usersByFilterRegex);
    }

    @QueryHandler
    @Override
    public UserLookupResponse getAllUsers(FindAllUsersQuery query) {
        return new UserLookupResponse(userRepository.findAll());
    }
}
