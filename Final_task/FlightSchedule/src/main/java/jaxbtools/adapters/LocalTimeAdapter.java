package jaxbtools.adapters;


import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * Адаптер для работы со временем и XML
 */
public class LocalTimeAdapter extends XmlAdapter<String, LocalTime> {

    /**
     * Формат времени
     */
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Преобразование строки в LocalTime
     *
     * @param v - строка с временем для преобразования
     * @return время LocalTime
     * @throws Exception
     */
    public LocalTime unmarshal(String v) throws Exception {
        return LocalTime.parse(v, dateFormat);
    }

    /**
     * Преобразование LocalTime в строку
     *
     * @param v - время LocalTime
     * @return время в строковом представлении
     * @throws Exception
     */
    public String marshal(LocalTime v) throws Exception {
        return v.format(dateFormat);
    }
}
