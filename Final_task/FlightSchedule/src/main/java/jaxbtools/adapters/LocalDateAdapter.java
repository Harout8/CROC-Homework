package jaxbtools.adapters;


import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Адаптер для работы с датами и XML
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    /**
     * Формат даты
     */
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Преобразование строки в LocalDate
     *
     * @param v - строка с датой для преобразования
     * @return дату LocalDate
     * @throws Exception
     */
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v, dateFormat);
    }

    /**
     * Преобразование LocalDate в строку
     *
     * @param v - дата LocalDate
     * @return дату в строковом представлении
     * @throws Exception
     */
    public String marshal(LocalDate v) throws Exception {
        return v.format(dateFormat);
    }
}
