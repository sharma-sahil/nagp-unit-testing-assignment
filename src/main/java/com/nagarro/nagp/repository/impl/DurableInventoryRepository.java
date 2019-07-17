package com.nagarro.nagp.repository.impl;

import com.nagarro.nagp.Inventory;
import com.nagarro.nagp.repository.IInventoryRepository;
import com.nagarro.nagp.repository.RepositoryHelper;

public class DurableInventoryRepository implements IInventoryRepository {

	@Override
	public Inventory save(final Inventory inventory) {
		return RepositoryHelper.saveDurable(inventory);
	}
}
