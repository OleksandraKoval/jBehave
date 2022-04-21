package session;

import lombok.Getter;

public enum SessionKey {
    REMEMBERED_VALUE_FOR_GOOGLE("REMEMBERED_VALUE_FOR_GOOGLE"),
    REMEMBERED_VALUE_FOR_BING("REMEMBERED_VALUE_FOR_GOOGLE");

    @Getter
    private final String value;

    SessionKey(String value) {
        this.value = value;
    }
}
