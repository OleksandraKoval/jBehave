package generic;

import lombok.Getter;

@Getter
class UiName {

    private String name;

    UiName(final String name) {
        this.name = name;
    }
}
