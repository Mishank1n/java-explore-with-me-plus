package ru.practicum.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.user.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByEmail(String email);

    List<User> findAllByIdInOrderById(List<Long> ids);
}
