package io.tylerchesley.apped.debug.setting;

public class ArrayChoiceProvider<T> implements ChoiceProvider<T> {

    private final T[] choices;

    public ArrayChoiceProvider(T[] choices) {
        this.choices = choices;
    }

    @Override
    public int getCount() {
        return choices.length;
    }

    @Override
    public T getItem(int position) {
        return choices[position];
    }

    @Override
    public int positionOf(T item) {
        for (int i = 0; i < choices.length; i++) {
            if (choices[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

}
