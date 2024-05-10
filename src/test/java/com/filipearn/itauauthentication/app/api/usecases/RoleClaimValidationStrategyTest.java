package com.filipearn.itauauthentication.app.api.usecases;

import com.filipearn.itauauthentication.app.enumeration.RoleEnum;
import com.filipearn.itauauthentication.app.usecases.NameClaimValidationStrategy;
import com.filipearn.itauauthentication.app.usecases.RoleClaimValidationStrategy;
import org.junit.Test;

import static com.filipearn.itauauthentication.app.enumeration.ClaimEnum.NAME;
import static com.filipearn.itauauthentication.app.enumeration.ClaimEnum.ROLE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoleClaimValidationStrategyTest {

    private final RoleClaimValidationStrategy strategy = new RoleClaimValidationStrategy();

    @Test
    public void testShouldReturnTrueWhenRoleIsAdmin() {
        String role = RoleEnum.ADMIN.getDescription();

        assertTrue(strategy.validateClaim(ROLE.getDescription(), role), "Must be false");
    }

    @Test
    public void testShouldReturnTrueWhenRoleIsMember() {
        String role = RoleEnum.MEMBER.getDescription();

        assertTrue(strategy.validateClaim(ROLE.getDescription(), role), "Must be false");
    }

    @Test
    public void testShouldReturnTrueWhenRoleIsExternal() {
        String role = RoleEnum.EXTERNAL.getDescription();

        assertTrue(strategy.validateClaim(ROLE.getDescription(), role), "Must be false");
    }

    @Test
    public void testShouldReturnFalseWhenRoleIsExternalLoweCase() {
        String role = RoleEnum.EXTERNAL.getDescription().toLowerCase();

        assertFalse(strategy.validateClaim(ROLE.getDescription(), role), "Must be false");
    }

    @Test
    public void testShouldReturnFalseWhenRoleInvalid() {
        String role = "any";

        assertFalse(strategy.validateClaim(ROLE.getDescription(), role), "Must be false");
    }

    @Test
    public void testShouldReturnFalseWhenRoleIsNull() {
        assertFalse(strategy.validateClaim(ROLE.getDescription(), null), "Must be false");
    }




}
