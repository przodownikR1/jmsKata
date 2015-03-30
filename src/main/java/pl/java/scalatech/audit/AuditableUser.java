package pl.java.scalatech.audit;

import lombok.Data;

import org.springframework.data.jpa.domain.AbstractAuditable;

import pl.java.scalatech.entity.User;
@Data
public class AuditableUser extends AbstractAuditable<User, Long> {
private static final long serialVersionUID = 1L;
private String username;

}