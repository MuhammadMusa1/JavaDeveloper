package com.example.iso20022;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Iso20022Parser {
    public static void main(String[] args) throws JAXBException {
        // Пример XML-сообщения ISO 20022
        String xml = """
            <?xml version="1.0" encoding="UTF-8"?>
            <PmtInf>
                <PmtId>TRANS123</PmtId>
                <Amt>1000.00</Amt>
            </PmtInf>
            """;

        // Парсинг XML в объект
        JAXBContext context = JAXBContext.newInstance(PaymentInfo.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        PaymentInfo payment = (PaymentInfo) unmarshaller.unmarshal(new StringReader(xml));
        System.out.println("Parsed: " + payment);

        // Обратная сериализация в XML
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(payment, writer);
        System.out.println("Serialized:\n" + writer);
    }
}