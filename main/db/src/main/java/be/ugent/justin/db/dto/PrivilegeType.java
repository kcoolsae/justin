/*
 * PrivilegeType.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.dto;

import lombok.Getter;

@Getter
public enum PrivilegeType {
    REGISTER_OWN('r'),     // register people from own country
    REGISTER_ALL('R');     // register people from all countries

    private final char code;

    PrivilegeType(char code) {
        this.code = code;
    }
}
