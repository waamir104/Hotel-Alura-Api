package dev.waamir.hotelaluraapi.application.service.User;

import java.util.List;
import java.util.Optional;

import dev.waamir.hotelaluraapi.domain.model.User;
import dev.waamir.hotelaluraapi.domain.port.IUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceImpl implements IUserService<User> {

    private final IUserRepository<User> userRepository;

    @Override
    public User create(User user) {
        return userRepository.create(user);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public List<User> list() {
        return userRepository.list();
    }

    @Override
    public void enable(String type, String userIdEncoded, String url) {
        userRepository.enable(type, userIdEncoded, url);
    }
    
}
