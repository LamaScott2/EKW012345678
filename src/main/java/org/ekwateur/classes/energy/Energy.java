package org.ekwateur.classes.energy;

import java.util.Arrays;
import java.util.List;

public enum Energy {
    ELECTRICITY, GAS, WOOD, PETROL;

    public static List<Energy> getAllEnergies() {
        return Arrays.asList(Energy.values());
    }
}