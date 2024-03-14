package org.ekwateur.classes.client;

import org.ekwateur.classes.exceptions.IncorrectReferenceClientException;

import java.util.regex.Pattern;

public abstract class EkwateurClient {

    private String referenceClient;

    public EkwateurClient(String referenceClient) throws IncorrectReferenceClientException {
        final Pattern pattern = Pattern.compile("^EKW[0-9]+", Pattern.CASE_INSENSITIVE);

        if (!pattern.matcher(referenceClient).matches()) {
            throw new IncorrectReferenceClientException("EKW-001 : Reference client didn't match the required pattern : " + referenceClient);
        }

        this.referenceClient = referenceClient;
    }

}
