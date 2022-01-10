package net.atos.crojo.service;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import net.atos.crojo.config.ResourceNotFoundException;
import net.atos.crojo.model.Base;
import net.atos.crojo.repo.BaseRepository;

public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements BaseService<E, ID> {
    protected BaseRepository<E, ID> baseRepository;
    
    @Autowired
    ModelMapper mmap;
    
    public BaseServiceImpl(BaseRepository<E, ID> baseRepository){
        this.baseRepository = baseRepository;
    }
    @Async("asyncTask")
    @Override
    public  CompletableFuture<List<E>> findAll() {
        
    	System.out.println( Thread.currentThread().getName());
            return CompletableFuture.completedFuture(baseRepository.findAll());
       
    }
    @Async("asyncTask")
    @Override
    public CompletableFuture<E> findById(ID id) {
            
			return CompletableFuture.completedFuture(
					baseRepository.findById(id).orElseThrow(() -> 
					new ResourceNotFoundException("Entity", "Id", id))
					);
        
    }
    @Async("asyncTask")
    @Override
    public CompletableFuture<E> save(E entity)  {
        
           
            return CompletableFuture.completedFuture(baseRepository.save(entity));
       
    }
    @Async("asyncTask")
    @Override
    public CompletableFuture<E> update(ID id, E entity)  {
            E existentEntity = baseRepository.findById(id).orElseThrow(() -> 
			new ResourceNotFoundException("Entity", "Id", id));
            
            entity.setId(existentEntity.getId());
            
            mmap.map(entity,existentEntity);
            
            return CompletableFuture.completedFuture( baseRepository.save(existentEntity) );
       
    }
    @Async("asyncTask")
    @Override
    public CompletableFuture<Boolean> delete(ID id) {
            if (baseRepository.existsById(id)){
                baseRepository.deleteById(id);
                return CompletableFuture.completedFuture(true);
            } else {
            	return CompletableFuture.completedFuture(false);
            }
    }
}