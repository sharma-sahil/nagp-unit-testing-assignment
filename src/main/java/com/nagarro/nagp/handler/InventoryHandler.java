package com.nagarro.nagp.handler;

import com.nagarro.nagp.Inventory;
import com.nagarro.nagp.domain.Category;
import com.nagarro.nagp.exception.InvalidRequestException;
import com.nagarro.nagp.repository.IInventoryRepository;
import com.nagarro.nagp.repository.impl.DurableInventoryRepository;
import com.nagarro.nagp.repository.impl.FragileInventoryRepository;

public class InventoryHandler {

	private final IInventoryRepository fragileInventoryRepository;
	private final IInventoryRepository durableInventoryRepository;

	public InventoryHandler(final FragileInventoryRepository fragileInventoryRepository,
			final DurableInventoryRepository durableInventoryRepository) {
		this.fragileInventoryRepository = fragileInventoryRepository;
		this.durableInventoryRepository = durableInventoryRepository;
	}

	public Inventory createInventory(final Inventory inventory) {

		if (inventory.getCategory() == Category.FRAGILE) {
			return fragileInventoryRepository.save(inventory);
		} else if (inventory.getCategory() == Category.DURABLE) {
			return durableInventoryRepository.save(inventory);
		} else {
			throw new InvalidRequestException("Wrong inventory type");
		}
	}

}
