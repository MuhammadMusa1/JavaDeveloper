package com.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Iso20022Example {
    public static void main(String[] args) {
        try {
            // Создаём объект платежа (например, для Ориёнбанка)
            PaymentInfo payment = new PaymentInfo("TRANS123", 1000.00, "TJS");

            // Создаём XML (маршалинг)
            JAXBContext context = JAXBContext.newInstance(PaymentInfo.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter writer = new StringWriter();
            marshaller.marshal(payment, writer);
            String xmlOutput = writer.toString();
            System.out.println("Созданное ISO 20022 сообщение:\n" + xmlOutput);

            // Парсим XML обратно в объект (демаршалинг)
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(xmlOutput);
            PaymentInfo parsedPayment = (PaymentInfo) unmarshaller.unmarshal(reader);
            System.out.println("Спарсенный объект: " + parsedPayment);

        } catch (JAXBException e) {
            System.err.println("Ошибка обработки XML: " + e.getMessage());
        }
    }
}