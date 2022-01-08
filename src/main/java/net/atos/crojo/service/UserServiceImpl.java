package net.atos.crojo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.atos.crojo.model.User;
import net.atos.crojo.repo.BaseRepository;
import net.atos.crojo.repo.UserRepository;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    @Autowired
    private UserRepository autorRepository;

    public UserServiceImpl(BaseRepository<User, Long> baseRepository) {
        super(baseRepository);
    }
}