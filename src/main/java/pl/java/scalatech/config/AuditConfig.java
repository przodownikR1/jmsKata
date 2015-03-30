package pl.java.scalatech.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import pl.java.scalatech.audit.MyAuditAwareImpl;

@Configuration
@EnableJpaAuditing
@Slf4j
public class AuditConfig {
    @Bean
    public AuditorAware<String> yourAuditorAwarebean() {
      log.info("+++  init audit");  
      return new MyAuditAwareImpl();
    }
}

