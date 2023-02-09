package com.modicon.user.query.api.queries;

import com.modicon.user.query.api.abstractions.AbstractQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SearchUsersQuery extends AbstractQuery {
    private String filter;
}
