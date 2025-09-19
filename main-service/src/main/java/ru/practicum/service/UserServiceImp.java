package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.exception.PaginationException;
import ru.practicum.exception.UserNotFoundException;
import ru.practicum.model.User;
import ru.practicum.model.dto.UserDto;
import ru.practicum.model.dto.UserRequest;
import ru.practicum.model.mapper.UserMapper;
import ru.practicum.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto addUser(UserRequest userRequest) {

        User currentUser = userRepository.getByEmail(userRequest.getEmail());
        if (currentUser != null) {
            throw new RuntimeException("User already exists");
        }
        User newUser = userRepository.save(UserMapper.toUser(userRequest));
        return UserMapper.toUserDto(newUser);
    }

    @Override
    public List<UserDto> getUsers(List<Long> ids, Integer from, Integer size) {
        if (from != null && from < 0) {
            throw new PaginationException("The 'from' parameter must be >= 0");
        }
        if (size != null && size < 1) {
            throw new PaginationException("The 'size' parameter must be >= 1");
        }

        List<User> users;

        if (ids != null && !ids.isEmpty()) {
            users = userRepository.findAllByIdInOrderById(ids);
        } else {
            PageRequest page = PageRequest.of(
                    from != null ? from : 0,
                    size != null ? size : Integer.MAX_VALUE,
                    Sort.by("id").ascending()
            );
            users = userRepository.findAll(page).getContent();
        }

        return users.stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id= " + id + " not found"));
        userRepository.deleteById(id);
    }
}
