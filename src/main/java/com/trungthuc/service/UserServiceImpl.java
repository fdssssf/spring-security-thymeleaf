package com.trungthuc.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trungthuc.entity.Role;
import com.trungthuc.entity.User;
import com.trungthuc.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);

	}

	@Override
	public void save(User user, String[] roles) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		Set<Role> roleset = new HashSet<Role>();
		for (String roleu : roles) {
			roleset.add(new Role(roleu));
		}
		user.setRoles(roleset);
		userRepository.save(user);
	}

}
