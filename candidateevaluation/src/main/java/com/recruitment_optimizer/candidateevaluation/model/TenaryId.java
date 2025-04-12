package com.recruitment_optimizer.candidateevaluation.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class TenaryId {
    
    private String parentId;

    private CompositeId childId;
    public TenaryId() {}

    public TenaryId(String entity1, CompositeId childId) {
        this.parentId = entity1;
        this.childId = childId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String entity1) {
        this.parentId = entity1;
    }

    public CompositeId getChildId() {
        return childId;
    }

    public void setChildId(CompositeId childId) {
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
        TenaryId that = (TenaryId) obj;
        return parentId.equals(that.parentId) && childId.equals(that.childId);
    }

    @Override
    public int hashCode() {
        return 31 * parentId.hashCode() + childId.hashCode();
    }


}
