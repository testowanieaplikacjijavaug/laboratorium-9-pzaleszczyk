
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;

import java.util.Collection;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;

public class RaceResultServiceTest {
	Client c = mock(Client.class);
	Message m = mock(Message.class);

	@Test
	public void testSendReceive() {
		Collection<Client> clients = spy(new HashSet<>());
		RaceResultService rrs = new RaceResultService(clients);
		clients.add(c);	
		rrs.send(m);
		verify(c).receive(m);
	}
	
	@Test
	public void addSubscriberTest() {
		Collection<Client> clients = spy(new HashSet<>());
		RaceResultService rrs = new RaceResultService(clients);
		rrs.addSubscriber(c);
		assertEquals(1, clients.size());
		
		verify(clients).add(c);
	}
	
	@Test
	public void removeSubscriberTest() {
		Collection<Client> clients = spy(new HashSet<>());
		RaceResultService rrs = new RaceResultService(clients);
		clients.add(c);	
		rrs.removeSubscriber(c);
		assertEquals(new HashSet<>(), clients);
		
		verify(clients).remove(c);
	}
	
}