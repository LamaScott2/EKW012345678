package org.ekwateur.classes.client;

import lombok.Getter;
import org.ekwateur.classes.exceptions.IncorrectReferenceClientException;

@Getter
public class ProEnergyClient extends EnergyClient {

    private String businessName;
    private String siret;
    private Integer turnover;

    public ProEnergyClient(String referenceClient, String businessName, String siret, Integer turnover) throws IncorrectReferenceClientException {
        super(referenceClient.toUpperCase());
        this.businessName = businessName;
        this.siret = siret;
        this.turnover = turnover;
    }
}
