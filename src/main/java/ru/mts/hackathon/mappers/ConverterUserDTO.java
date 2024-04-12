package ru.mts.hackathon.mappers;

import org.springframework.stereotype.Service;
import ru.mts.hackathon.domain.entities.UserEntity;
import ru.mts.hackathon.dto.UserDTO;
import ru.mts.hackathon.mappers.intrefaces.MapperUserDTO;

@Service
public class ConverterUserDTO implements MapperUserDTO {
    @Override
    public UserDTO toUserDTO(UserEntity user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(user.getId(), user.getRole(), user.getFirstName(), user.getLastName(), user.getFatherName(),
                user.getGrade(), user.getBirthDate(), user.getPhone(), user.getEmail(), user.getPassport());
    }
}
