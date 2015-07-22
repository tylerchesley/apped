package io.tylerchesley.apped.debug.setting;

public abstract class SingleChoiceSetting extends Setting {

    public SingleChoiceSetting(String title) {
        super(title);
    }

    public abstract int getCount();

    public abstract String getLabel(int position);

    public abstract int getSelectedItem();
    public abstract void setSelectedItem(int position);

}
