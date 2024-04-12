package com.example.OnlineSeatBook.util;

import com.example.OnlineSeatBook.model.Seat;

import java.util.ArrayList;
import java.util.Comparator;

public class SortByIndex {

    public static void sortByIndex(ArrayList<Seat> list) {
        list.sort(new Comparator<Seat>() {
            @Override
            public int compare(Seat o1, Seat o2) {
                return Integer.compare(o1.getSeatIndex(), o2.getSeatIndex());
            }
        });
    }
}
