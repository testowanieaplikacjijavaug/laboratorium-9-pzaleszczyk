import java.util.Collection;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

public class RaceResultServiceEasyMocksTest {

	Client c = createMock(Client.class);
	Message m = createMock(Message.class);

	@Test
	public void testSendReceive() {
		Collection<Client> clients = createMock(Collection.class);
		RaceResultService rrs = new RaceResultService(clients);
		replay(c);
		rrs.send(m);
		EasyMock.verify(c);
	}
	
	@Test
	public void addSubscriberTest() {
		Collection<Client> clients = createMock(Collection.class);
		RaceResultService rrs = new RaceResultService(clients);
		replay(c);
		rrs.addSubscriber(c);
		
		EasyMock.verify(c);
	}
	
	@Test
	public void removeSubscriberTest() {
		Collection<Client> clients = createMock(Collection.class);
		RaceResultService rrs = new RaceResultService(clients);
		replay(c);
		rrs.removeSubscriber(c);
		
		EasyMock.verify(c);
	}
	
}
