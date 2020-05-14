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

        // False: Too short with valid complexity
        assertFalse(Person.isValidPassword("aA1"));

        // False: Long enough but no uppercase or numbers
        assertFalse(Person.isValidPassword("complexity"));

        // False: Long enough but no uppercase
        assertFalse(Person.isValidPassword("complexity1"));

        // False: Long enough but no number
        assertFalse(Person.isValidPassword("Complexity"));

        // True : Long enough with uppercase and number
        assertTrue(Person.isValidPassword("El1buBkqQ"));

        // True: Long enough to not need complexity
        assertTrue(Person.isValidPassword("discontinuation"));
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