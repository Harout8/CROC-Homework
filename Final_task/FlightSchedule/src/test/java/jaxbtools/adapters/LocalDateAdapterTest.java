package jaxbtools.adapters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


public class LocalDateAdapterTest {
    @Test
    public void testUnmarshal() throws Exception {
        LocalDateAdapter localDateAdapter = new LocalDateAdapter();

        Assertions.assertEquals(LocalDate.of(2021,1,1), localDateAdapter.unmarshal("01.01.2021"));

        System.out.println(localDateAdapter.unmarshal("01.01.2021"));
        System.out.println(LocalDate.of(2021,1,1));
    }

    @Test
    public void testMarshal() throws Exception {
        LocalDateAdapter localDateAdapter = new LocalDateAdapter();

        Assertions.assertEquals("01.01.2021", localDateAdapter.marshal(LocalDate.of(2021,1,1)));

        System.out.println("01.01.2021");
        System.out.println(localDateAdapter.marshal(LocalDate.of(2021,1,1)));
    }
}
