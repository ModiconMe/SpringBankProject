package com.modicon.user.core.abstractions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
abstract public class AbstractEvent {
    protected String id;
}
