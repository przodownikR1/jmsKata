package pl.java.scalatech.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class User implements Serializable {
    @Id
    private Long id;
    
    private String login;
    private String passwd;
    private String name;
    private BigDecimal salary;
    
}
