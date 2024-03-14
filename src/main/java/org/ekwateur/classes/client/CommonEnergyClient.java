package org.ekwateur.classes.client;

import org.ekwateur.classes.exceptions.IncorrectReferenceClientException;

public class CommonEnergyClient extends EnergyClient {

    private String civility;
    private String firstName;
    private String secondName;

    public CommonEnergyClient(String referenceClient, String civility, String firstName, String secondName) throws IncorrectReferenceClientException {
        super(referenceClient.toUpperCase());
        this.civility = civility;
        this.firstName = firstName;
        this.secondName = secondName;
    }
}
