package com.nagarro.nagp;

import java.util.Objects;

import com.nagarro.nagp.exception.InvalidRequestException;
import com.nagarro.nagp.handler.InventoryHandler;

public class InventoryResource {

	private final InventoryHandler handler;

	public InventoryResource(InventoryHandler handler) {
		this.handler = handler;
	}

	public Inventory createInventory(final Inventory inventory) {

		if (Objects.nonNull(inventory)) {
			return handler.createInventory(inventory);
		}

		throw new InvalidRequestException("Inventory must not be null");
	}

}
