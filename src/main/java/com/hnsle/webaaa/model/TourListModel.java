package com.hnsle.webaaa.model;

import java.util.ArrayList;
import java.util.List;

public class TourListModel {
    List<TourModel> items= new ArrayList<>();

    public List<TourModel> getItems() {
        return items;
    }

    public TourListModel(List<TourModel> items) {
        this.items = items;
    }
}
