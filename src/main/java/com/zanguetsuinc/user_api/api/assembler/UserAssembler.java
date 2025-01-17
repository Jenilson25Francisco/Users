package com.zanguetsuinc.user_api.api.assembler;

import com.zanguetsuinc.user_api.api.dto.UserDto;
import com.zanguetsuinc.user_api.api.response.UserResponse;
import com.zanguetsuinc.user_api.domain.model.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserAssembler {

    private ModelMapper modelMapper;

    public UserDto toDto(User user){
        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> toCollectionModel(List<User> users){
        return users.stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    public User toResponse(UserResponse userResponse){
        return modelMapper.map(userResponse, User.class);
    }

}
