package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(DatabaseTestRunner.class)
public class MyBatisUserRepositoryTest {
    @Inject
    UserRepository userRepository;

    @Test
    public void should_save_user_and_find_by_id() {
        User user = userRepository.createUser(TestHelper.userMap("felix"));
        Optional<User> user_get = userRepository.findById(user.getId());
        assertThat(user_get.get().getId(), is(user.getId()));
    }

    @Test
    public void should_find_user_by_id() {
        User user = userRepository.createUser(TestHelper.userMap("felix"));
        Optional<User> user_get = userRepository.findById(user.getId());
        assertThat(user_get.get().getId(), is(user.getId()));
    }
}
