package net.atos.crojo.dto;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseDTO implements Serializable {
    
    private Long id;
}