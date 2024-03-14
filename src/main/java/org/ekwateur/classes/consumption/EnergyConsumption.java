package org.ekwateur.classes.consumption;

import lombok.Getter;
import org.ekwateur.classes.energy.Energy;

import java.time.LocalDate;

@Getter
public class EnergyConsumption {
    private Energy energy;
    private LocalDate date;
    private Double kWh;

    public EnergyConsumption(Energy energy, LocalDate date, Double kWh) {
        this.energy = energy;
        this.date = date;
        this.kWh = kWh;
    }
}
