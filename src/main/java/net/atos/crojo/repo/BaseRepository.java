package net.atos.crojo.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import net.atos.crojo.model.Base;

@NoRepositoryBean
public interface  BaseRepository <E extends Base, ID extends Serializable> extends JpaRepository<E, ID> {

}
