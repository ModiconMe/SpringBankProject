package com.modicon.user.query.api.abstractions;

import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryGateway;

@RequiredArgsConstructor
abstract public class AbstractQueryController {
    protected final QueryGateway queryGateway;
}
