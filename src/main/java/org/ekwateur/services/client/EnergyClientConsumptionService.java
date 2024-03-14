package org.ekwateur.services.client;

import org.ekwateur.classes.client.EnergyClient;
import org.ekwateur.classes.consumption.EnergyConsumption;
import org.ekwateur.classes.energy.Energy;
import org.ekwateur.classes.repository.ConsumptionRepository;

import java.time.Month;
import java.time.Year;
import java.util.EnumMap;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class EnergyClientConsumptionService {

    private final EnergyPricingService energyPricingService;
    private final ConsumptionRepository consumptionRepository;

    public EnergyClientConsumptionService() {
        this.energyPricingService = EnergyPricingService.getInstance();
        this.consumptionRepository = ConsumptionRepository.getInstance();
    }

    public Double calculateClientMonthlyConsumption(EnergyClient client, Year targetYear, Month targetMonth) {
        AtomicReference<Double> totalCost = new AtomicReference<>(0.000);
        Set<EnergyConsumption> consumptions = consumptionRepository.getClientMonthlyConsumptions(client, targetYear, targetMonth);
        EnumMap<Energy, Double> energiesPricing = energyPricingService.getClientEnergyPricingByEnergyType(client, Energy.getAllEnergies());

        consumptions.forEach(energyConsumption -> totalCost.set(totalCost.get() + (energyConsumption.getKWh() * energiesPricing.get(energyConsumption.getEnergy()))));

        return totalCost.get();
    }
}
