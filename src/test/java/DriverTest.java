import Enums.DriverQualification;
import entity.Driver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.TreeSet;

public class DriverTest {
    @Test
    void DefaultConstructor(){
        Driver driver = new Driver();
        assertEquals(driver.getQualification(), new TreeSet<DriverQualification>());
    }

    @Test
    void SalaryUnderMinimal(){
    }
}
