package io.tylerchesley.apped.debug.setting;

public interface HasChildren {

    int numChildren();

    Setting getChild(int position);

}
