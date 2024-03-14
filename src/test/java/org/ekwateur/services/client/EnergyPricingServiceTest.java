package org.ekwateur.services.client;

import org.ekwateur.classes.client.CommonEnergyClient;
import org.ekwateur.classes.client.ProEnergyClient;
import org.ekwateur.classes.energy.Energy;
import org.ekwateur.classes.exceptions.IncorrectReferenceClientException;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnergyPricingServiceTest {

    EnergyPricingService energyPricingService = EnergyPricingService.getInstance();

    @Test
    public void shouldGetCorrectPricesForProClientUnderThreshold() throws IncorrectReferenceClientException {
        ProEnergyClient client = new ProEnergyClient("EKW012345678", "Small Company", "siret", 123456);
        EnumMap<Energy, Double> prices = energyPricingService.getClientEnergyPricingByEnergyType(client, Energy.getAllEnergies());

        assertEquals(prices.get(Energy.ELECTRICITY), EnergyPricingService.PRO_ELECTRICITY_PRICE);
        assertEquals(prices.get(Energy.GAS), EnergyPricingService.PRO_GAS_PRICE);
    }

    @Test
    public void shouldGetCorrectPricesForProClientOverThreshold() throws IncorrectReferenceClientException {
        ProEnergyClient client = new ProEnergyClient("EKW012345678", "Big Company", "siret", 1000000000);
        EnumMap<Energy, Double> prices = energyPricingService.getClientEnergyPricingByEnergyType(client, Energy.getAllEnergies());

        assertEquals(prices.get(Energy.ELECTRICITY), EnergyPricingService.PRO_ELECTRICITY_PRICE_DECREASED);
        assertEquals(prices.get(Energy.GAS), EnergyPricingService.PRO_GAS_PRICE_DECREASED);
    }

    @Test
    public void shouldGetCorrectPricesForCommonClient() throws IncorrectReferenceClientException {
        CommonEnergyClient client = new CommonEnergyClient("EKW012345678", "Mister", "Nguyen", "Kevin");
        EnumMap<Energy, Double> prices = energyPricingService.getClientEnergyPricingByEnergyType(client, Energy.getAllEnergies());

        assertEquals(prices.get(Energy.ELECTRICITY), EnergyPricingService.COMMON_ELECTRICITY_PRICE);
        assertEquals(prices.get(Energy.GAS), EnergyPricingService.COMMON_GAS_PRICE);
    }
}
