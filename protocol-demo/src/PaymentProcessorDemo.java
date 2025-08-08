import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PaymentProcessorDemo {

    // Симуляция обработки платежа
    static class PaymentTask implements Runnable {
        private final String transactionId;
        private final double amount;

        public PaymentTask(String transactionId, double amount) {
            this.transactionId = transactionId;
            this.amount = amount;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()
                    + " 🔄 Обрабатывает транзакцию " + transactionId
                    + " на сумму " + amount + " TJS");
            try {
                Thread.sleep(1000); // имитация задержки обработки
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()
                    + " ✅ Завершил транзакцию " + transactionId);
        }
    }

    public static void main(String[] args) {
        // Пул потоков на 3 "банковских клерка"
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Отправляем 5 транзакций на обработку
        executor.submit(new PaymentTask("TXN-001", 1000));
        executor.submit(new PaymentTask("TXN-002", 2500));
        executor.submit(new PaymentTask("TXN-003", 500));
        executor.submit(new PaymentTask("TXN-004", 1200));
        executor.submit(new PaymentTask("TXN-005", 800));

        executor.shutdown(); // запрещаем новые задачи
    }
}
