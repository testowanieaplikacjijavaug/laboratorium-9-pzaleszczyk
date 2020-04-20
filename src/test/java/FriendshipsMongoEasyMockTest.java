
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;


public class FriendshipsMongoEasyMockTest {

	@InjectMocks
	FriendshipsMongo friendships = new FriendshipsMongo();

	
	private FriendsCollection friends = mock(FriendsCollection.class);
	private Person joe = mock(Person.class);

	@Test
	public void mockingWorksAsexpected(){
		Person joe = new Person("Joe");
		when(friends.findByName("Joe")).thenReturn(joe);
		assertThat(friends.findByName("Joe")).isEqualTo(joe);
	}

	@Test
	public void alexDoesNotHaveFriends(){
		assertThat(friendships.getFriendsList("Alex")).isEmpty();
	}

	@Test
	public void joeHas5Friends(){
		List<String> expected = Arrays.asList(new String[]{"Karol","Dawid","Maciej","Tomek","Adam"});
		when(friends.findByName("Joe")).thenReturn(joe);
		when(joe.getFriends()).thenReturn(expected);
		assertThat(friendships.getFriendsList("Joe")).hasSize(5).containsOnly("Karol","Dawid","Maciej","Tomek","Adam");
	}

	///NOWE TESTY
	//1
	@Test
	public void joeHasNoFriends(){
		List<String> expected = Arrays.asList(new String[]{});
		when(friends.findByName("Joe")).thenReturn(joe);
		when(joe.getFriends()).thenReturn(expected);
		assertThat(friendships.getFriendsList("Joe")).isEmpty();
	}
	//2
	@Test
	public void testGetName(){
		
		when(joe.getName()).thenReturn("Joe");
		assertThat(joe.getName()).isEqualTo("Joe");
	}
	//3
	@Test
	public void testAreFriends(){
		List<String> expected = Arrays.asList(new String[]{"Bob","Dawid","Maciej","Tomek","Adam"});

		when(friends.findByName("Joe")).thenReturn(joe);
		when(joe.getFriends()).thenReturn(expected);
		assertThat(friendships.areFriends("Joe", "Bob")).isTrue();

	}
//	//4
//	@Test
//	public void testaddfriends(){
//		ArrayList<String> expected = new ArrayList<String>();
//		expected.add("Karol");
//		
//		//Act
//		joe.addFriend(anyString());
//		EasyMock.whenLastCall().andAnswer(() -> {
//			expected.add(""+getCurrentArguments()[0]);
//			return null;
//		}).times(2);
//		
//		when(friends.findByName("Joe")).thenReturn(joe);
//		when(joe.getFriends()).thenReturn(expected);
//		replay(friends);
//		replay(joe);
//
//		joe.addFriend("Mon");
//		joe.addFriend("Yoh");
//		assertThat(friendships.getFriendsList("Joe")).hasSize(3).containsOnly("Karol","Mon","Yoh");
//	}
//	//5
//	@Test
//	public void testaddfriend(){
//		Person joe = createMock(Person.class);
//		List<String> expected = new ArrayList<String>();
//
//		//Act
//		joe.addFriend(anyString());
//		EasyMock.whenLastCall().andAnswer(() -> {
//			expected.add(""+getCurrentArguments()[0]);
//			return null;
//		}).times(1);
//
//		when(friends.findByName("Joe")).thenReturn(joe);
//		when(joe.getFriends()).thenReturn(expected);
//		replay(friends);
//		replay(joe);
//
//		joe.addFriend("Mon");
//		assertThat(friendships.areFriends("Joe", "Mon")).isTrue();
//
//	}


}