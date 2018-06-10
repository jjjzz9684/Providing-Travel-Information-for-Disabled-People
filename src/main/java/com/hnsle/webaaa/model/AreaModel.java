package com.hnsle.webaaa.model;

import java.util.List;

public class AreaModel {
    private List<String> id;
    private int totalCount;
    private List<String> content;

    public AreaModel(List<String> id, int totalCount, List<String> content) {
        this.id = id;
        this.totalCount = totalCount;
        this.content = content;
    }

    public AreaModel(){};

    public List<String> getId() {
        return id;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<String> getContent() {
        return content;
    }


}
