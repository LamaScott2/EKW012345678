package org.ekwateur.services.client;

import org.ekwateur.classes.client.EnergyClient;
import org.ekwateur.classes.client.ProEnergyClient;
import org.ekwateur.classes.energy.Energy;

import java.util.EnumMap;
import java.util.List;

public class EnergyPricingService {

    private static EnergyPricingService instance;
    public static Double COMMON_ELECTRICITY_PRICE = 0.133;
    public static Double COMMON_GAS_PRICE = 0.108;
    public static Integer TURNOVER_GAS_THRESHOLD = 1000000;
    public static Integer TURNOVER_ELECTRICITY_THRESHOLD = 1000000;
    public static Double PRO_ELECTRICITY_PRICE = 0.112;
    public static Double PRO_GAS_PRICE = 0.117;
    public static Double PRO_ELECTRICITY_PRICE_DECREASED = 0.110;
    public static Double PRO_GAS_PRICE_DECREASED = 0.123;
    public static Double UNDEFINED_PRICE = 0.000;

    private EnergyPricingService() {
    }

    public static EnergyPricingService getInstance() {
        if (instance == null) {
            instance = new EnergyPricingService();
        }
        return instance;
    }

    EnumMap<Energy, Double> getClientEnergyPricingByEnergyType(EnergyClient client, List<Energy> energies) {
        EnumMap<Energy, Double> energiesPricing = new EnumMap<>(Energy.class);

        energies.forEach(energy -> energiesPricing.put(energy, getClientEnergyPrice(client, energy)));

        return energiesPricing;
    }

    private Double getClientEnergyPrice(EnergyClient client, Energy energy) {
        return switch (energy) {
            case ELECTRICITY -> getClientElectricityPrice(client);
            case GAS -> getClientGasPrice(client);
            default -> UNDEFINED_PRICE;
        };
    }

    private Double getClientElectricityPrice(EnergyClient client) {
        if (client instanceof ProEnergyClient) {
            return ((ProEnergyClient) client).getTurnover() > TURNOVER_ELECTRICITY_THRESHOLD ? PRO_ELECTRICITY_PRICE_DECREASED : PRO_ELECTRICITY_PRICE;
        } else {
            return COMMON_ELECTRICITY_PRICE;
        }
    }

    private Double getClientGasPrice(EnergyClient client) {
        if (client instanceof ProEnergyClient) {
            return ((ProEnergyClient) client).getTurnover() > TURNOVER_GAS_THRESHOLD ? PRO_GAS_PRICE_DECREASED : PRO_GAS_PRICE;
        } else {
            return COMMON_GAS_PRICE;
        }
    }
}
