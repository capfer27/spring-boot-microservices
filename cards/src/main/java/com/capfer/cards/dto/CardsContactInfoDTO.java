package com.capfer.cards.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@ConfigurationProperties(prefix = "cards")
public final class CardsContactInfoDTO {
    private final String message;
    private final Map<String, String> contactDetails;
    private final List<String> onCallSupport;

    public CardsContactInfoDTO(
            String message, Map<String, String> contactDetails, List<String> onCallSupport
    ) {
        this.message = message;
        this.contactDetails = contactDetails;
        this.onCallSupport = onCallSupport;
    }

    public String message() {
        return message;
    }

    public Map<String, String> contactDetails() {
        return contactDetails;
    }

    public List<String> onCallSupport() {
        return onCallSupport;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CardsContactInfoDTO) obj;
        return Objects.equals(this.message, that.message) &&
                Objects.equals(this.contactDetails, that.contactDetails) &&
                Objects.equals(this.onCallSupport, that.onCallSupport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, contactDetails, onCallSupport);
    }

    @Override
    public String toString() {
        return "CardsContactInfoDTO[" +
                "message=" + message + ", " +
                "contactDetails=" + contactDetails + ", " +
                "onCallSupport=" + onCallSupport + ']';
    }

}
