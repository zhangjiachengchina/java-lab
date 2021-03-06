package com.zhang.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxiangdong on 2016/12/2.
 */
public abstract class LetterComposite {

    private List<LetterComposite> children = new ArrayList<>();

    public void add(LetterComposite letterComposite) {
        children.add(letterComposite);
    }

    public int count() {
        return children.size();
    }

    protected abstract void printThisBefore();

    protected abstract void printThisAfter();

    public void print() {
        printThisBefore();

        for (LetterComposite letterComposite : children) {
            letterComposite.print();
        }

        printThisAfter();
    }

}
