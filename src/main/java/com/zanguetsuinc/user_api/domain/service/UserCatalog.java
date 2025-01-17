package com.zanguetsuinc.user_api.domain.service;

import com.zanguetsuinc.user_api.domain.model.User;
import com.zanguetsuinc.user_api.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCatalog {

    private final UserRepository userRepository;

    public User get(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("cliente nao encontrado"));
    }

    @Transactional
    public User creat(User user){

//        boolean usedEmail = userRepository.findByEmail(user.getEmail())
//                .stream()
//                .anyMatch(existClient -> !existClient.equals(user));

//        if (usedEmail){
//            throw new RuntimeException("este email ja existe");
//        }

        return userRepository.save(user);
    }

    @Transactional
    public void remove(Long userId){
        userRepository.deleteById(userId);
    }

}
