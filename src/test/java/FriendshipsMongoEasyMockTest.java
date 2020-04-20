

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FriendshipsMongoEasyMockTest {

	@Mock
    private FriendsCollection friends;
    
    @InjectMocks
    private FriendshipsMongo friendships;
	
	private Person joe = mock(Person.class);

	@Test
	public void mockingWorksAsexpected(){
		Mockito.doReturn(joe).when(friends).findByName("Joe");
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
		
		verify(friends).findByName("Joe");
        verify(joe).getFriends();
	}

	///NOWE TESTY
	//1
	@Test
	public void joeHasNoFriends(){
		List<String> expected = Arrays.asList(new String[]{});
		when(friends.findByName("Joe")).thenReturn(joe);
		when(joe.getFriends()).thenReturn(expected);
		assertThat(friendships.getFriendsList("Joe")).isEmpty();
		
		verify(friends).findByName("Joe");
		verify(joe).getFriends();
	}
	//2
	@Test
	public void testGetName(){
		when(joe.getName()).thenReturn("Joe");
		assertThat(joe.getName()).isEqualTo("Joe");
		verify(joe).getName();
	}
	//3
	@Test
	public void testAreFriends(){
		List<String> expected = Arrays.asList(new String[]{"Bob","Dawid","Maciej","Tomek","Adam"});

		when(friends.findByName("Joe")).thenReturn(joe);
		when(joe.getFriends()).thenReturn(expected);
		assertThat(friendships.areFriends("Joe", "Bob")).isTrue();

		verify(friends).findByName("Joe");
		verify(joe).getFriends();
	}
	//4
	@Test
	public void testaddfriends(){
		List<String> expected = new ArrayList<String>();
		expected.add("Karol");

		doAnswer((i) -> {
			expected.add(""+i.getArgument(0));
			return null;
		}).when(joe).addFriend(anyString());

		when(friends.findByName("Joe")).thenReturn(joe);
		when(joe.getFriends()).thenReturn(expected);
		
		joe.addFriend("Mon");
		joe.addFriend("Yoh");
		assertThat(friendships.getFriendsList("Joe")).hasSize(3).containsOnly("Karol","Mon","Yoh");
		
		verify(friends).findByName("Joe");
		verify(joe).getFriends();
	}
	//5
	@Test
	public void testaddfriend(){
		List<String> expected = new ArrayList<String>();

		doAnswer((i) -> {
			expected.add(""+i.getArgument(0));
			return null;
		}).when(joe).addFriend(anyString());
		
		when(friends.findByName("Joe")).thenReturn(joe);
		when(joe.getFriends()).thenReturn(expected);

		joe.addFriend("Mon");
		assertThat(friendships.areFriends("Joe", "Mon")).isTrue();
		
		verify(friends).findByName("Joe");
		verify(joe).getFriends();

	}


}