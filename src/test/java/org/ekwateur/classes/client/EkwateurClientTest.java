package org.ekwateur.classes.client;

import org.ekwateur.classes.exceptions.IncorrectReferenceClientException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EkwateurClientTest {

    @Test
    public void shouldNotThrowExceptionBecauseReferenceClientMatchingDefinedPattern() {
        assertDoesNotThrow(() -> new CommonEnergyClient("EKW012345678", "civility", "firstName", "secondName"));
        assertDoesNotThrow(() -> new CommonEnergyClient("ekw012345678", "civility", "firstName", "secondName"));
    }

    @Test
    public void shouldThrowExceptionBecauseReferenceClientNotMatchingDefinedPattern() {
        assertThrows(IncorrectReferenceClientException.class, () -> new CommonEnergyClient("EKWW87654321", "civility", "firstName", "secondName"));
        assertThrows(IncorrectReferenceClientException.class, () -> new CommonEnergyClient("EKW0123A5678", "civility", "firstName", "secondName"));
        assertThrows(IncorrectReferenceClientException.class, () -> new CommonEnergyClient("1KW012345678", "civility", "firstName", "secondName"));
    }
}
