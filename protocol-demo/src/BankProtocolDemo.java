public class BankProtocolDemo {

    // ====== КОДИРОВАНИЕ СООБЩЕНИЯ ======
    public static String encodeMessage(String account, String amount, String currency) {
        // Формат: ACCOUNT=...;AMOUNT=...;CURRENCY=...;
        return "ACCOUNT=" + account + ";" +
               "AMOUNT=" + amount + ";" +
               "CURRENCY=" + currency + ";";
    }

    // ====== ДЕКОДИРОВАНИЕ СООБЩЕНИЯ ======
    public static void decodeMessage(String message) {
        String[] parts = message.split(";");
        for (String part : parts) {
            if (!part.isEmpty()) {
                String[] keyValue = part.split("=");
                if (keyValue.length == 2) {
                    System.out.println("  " + keyValue[0] + ": " + keyValue[1]);
                }
            }
        }
    }

    public static void main(String[] args) {
        // === Клиент отправляет перевод ===
        String encoded = encodeMessage("1234567890", "1000", "TJS");
        System.out.println("📤 Отправлено: " + encoded);

        // === Сервер принимает перевод ===
        System.out.println("📥 Получено:");
        decodeMessage(encoded);
    }
}
