import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.assertj.core.api.SoftAssertions;
import org.easymock.EasyMock;


public class CarTest {
    private Car myFerrari = mock(Car.class);

    @Test
    public void test_instance_car(){
        assertTrue(myFerrari instanceof Car);
    }

    @Test
    public void test_default_behavior_needsFuel(){
        assertFalse(myFerrari.needsFuel(), "New test double should return False as boolean");
    }

    @Test
    public void test_default_behavior_temperature(){
        assertEquals(0.0, myFerrari.getEngineTemperature(), "New test double should return 0.0");
    }

    @Test
    public void test_stubbing_mock(){
        when(myFerrari.needsFuel()).thenReturn(true);
        assertTrue(myFerrari.needsFuel());
    }

    @Test
    public void test_exception(){
        when(myFerrari.needsFuel()).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> {
            myFerrari.needsFuel();
        });
    }

    @Test
    public void testVerification(){
        myFerrari.driveTo("Kartuzy");
        myFerrari.needsFuel();

        verify(myFerrari).driveTo("Kartuzy");
        verify(myFerrari).needsFuel();
        assertFalse(myFerrari.needsFuel());
    }


  	//4
  	@Test
  	public void test_enginetemp() {
  		when(myFerrari.getEngineTemperature()).thenReturn(100.0);
  		assertThat(myFerrari.getEngineTemperature()).isEqualTo(100.0);
  		
  		verify(myFerrari).getEngineTemperature();
  	}
  	
  	@Test
  	public void test_enginetemp_exception() {
  		when(myFerrari.getEngineTemperature()).thenThrow(new RuntimeException());
  		assertThrows(RuntimeException.class, () -> {
  			myFerrari.getEngineTemperature();
  		});
  		
  		verify(myFerrari).getEngineTemperature();
  	}
  	
  	@Test
    public void test_do_throw() {
        doThrow(new IllegalArgumentException()).when(myFerrari).driveTo(null);
        
        assertThrows(IllegalArgumentException.class, () -> myFerrari.driveTo(null));
    }
    
    @Test
    public void test_do_answer() {
        doAnswer((i) -> {
            assertEquals("Wejherowo", i.getArgument(0)+"");
            return null;
        }).when(myFerrari).driveTo("Wejherowo");
        
        myFerrari.driveTo("Wejherowo");
    }
    
}