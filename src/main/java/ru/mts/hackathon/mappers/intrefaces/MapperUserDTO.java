package ru.mts.hackathon.mappers.intrefaces;

import ru.mts.hackathon.domain.entities.UserEntity;
import ru.mts.hackathon.dto.UserDTO;

public interface MapperUserDTO {
    UserDTO toUserDTO(UserEntity user);
}
