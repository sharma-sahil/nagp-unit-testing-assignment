package com.nagarro.nagp;

import com.nagarro.nagp.domain.Category;

public class Inventory {

    private final Category category;

    public Inventory(final Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}
