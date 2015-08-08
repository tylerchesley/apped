package io.tylerchesley.apped.debug.setting;

import java.util.ArrayList;
import java.util.List;

public class Section extends Setting implements HasChildren {

    private final List<Setting> children;

    public Section(String title) {
        this(title, new ArrayList<Setting>());
    }

    public Section(String title, List<Setting> children) {
        super(title);
        this.children = children;
    }

    @Override
    public int numChildren() {
        return children.size();
    }

    @Override
    public Setting getChild(int position) {
        return children.get(position);
    }

}
