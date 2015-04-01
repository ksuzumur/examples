package org.example;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity(difficultyComparatorClass = ItemDifficultyComparator.class)
public class Item {
    private String name;
    private int price;
    private Bucket bucket;

    public Item() {
    }

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @PlanningVariable(valueRangeProviderRefs = { "bucketList" })
    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isInCart() {
        return bucket == null ? false : bucket.isCart();
    }
    
    public String toString() {
        return "item:" + name;
    }

}
