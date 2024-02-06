package org.geekhub.crypto.users;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(int userId) {
        return userRepository.getUser(userId)
            .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found"));
    }

    public boolean isUserExist(int userId) {
        return userRepository.isUserExists(userId);
    }
}
