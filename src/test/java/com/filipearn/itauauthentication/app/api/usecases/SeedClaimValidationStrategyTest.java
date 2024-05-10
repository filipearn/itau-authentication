package com.filipearn.itauauthentication.app.api.usecases;

import com.filipearn.itauauthentication.app.usecases.SeedClaimValidationStrategy;
import org.junit.Test;

import static com.filipearn.itauauthentication.app.enumeration.ClaimEnum.SEED;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeedClaimValidationStrategyTest {

    private final SeedClaimValidationStrategy strategy = new SeedClaimValidationStrategy();

    @Test
    public void testShouldReturnFalseWhenSeedNotANumber() {
        String seed = "string";

        assertFalse(strategy.validateClaim(SEED.getDescription(), seed), "Must be false");
    }

    //Prime
    @Test
    public void testShouldReturnTrueWhenSeedIsPrime2() {
        String seed = "2";

        assertTrue(strategy.validateClaim(SEED.getDescription(), seed), "Must be false");
    }

    @Test
    public void testShouldReturnTrueWhenSeedIsPrime3() {
        String seed = "3";

        assertTrue(strategy.validateClaim(SEED.getDescription(), seed), "Must be false");
    }

    @Test
    public void testShouldReturnTrueWhenSeedIsPrime5() {
        String seed = "5";

        assertTrue(strategy.validateClaim(SEED.getDescription(), seed), "Must be false");
    }
    @Test
    public void testShouldReturnTrueWhenSeedIsPrime7() {
        String seed = "7";

        assertTrue(strategy.validateClaim(SEED.getDescription(), seed), "Must be false");
    }

    @Test
    public void testShouldReturnTrueWhenSeedIsPrime11() {
        String seed = "11";

        assertTrue(strategy.validateClaim(SEED.getDescription(), seed), "Must be false");
    }

    //Not Prime
    @Test
    public void testShouldReturnFalseWhenSeedIsPrime0() {
        String seed = "0";

        assertFalse(strategy.validateClaim(SEED.getDescription(), seed), "Must be false");
    }

    @Test
    public void testShouldReturnFalseWhenSeedIsNotPrime1() {
        String seed = "1";

        assertFalse(strategy.validateClaim(SEED.getDescription(), seed), "Must be false");
    }

    @Test
    public void testShouldReturnFalseWhenSeedIsNotPrime4() {
        String seed = "4";

        assertFalse(strategy.validateClaim(SEED.getDescription(), seed), "Must be false");
    }

    @Test
    public void testShouldReturnFalseWhenSeedIsNotPrime6() {
        String seed = "6";

        assertFalse(strategy.validateClaim(SEED.getDescription(), seed), "Must be false");
    }

    @Test
    public void testShouldReturnFalseWhenSeedIsNotPrime8() {
        String seed = "8";

        assertFalse(strategy.validateClaim(SEED.getDescription(), seed), "Must be false");
    }

    @Test
    public void testShouldReturnFalseWhenSeedIsNotPrime9() {
        String seed = "9";

        assertFalse(strategy.validateClaim(SEED.getDescription(), seed), "Must be false");
    }

    @Test
    public void testShouldReturnFalseWhenSeedIsNotPrime10() {
        String seed = "10";

        assertFalse(strategy.validateClaim(SEED.getDescription(), seed), "Must be false");
    }

    @Test
    public void testShouldReturnFalseWhenSeedIsNull() {
        assertFalse(strategy.validateClaim(SEED.getDescription(), null), "Must be false");
    }




}
