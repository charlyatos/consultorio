package net.atos.crojo.repo;

import org.springframework.stereotype.Repository;

import net.atos.crojo.model.User;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

}
