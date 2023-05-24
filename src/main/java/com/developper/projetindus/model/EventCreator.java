package com.developper.projetindus.model;

import com.developper.projetindus.entity.CategoryEntity;

import java.util.List;

public class EventCreator {

    public List<CategoryEntity> categoryEntityList;
    public long infrastructureId;

    public EventCreator() {
    }

    public List<CategoryEntity> getCategoryEntityList() {
        return categoryEntityList;
    }

    public void setCategoryEntityList(List<CategoryEntity> categoryEntityList) {
        this.categoryEntityList = categoryEntityList;
    }

    public long getInfrastructureId() {
        return infrastructureId;
    }

    public void setInfrastructureId(long infrastructureId) {
        this.infrastructureId = infrastructureId;
    }
}
