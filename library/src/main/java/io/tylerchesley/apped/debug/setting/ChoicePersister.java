package io.tylerchesley.apped.debug.setting;

public interface ChoicePersister<C> {

    C getSelectedItem();

    void setSelectedItem(C item);

}
