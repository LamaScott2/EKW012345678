package org.ekwateur.services.client;

import org.ekwateur.classes.client.ProEnergyClient;
import org.ekwateur.classes.consumption.EnergyConsumption;
import org.ekwateur.classes.energy.Energy;
import org.ekwateur.classes.exceptions.IncorrectReferenceClientException;
import org.ekwateur.classes.repository.ConsumptionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

public class EnergyClientConsumptionServiceTest {

    @Test
    public void shouldGetCorrectCostForClient() throws IncorrectReferenceClientException {
        ConsumptionRepository mockRepository = Mockito.mock(ConsumptionRepository.class);
        EnergyPricingService mockService = Mockito.mock(EnergyPricingService.class);

        MockedStatic<ConsumptionRepository> staticMockRepository = mockStatic(ConsumptionRepository.class);
        staticMockRepository.when(ConsumptionRepository::getInstance).thenReturn(mockRepository);
        MockedStatic<EnergyPricingService> staticMockService = mockStatic(EnergyPricingService.class);
        staticMockService.when(EnergyPricingService::getInstance).thenReturn(mockService);

        EnergyClientConsumptionService energyClientConsumptionService = new EnergyClientConsumptionService();

        ProEnergyClient client = new ProEnergyClient("EKW012345678", "Small Company", "siret", 123456);
        EnumMap<Energy, Double> mockPrices = new EnumMap<>(Map.of(
                Energy.ELECTRICITY, 1.000,
                Energy.GAS, 2.000
        ));
        Set<EnergyConsumption> mockConsumptions = Set.of(
                new EnergyConsumption(Energy.ELECTRICITY, LocalDate.now(), 1.000),
                new EnergyConsumption(Energy.GAS, LocalDate.now(), 2.000)
        );

        when(mockService.getClientEnergyPricingByEnergyType(any(), any())).thenReturn(mockPrices);
        when(mockRepository.getClientMonthlyConsumptions(any(), any(), any())).thenReturn(mockConsumptions);

        Assertions.assertEquals(energyClientConsumptionService.calculateClientMonthlyConsumption(client, Year.now(), Month.FEBRUARY), 5.000);
    }
}
