package io.tylerchesley.apped.debug.setting;

public class ImmuttableSetting extends Setting {

    private final String value;

    public ImmuttableSetting(String title, String value) {
        super(title);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
