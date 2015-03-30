package pl.java.scalatech.audit;

import org.springframework.data.domain.AuditorAware;

public class MyAuditAwareImpl implements AuditorAware<String> {

    // TODO inject SecurityContext
    // TODO inject dao or service

    @Override
    public String getCurrentAuditor() {
        /*
         * AuditableUser au = new AuditableUser();
         * au.setUsername("przodownik");
         */

        String userName = "przodownik";//= SecurityUtils.getCurrentLogin();
        return (userName != null ? userName : SecurityAccount.SYSTEM_ACCOUNT.name());

    }

}
