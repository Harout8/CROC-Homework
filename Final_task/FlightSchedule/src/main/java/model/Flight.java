package model;


import jaxbtools.adapters.LocalDateAdapter;
import jaxbtools.adapters.LocalTimeAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;


/**
 * Авиарейс
 */
@XmlRootElement()
public class Flight {
    /**
     * Идентификатор рейса (номер рейса)
     */
    @XmlElement()
    private int idNumber;

    /**
     * Признак "входящий/исходящий" рейс
     */
    @XmlElement()
    private String inOut;

    /**
     * Город "откуда"
     */
    @XmlElement()
    private String cityFrom;

    /**
     * Город "Куда"
     */
    @XmlElement()
    private String cityTo;

    /**
     * Дата взлёта
     */
    @XmlElement()
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate departureDate;

    /**
     * Дата прибытия
     */
    @XmlElement()
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate arrivalDate;

    /**
     * Время взлёта
     */
    @XmlElement()
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    private LocalTime departureTime;

    /**
     * Время прибытия
     */
    @XmlElement()
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    private LocalTime arrivalTime;


    /**
     * Название таблицы данных о рейсах в БД
     */
    private static final String TABLE_NAME = "flight";


    /**
     * Конструктор рейса по умолчанию
     */
    public Flight() {
        // Конструктор по умолчанию
    }

    /**
     * Конструктор рейса
     *
     * @param idNumber - идентификатор рейса (номер рейса)
     * @param inOut - признак "входящий/исходящий" рейс
     * @param cityFrom - город "откуда"
     * @param cityTo - город "Куда"
     * @param departureDate - дата взлёта
     * @param arrivalDate - дата прибытия
     * @param departureTime - время взлёта
     * @param arrivalTime - время прибытия
     */
    public Flight(int idNumber,
                  String inOut,
                  String cityFrom,
                  String cityTo,
                  LocalDate departureDate,
                  LocalDate arrivalDate,
                  LocalTime departureTime,
                  LocalTime arrivalTime) {
        this.idNumber = idNumber;
        this.inOut = inOut;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }


    public int getIdNumber() {
        return idNumber;
    }

    public String getInOut() {
        return inOut;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }


    public static String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "idNumber=" + idNumber +
                ", inOut='" + inOut + '\'' +
                ", cityFrom='" + cityFrom + '\'' +
                ", cityTo='" + cityTo + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return idNumber == flight.idNumber &&
                Objects.equals(inOut, flight.inOut) &&
                Objects.equals(cityFrom, flight.cityFrom) &&
                Objects.equals(cityTo, flight.cityTo) &&
                Objects.equals(departureDate, flight.departureDate) &&
                Objects.equals(arrivalDate, flight.arrivalDate) &&
                Objects.equals(departureTime, flight.departureTime) &&
                Objects.equals(arrivalTime, flight.arrivalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNumber, inOut, cityFrom, cityTo, departureDate, arrivalDate, departureTime, arrivalTime);
    }
}
