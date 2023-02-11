package com.delivery.securityauth.user;

import java.util.HashSet;
import java.util.Set;

public enum Role {
    USER,
    LIVREUR,
    RESTAURANT,
    ADMIN;

    private final static Set<String> values = new HashSet<>(Role.values().length);

    static{
        for(Role r: Role.values())
            values.add(r.name());
    }

    public static boolean contains( String value ){
        return values.contains(value);
    }


}

