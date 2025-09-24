package ru.practicum.request.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.event.model.Event;
import ru.practicum.event.repository.EventRepository;
import ru.practicum.exception.NotFoundException;
import ru.practicum.request.model.Request;
import ru.practicum.request.model.RequestStatus;
import ru.practicum.request.model.dto.RequestDto;
import ru.practicum.request.model.mapper.RequestMapper;
import ru.practicum.request.repository.RequestRepository;
import ru.practicum.user.model.User;
import ru.practicum.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RequestServiceImp implements RequestService{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private RequestRepository repository;

    @Autowired
    private  EventRepository eventRepository;

    @Autowired
    private  UserRepository userRepository;


    @Override
    public List<RequestDto> getAll(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException(String.format("User with id = %d not found", userId)));
        return repository.findByRequester(userId).stream().map(RequestMapper::toRequestDto).toList();
    }

    @Override
    public RequestDto create(Long userId, Long eventId) {
        User requestor = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(String.format("User with id = %d not found", userId)));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException(String.format("Event with id = %d not found", eventId)));
        Request request = new Request();
        request.setRequester(requestor);
        request.setEvent(event);
        request.setCreated(LocalDateTime.now());
        request.setStatus(RequestStatus.PENDING);
        return RequestMapper.toRequestDto(repository.save(request));
    }

    @Override
    public RequestDto cancelRequest(Long userId, Long requestId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(String.format("User with id = %d not found", userId)));
        Request request = repository.findById(requestId).orElseThrow(() -> new NotFoundException(String.format("Request with id = %d not found", requestId)));
        repository.updateToRejected(requestId);
        repository.flush();
        entityManager.clear();
        return RequestMapper.toRequestDto(repository.findById(requestId).get());
    }
}
