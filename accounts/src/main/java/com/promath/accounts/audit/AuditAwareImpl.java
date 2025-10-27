package com.promath.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component(value = "auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    /**
     * Returns the current auditor of the application.
     * @return the current auditor.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        /**
         * Here we're returning the string ACCOUNTS_MS (ACCOUNTS_MICROSERVICE), so the spring data jpa can leverage who's
         * the current auditor when populating the fields: createdBy and updatedBy whenever is needed.
         */
        return Optional.of("ACCOUNTS_MS");
    }
}
