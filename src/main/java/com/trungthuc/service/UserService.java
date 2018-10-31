package com.trungthuc.service;

import com.trungthuc.entity.User;

public interface UserService {
User findByUsername(String username);
void save(User user,String [] role );
}
