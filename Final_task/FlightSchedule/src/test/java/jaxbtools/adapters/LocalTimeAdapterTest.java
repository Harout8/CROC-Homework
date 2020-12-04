package jaxbtools.adapters;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;


public class LocalTimeAdapterTest {
    @Test
    public void testUnmarshal() throws Exception {
        LocalTimeAdapter localTimeAdapter = new LocalTimeAdapter();

        Assertions.assertEquals(LocalTime.of(11,0), localTimeAdapter.unmarshal("11:00"));

        System.out.println(localTimeAdapter.unmarshal("11:00"));
        System.out.println(LocalTime.of(11,0));
    }

    @Test
    public void testMarshal() throws Exception {
        LocalTimeAdapter localTimeAdapter = new LocalTimeAdapter();

        Assertions.assertEquals("11:00", localTimeAdapter.marshal(LocalTime.of(11,0)));

        System.out.println("11:00");
        System.out.println(localTimeAdapter.marshal(LocalTime.of(11,0)));
    }
}
