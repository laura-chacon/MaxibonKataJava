package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by laura on 2017-03-25.
 */

@RunWith(JUnitQuickcheck.class)
public class DeveloperProperties {
    private static final String ANY_NAME = "Pedro";
    private static final int ANY_NUMBER_OF_MAXIBONS = 1;

    @Property
    public void theNumberOfMaxibonsAssignedIsPositiveOrZero(int numberOfMaxibons) {
        Developer developer = new Developer(ANY_NAME, numberOfMaxibons);
        assertTrue(developer.getNumberOfMaxibonsToGrab() >= 0);
    }

    @Property public void theNameIsAssignedInConstruction(String name) {
        Developer developer = new Developer(name, ANY_NUMBER_OF_MAXIBONS);
        assertEquals(name, developer.getName());
    }

    @Test
    public void theNumberOfMaxibonsAssociatedToEveryKarumieIsAlreadyAssigned() {
        assertEquals(3, Karumies.PEDRO.getNumberOfMaxibonsToGrab());
        assertEquals(0, Karumies.DAVIDE.getNumberOfMaxibonsToGrab());
        assertEquals(1, Karumies.ALBERTO.getNumberOfMaxibonsToGrab());
        assertEquals(2, Karumies.SERGIO.getNumberOfMaxibonsToGrab());
        assertEquals(1, Karumies.JORGE.getNumberOfMaxibonsToGrab());
    }

}
