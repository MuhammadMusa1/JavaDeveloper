import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoType;
import com.solab.iso8583.MessageFactory;
import java.io.IOException;

public class Iso8583Example {
    public static void main(String[] args) throws IOException {
        // Создаём фабрику сообщений ISO 8583
        MessageFactory<IsoMessage> messageFactory = new MessageFactory<>();
        
        // Настраиваем типы полей (можно загрузить из конфигурации)
        messageFactory.setCharacterEncoding("UTF-8");

        // Создаём запрос авторизации (MTI 0200)
        IsoMessage request = new IsoMessage();
        request.setType(0x200); // MTI: Запрос авторизации
        
        // Заполняем поля (Data Elements)
        request.setField(2, IsoType.LLVAR, "1234567890123456"); // Номер карты
        request.setField(3, IsoType.NUMERIC, "000000"); // Код транзакции
        request.setField(4, IsoType.AMOUNT, 100000); // Сумма: 1000.00 TJS (100000 в копейках)
        request.setField(11, IsoType.NUMERIC, "123456"); // Номер транзакции
        request.setField(41, IsoType.ALPHA, "TERM001"); // ID терминала
        request.setField(49, IsoType.ALPHA, "TJS"); // Код валюты

        // Выводим сообщение в бинарном виде (для отправки)
        byte[] binaryMessage = request.writeBinary();
        System.out.println("Отправленное сообщение: " + request.debugString());

        // Имитация ответа от банка (MTI 0210)
        IsoMessage response = messageFactory.parseMessage(binaryMessage, 0);
        response.setType(0x210); // Ответ на запрос
        response.setField(39, IsoType.ALPHA, "00"); // Код ответа: Успех

        // Парсим ответ
        System.out.println("Полученный ответ:");
        System.out.println("Код ответа: " + response.getField(39).getValue());
    }
}