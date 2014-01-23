package UserSample;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest extends TestCase {

    @InjectMocks
    private UserManager userManager;

    @Mock
    private UserService userService;

    @Test
    public void testSaveUser() throws Exception {
        User user = new User("David");
        userManager.saveUser(user);
        Mockito.verify(userService).saveUser(user);
        Mockito.verify(userService).saveUser(Mockito.<User>any());
    }

    @Test
    public void testCount() throws Exception {
        userManager.findUser("David");
        Mockito.verify(userService, Mockito.times(1)).findUserByName("David");
        Mockito.verifyNoMoreInteractions(userService);
    }

    @Test
    public void testZero() throws Exception {
        User user = new User("David", new Date());
        userManager.getUserLastLogin(user);
        Mockito.verifyZeroInteractions(userService);
    }

    public void testFindUserByName() throws Exception {

    }

    public void testUpdateUser() throws Exception {

    }
}
