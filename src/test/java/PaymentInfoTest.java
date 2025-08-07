import org.junit.Test;
import static org.junit.Assert.*;

public class PaymentInfoTest {
    @Test
    public void testPaymentInfo() {
        PaymentInfo payment = new PaymentInfo("TRANS123", 1000.0, "TJS");
        assertEquals("TRANS123", payment.getPaymentId());
        assertEquals(1000.0, payment.getAmount(), 0.01);
        assertEquals("TJS", payment.getCurrency());
    }
}