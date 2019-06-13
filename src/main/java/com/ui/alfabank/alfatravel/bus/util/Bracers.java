package com.ui.alfabank.alfatravel.bus.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Bracers {
    public String createBracers(int i) {
        final StringBuilder result = new StringBuilder();
        for (int j = 0; j < i; j++) {
            result.append("{} ");
        }
        return result.toString();
    }
}