package UserSample;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by dmendoza on 1/23/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @InjectMocks
    private UserManager userManager;

    @Mock
    private UserService userService;

    /*@Before
    public void setUp() throws Exception {
        userService = Mockito.mock(UserService.class);
        userManager = new UserManager();
        userManager.setUserService(userService);
    }*/

    @Test
    public void testSaveUser() throws Exception {
        User user = new User("David");
        userManager.saveUser(user);
        verify(userService).saveUser(user);
        verify(userService).saveUser(Mockito.<User>any());
        System.out.println("Success!");
    }

    @Test
    public void testCount() throws Exception {

    }

    @Test
    public void mockList() {
        LinkedList linkedList = mock(LinkedList.class);
        when(linkedList.get(0)).thenReturn("first");
        System.out.println(linkedList.get(0));
        System.out.println(linkedList.get(1));
    }

    @Test (expected = NullPointerException.class)
    public void mockList2() throws Exception {
        List<String> mockedList = mock(ArrayList.class);
        mockedList.add("once");
        mockedList.add("twice");
        mockedList.add("twice");
        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("five times");
        mockedList.add("five times");

        verify(mockedList, times(1)).add("once");
        //exact number of invocations verification
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");
        //verification using never(). never() is an alias to times(0)
        verify(mockedList, never()).add("never happened");
        //verification using atLeast()/atMost()
        verify(mockedList, atLeastOnce()).add("three times");
        verify(mockedList, atLeast(2)).add("five times");
        verify(mockedList, atMost(3)).add("three times");

        doThrow(new NullPointerException()).when(mockedList).clear();
        mockedList.clear();
    }

    @Test
    public void mockList3() {
        List singleMock = mock(List.class);
        singleMock.add("was first");
        singleMock.add("was second");

        InOrder inOrder = inOrder(singleMock);
        inOrder.verify(singleMock).add("was first");
        inOrder.verify(singleMock).add("was second");

        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        firstMock.add("first");
        secondMock.add("second");

        inOrder = inOrder(firstMock,secondMock);
        inOrder.verify(firstMock).add("first");
        inOrder.verify(secondMock).add("second");
    }
}
