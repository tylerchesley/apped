package io.tylerchesley.apped.debug.setting;

public class EnumChoiceFormatter<C extends Enum<C>> implements ChoiceFormatter<C> {

    @Override
    public String format(C item) {
        return item.name();
    }

}
