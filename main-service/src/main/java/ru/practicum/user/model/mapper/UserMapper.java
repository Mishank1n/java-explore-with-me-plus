package ru.practicum.user.model.mapper;

import ru.practicum.user.model.User;
import ru.practicum.user.model.dto.UserDto;
import ru.practicum.user.model.dto.UserRequest;

public class UserMapper {
    public static User toUser(UserRequest userRequest) {
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setName(userRequest.getName());
        return user;
    }

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        return userDto;
    }

}
