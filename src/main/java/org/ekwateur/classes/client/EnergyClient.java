package org.ekwateur.classes.client;

import lombok.Getter;
import org.ekwateur.classes.exceptions.IncorrectReferenceClientException;

@Getter
public abstract class EnergyClient extends EkwateurClient {

    public EnergyClient(String referenceClient) throws IncorrectReferenceClientException {
        super(referenceClient);
    }
}
