package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by laura on 2017-03-25.
 */


@RunWith(JUnitQuickcheck.class)
public class KarumiHQsProperties {

    private KarumiHQs karumiHQs;
    private Chat chat;

    @Before
    public void setUp(){
        chat = mock(Chat.class);
        karumiHQs = new KarumiHQs();
    }

    @Property(trials = 1000)
    public void theNumberOfMaxibonsLeftCanNotBeLowerThanTwo(
            @From(DevelopersGenerator.class) Developer developer) {
        karumiHQs.openFridge(developer);
        assertTrue(karumiHQs.getMaxibonsLeft() > 2);
    }

    @Property
    public void ifSomeKarumiesGoToTheKitchenTheNumberOfMaxibonsLeftCantBeLowerThanTwo(
            List<@From(KarumiesGenerator.class) Developer> developers) {
        karumiHQs.openFridge(developers);
        assertTrue(karumiHQs.getMaxibonsLeft() > 2);
    }

    @Property
    public void ifThereAreTwoOrLessMaxibonsAfterOpeningTheFridgeTheDeveloperSendsAMessageToBuyMore(
            @From(HungryDevelopersGenerator.class) Developer developer) {
        karumiHQs.openFridge(developer);
        verify(chat).sendMessage("Hi guys, I'm " + developer.getName() + ". We need more maxibons!");
    }

    @Property
    public void ifThereAreLessThanTwoMaxibonsLeftAMessage(
            @From(NotSoHungryDevelopersGenerator.class) Developer developer) {
        karumiHQs.openFridge(developer);
        verify(chat, never()).sendMessage(any());
    }
}
