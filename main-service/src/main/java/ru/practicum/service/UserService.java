package ru.practicum.service;

import ru.practicum.model.dto.UserDto;
import ru.practicum.model.dto.UserRequest;

import java.util.List;

public interface UserService {
    UserDto addUser(UserRequest userRequest);

    List<UserDto> getUsers(List<Long> ids, Integer from, Integer size);

    void deleteUser(Long id);
}
