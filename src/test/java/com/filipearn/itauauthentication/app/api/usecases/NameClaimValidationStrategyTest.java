package com.filipearn.itauauthentication.app.api.usecases;

import com.filipearn.itauauthentication.app.usecases.NameClaimValidationStrategy;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import static com.filipearn.itauauthentication.app.enumeration.ClaimEnum.*;

public class NameClaimValidationStrategyTest {

    private final NameClaimValidationStrategy strategy = new NameClaimValidationStrategy();

    @Test
    public void testShouldReturnTrueWhenNameIsValid() {
        String validName = "Maria";

        assertTrue(strategy.validateClaim(NAME.getDescription(), validName), "Must be false");
    }

    @Test
    public void testShouldReturnTrueWhenNameIsEqualThan256() {
        String nameWith256Characters = "MariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaa";

        assertTrue(strategy.validateClaim(NAME.getDescription(), nameWith256Characters), "Must be false");
    }

    @Test
    public void testShouldReturnFalseWhenNameIsEqualThan256() {
        String nameWith257Characters = "MariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaa";

        assertFalse(strategy.validateClaim(NAME.getDescription(), nameWith257Characters), "Must be false");
    }

    @Test
    public void testShouldReturnFalseWhenNameHasNumber() {
        String nameWithNumber = "M4ria";

        assertFalse(strategy.validateClaim(NAME.getDescription(), nameWithNumber), "Must be false");
    }

    @Test
    public void testShouldReturnFalseWhenNameIsNull() {
        String nameWithNumber = null;

        assertFalse(strategy.validateClaim(NAME.getDescription(), nameWithNumber), "Must be false");
    }




}
