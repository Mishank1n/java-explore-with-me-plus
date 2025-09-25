package ru.practicum.request.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.event.model.Event;
import ru.practicum.event.repository.EventRepository;
import ru.practicum.exception.BadParameterException;
import ru.practicum.exception.NotFoundException;
import ru.practicum.request.model.Request;
import ru.practicum.request.model.RequestStatus;
import ru.practicum.request.model.dto.RequestDto;
import ru.practicum.request.model.mapper.RequestMapper;
import ru.practicum.request.repository.RequestRepository;
import ru.practicum.user.model.User;
//import ru.practicum.user.model.mapper.UserMapper;
import ru.practicum.user.model.mapper.UserMapper;
import ru.practicum.user.repository.UserRepository;
import ru.practicum.user.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImp implements RequestService {

    private final EntityManager entityManager;
    private final RequestRepository repository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final RequestRepository requestRepository;


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

    @Override
    public List<RequestDto> getAllRequestsEventId(Long eventId) {
        if (eventId < 0) {
            throw new BadParameterException("Id собтия должен быть больше 0");
        }

        List<Request> partRequests = repository.findAllByEventId(eventId); //запрашиваем все запросы на событие
        if (partRequests == null || partRequests.isEmpty()) { //если запросов нет, возвращаем пустой список
            return new ArrayList<>();
        }
        /*преобразуем в DTO и возвращаем*/
        return partRequests.stream()
                .map(RequestMapper::toRequestDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateAll(List<RequestDto> requestDtoList, Event event) {
        /*подготовка данных для массового преобразоывания списка ParticipationRequestDto в ParticipationRequest*/
        /*Собираем пользователей в мапу <userId, User>*/
        List<Long> userIds = requestDtoList.stream()
                .map(RequestDto::getRequester)
                .collect(Collectors.toList()); //список id пользователей - авторов запросов на участие
        Map<Long, User> users = userService.getAllUsers(userIds).stream()
                .map(UserMapper::toUser)
                .collect(Collectors.toMap(User::getId, u -> u)); //мапа <userId, User> пользователей - авторов запросов на участие
        /*делаем две мапы, которе требуются для маппинга из ParticipationRequestDto в ParticipationRequest*/
        Map<Long, RequestDto> prDtoMap = requestDtoList.stream()
                .collect(Collectors.toMap(RequestDto::getId, e -> e)); //мапа <id запроса,сам запрос на участие>
        Map<Long, User> requestUserMap = requestDtoList.stream()
                .collect(Collectors.toMap(RequestDto::getId, pr -> users.get(pr.getRequester()))); //мапа <id запроса, User>

        List<Request> prList = requestDtoList.stream()
                .map(pr -> RequestMapper.toRequest(pr, event, requestUserMap.get(pr.getId())))
                .collect(Collectors.toList()); //преобразовали список DTO в список непосредственно запросов на участие.

        requestRepository.saveAll(prList); //сохраняем в БД обновленную информацию обо всех запросах на участие
    }

    @Transactional
    public void update(RequestDto prDto, Event event) {
        User user = UserMapper.toUser(userService.getUserById(prDto.getRequester())); //пользователь запрашивающий участие
        requestRepository.save(RequestMapper.toRequest(prDto, event, user)); //сохраняем обновленную информацию в БД
    }
}
