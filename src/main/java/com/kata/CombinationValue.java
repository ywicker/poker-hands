package com.kata;

public enum CombinationValue {
    HIGHT_CARDS("hight card"),
    PAIRS("pair"),
    THREE_OF_KIND("three of kind"),
    STRAIGTH("straight"),
    FLUSH("flush"),
    FULL_HOUSSE("full house"),
    FOUR_OF_KIND("four of kind"),
    STRAIGTH_FLUSH("straight flush");

    public final String label;
    CombinationValue(String label) {
        this.label = label;
    }
}
