import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MockDiff {
	Client mockito = Mockito.mock(Client.class);
	Client easymock = EasyMock.createNiceMock(Client.class);


	//Z Mockito mozna veryfikowac poszczegolne metody. A z easy mocks tylko wszystkie.
	//W mockito weryfikacja jest opcjonalna
	//Na przykladach pokazana jest takze roznica w zapisie.
	//Mockito nie wymaga metody replay.
	@Test
	public void mockito() {
		Mockito.when(mockito.getEmail()).thenReturn("Osiem");
		Mockito.when(mockito.getName()).thenReturn("sth");

		assertEquals("Osiem", mockito.getEmail());

		Mockito.verify(mockito).getEmail();
	}

	@Test
	public void easymock() {
		EasyMock.expect(easymock.getEmail()).andReturn("Osiem");
		EasyMock.expect(easymock.getName()).andReturn("sth");

		EasyMock.replay(easymock);

		assertEquals("Osiem", easymock.getEmail());
		assertEquals("sth", easymock.getName());

		EasyMock.verify(easymock);
	}
	
	//Mockito oferuje "spy" czyli czesciowy mock . Easymocks nie posiada takiej opcji.
	//W przypadku gdy metoda nie jest stubowana spy calluje prawdziwa metode.
	
	List<String> list = new ArrayList<String>();
    List<String> spyList = Mockito.spy(list);
	List<String> mockList = Mockito.mock(List.class);

	@Test
	public void test_mock_no_stub() {
		mockList.add("Example");
		assertNull(mockList.get(0));
	}

	@Test
	public void test_spy_no_stub() {
		spyList.add("Example");
		assertEquals("Example", spyList.get(0));
	}

	@Test
	public void test_mock_stub() {
		Mockito.when(mockList.get(0)).thenReturn("Returned");

		mockList.add("Example");
		assertEquals("Returned", mockList.get(0));
	}

	
	@Test
    public void test_spy_stub() {
        Mockito.doReturn("Returned").when(spyList).get(0);
 
        assertEquals("Returned", spyList.get(0));
    }
	
	//Mockito posiada uproszczony model stubowania - metody pow
	//tarzane sa z stubowana wartoscia bez wzgledy na to ile razy byla wywolana.
	@Test
	public void multiple_mockito() {
		Mockito.when(mockito.getName()).thenReturn("Returned");
		mockList.add("Example");
		assertEquals("Returned", mockito.getName());
		mockList.add("Example");
		assertEquals("Returned", mockito.getName());
		
	}
	
	@Test
	public void multiple_easy() {
		EasyMock.expect(easymock.getName()).andReturn("Returned");
		EasyMock.replay(easymock);
		mockList.add("Example");
		assertEquals("Returned", easymock.getName());
		
		mockList.add("Example");
		assertEquals(null, easymock.getName());
		
	}
	
}
