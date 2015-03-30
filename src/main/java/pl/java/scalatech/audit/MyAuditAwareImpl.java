package pl.java.scalatech.audit;

import org.springframework.data.domain.AuditorAware;

public class MyAuditAwareImpl  implements AuditorAware<AuditableUser>{

    //TODO inject SecurityContext 
    //TODO inject dao or service 
    
    @Override
    public AuditableUser getCurrentAuditor() {
        AuditableUser au = new AuditableUser();
        au.setUsername("przodownik");
        return au;
    }

}
