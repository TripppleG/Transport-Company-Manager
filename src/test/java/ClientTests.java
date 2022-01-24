import entity.Client;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClientTests {
    @Test
    void paidObligations(){
        Client c = new Client("Gosho", 31, "9009297350", "+359895634784", false);
        assertEquals(false, c.hasPaidObligations());
    }
}