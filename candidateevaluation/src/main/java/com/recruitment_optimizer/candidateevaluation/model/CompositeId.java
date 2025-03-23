package com.recruitment_optimizer.candidateevaluation.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class CompositeId {

    private String parentId;

    private String childId;

    public CompositeId () {}
    
    public CompositeId(String parentId, String childId) {
        this.parentId = parentId;
        this.childId = childId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CompositeId that = (CompositeId) obj;
        return parentId.equals(that.parentId) && childId.equals(that.childId);
    }

    @Override
    public int hashCode() {
        return 31 * parentId.hashCode() + childId.hashCode();
    }

}
