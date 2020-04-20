import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;

import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;

public class MessengerTest {
	
	Template t = mock(Template.class);
	Client c = mock(Client.class);
	MailServer ms = mock(MailServer.class);
	TemplateEngine te = mock(TemplateEngine.class);
	
	
	@Test
	public void sendMesessage() {
		Messenger mess = new Messenger(ms,te);
		when(te.prepareMessage(t,c)).thenReturn("Message");
		when(c.getEmail()).thenReturn("mail@gmail.com");
		
		mess.sendMessage(c, t);
		
		verify(te).prepareMessage(t, c);
		verify(c).getEmail();
		verify(ms).send("mail@gmail.com", "Message");
	}
	
	
	
}
