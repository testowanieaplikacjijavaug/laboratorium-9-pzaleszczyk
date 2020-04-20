
public interface Client {

	Object getEmail();
	String getName();

	void receive(Message message);

}
