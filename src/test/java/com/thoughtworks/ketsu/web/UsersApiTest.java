package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(ApiTestRunner.class)
public class UsersApiTest extends ApiSupport {
    @Inject
    UserRepository userRepository;

    @Test
    public void should_return_201_when_create_user() {
        final Response POST = post("users", TestHelper.userMap("felix"));
        assertThat(POST.getStatus(), is(201));
        assertThat(Pattern.matches(".*?/users/[0-9-]*", POST.getLocation().toASCIIString()), is(true));
    }

    @Test
    public void should_return_400_when_create_user_with_missing_params() {
        final Response POST = post("users", TestHelper.userMap(""));
        assertThat(POST.getStatus(), is(400));
    }

    @Test
    public void should_return_200_when_find_user() {
        User user = userRepository.createUser(TestHelper.userMap("felix"));
        final Response GET = get("users/" + user.getId());
        assertThat(GET.getStatus(), is(200));
        final Map<String, Object> RET = GET.readEntity(Map.class);
        assertThat(RET.get("id"), is(user.getId()));
    }

    @Test
    public void should_return_404_when_user_not_found() {
        User user = userRepository.createUser(TestHelper.userMap("felix"));
        final Response GET = get("users/" + (user.getId() + 1));
        assertThat(GET.getStatus(), is(404));
    }
}
