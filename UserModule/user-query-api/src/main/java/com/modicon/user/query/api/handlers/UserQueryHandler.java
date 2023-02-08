package com.modicon.user.query.api.handlers;

import com.modicon.user.query.api.dto.UserLookupResponse;
import com.modicon.user.query.api.queries.FindAllUsersQuery;
import com.modicon.user.query.api.queries.FindUserByIdQuery;
import com.modicon.user.query.api.queries.SearchUsersQuery;

public interface UserQueryHandler {
    UserLookupResponse getUserById(FindUserByIdQuery query);
    UserLookupResponse searchUsers(SearchUsersQuery query);
    UserLookupResponse getAllUsers(FindAllUsersQuery query);
}
