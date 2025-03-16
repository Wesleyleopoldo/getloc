package com.getloc.syntech.getloc.requests.users;

import com.fasterxml.jackson.annotation.JsonAlias;

public record UpdateBody(@JsonAlias({"name"}) String userName, @JsonAlias({"email"}) String userEmail, @JsonAlias({"password"}) String password) {
}
