package io.tylerchesley.apped.debug.setting;

public interface ChoiceProvider<C> {

    int getCount();

    C getItem(int position);

    int positionOf(C item);

}
