package se.hkr.e7.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonTest {

    @Test
    void isValidSsn() {
        // Correct format
        assertTrue(Person.isValidSsn("7309260000"));

        // Correct long format
        assertTrue(Person.isValidSsn("197309260000"));

        // Wrong eleven characters
        assertFalse(Person.isValidSsn("97309260000"));

        // Wrong too long
        assertFalse(Person.isValidSsn("1197309260000"));

        // Wrong too short
        assertFalse(Person.isValidSsn("309260000"));

        // Wrong not a number
        assertFalse(Person.isValidSsn("7309a60000"));

        // Wrong part of a larger string
        assertFalse(Person.isValidSsn("Lorem 7309260000 Ipsum"));
    }

    @Test
    void isValidPassword() {
        // False: Too short
        assertFalse(Person.isValidPassword("abc"));

        // False: Long enough but no uppercase,digit and special characters
        assertFalse(Person.isValidPassword("weogweuhgweg"));

        // True : Long and complex enough
        assertTrue(Person.isValidPassword("abc1@Aabc"));
        // True : Long and complex enough
        assertTrue(Person.isValidPassword("El1@bubkqQ"));
    }

    @Test
    void isValidEmail() {
    }

    @Test
    void isValidPhone() {
    }

    @Test
    void isValidSalary() {
    }

    @Test
    void testIsValidSalary() {
    }
}