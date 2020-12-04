package jaxbtools;


import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Конвертер JAXB
 */
public class JaxbConverter {
    /**
     * Преобразование XML-файла в список ArrayList<T>
     * @param xml - строковое представление XML-файла
     * @param type - тип элементов в списке
     * @param <T> - дженерик (обобщённый тип), который собираемся использовать
     * @return - список объектов типа T
     * @throws IOException
     */
    public static <T> ArrayList<T> fromXml(String xml, Class<T> type) throws IOException {
        XmlMapper mapper = createXmlMapper();
        return mapper.readValue(xml, mapper.getTypeFactory().constructCollectionType(ArrayList.class, type));
    }

    private static XmlMapper createXmlMapper() {
        final XmlMapper mapper = new XmlMapper();
        JaxbAnnotationModule module = new JaxbAnnotationModule();

        mapper.registerModule(module);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        return mapper;
    }
}