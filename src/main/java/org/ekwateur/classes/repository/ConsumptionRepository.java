package org.ekwateur.classes.repository;

import org.ekwateur.classes.client.EnergyClient;
import org.ekwateur.classes.consumption.EnergyConsumption;
import org.ekwateur.classes.energy.Energy;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class ConsumptionRepository {

    private static ConsumptionRepository instance;

    private ConsumptionRepository() {
    }

    public static ConsumptionRepository getInstance() {
        if (instance == null) {
            instance = new ConsumptionRepository();
        }
        return instance;
    }

    /*
     * Mocks data
     * */
    public Set<EnergyConsumption> getClientMonthlyConsumptions(EnergyClient client, Year targetYear, Month targetMonth) {
        int randomElectricityConsumptions = ThreadLocalRandom.current().nextInt(1, 10 + 1);
        int randomGazConsumptions = ThreadLocalRandom.current().nextInt(1, 10 + 1);
        Set<EnergyConsumption> consumptions = new HashSet<>(randomElectricityConsumptions + randomGazConsumptions);

        for (int i = 0; i < randomElectricityConsumptions; i++) {
            Double randomkWh = (double) ThreadLocalRandom.current().nextInt(50, 1000 + 1);

            consumptions.add(new EnergyConsumption(Energy.ELECTRICITY, LocalDate.of(targetYear.getValue(), targetMonth.getValue(), ThreadLocalRandom.current().nextInt(1, 27 + 1)), randomkWh));
        }

        for (int i = 0; i < randomGazConsumptions; i++) {
            Double randomkWh = (double) ThreadLocalRandom.current().nextInt(50, 1000 + 1);

            consumptions.add(new EnergyConsumption(Energy.GAS, LocalDate.of(targetYear.getValue(), targetMonth.getValue(), ThreadLocalRandom.current().nextInt(1, 27 + 1)), randomkWh));
        }

        return consumptions;
    }

    private static class ConsumptionRepositoryHolder {
        private final static ConsumptionRepository instance = new ConsumptionRepository();
    }
}
