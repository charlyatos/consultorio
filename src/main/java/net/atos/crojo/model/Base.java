package net.atos.crojo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Base implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @CreationTimestamp
    @Column(name = "CreationTime", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date creationTime;

    @UpdateTimestamp
    @Column(name = "ModificationTime")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date modificationTime;
    
    
}