package com.nagarro.nagp.handler;

import static org.hamcrest.CoreMatchers.equalTo;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.nagarro.nagp.Inventory;
import com.nagarro.nagp.domain.Category;
import com.nagarro.nagp.exception.InvalidRequestException;
import com.nagarro.nagp.repository.impl.DurableInventoryRepository;
import com.nagarro.nagp.repository.impl.FragileInventoryRepository;

@RunWith(MockitoJUnitRunner.class)
public class InventoryHandlerTest {

	@InjectMocks
	private InventoryHandler inventoryHandler;

	@Mock
	private FragileInventoryRepository fragileInventoryRepository;

	@Mock
	private DurableInventoryRepository durableInventoryRepository;

	@Test
	public void shouldReturnDurableInventoryObjectIfDurableInventoryObjectPassed() {
		Inventory expectedValue = new Inventory(Category.DURABLE);
		Mockito.when(this.durableInventoryRepository.save(expectedValue)).thenReturn(expectedValue);

		Inventory actualValue = this.inventoryHandler.createInventory(expectedValue);

		Mockito.verify(this.durableInventoryRepository).save(expectedValue);
		Mockito.verifyZeroInteractions(this.fragileInventoryRepository);

		MatcherAssert.assertThat(actualValue, equalTo(expectedValue));
		MatcherAssert.assertThat(actualValue.getCategory(), equalTo(expectedValue.getCategory()));

	}

	@Test
	public void shouldReturnFragileInventoryObjectIfFragileInventoryObjectPassed() {
		Inventory expectedValue = new Inventory(Category.FRAGILE);
		Mockito.when(this.fragileInventoryRepository.save(expectedValue)).thenReturn(expectedValue);

		Inventory actualValue = this.inventoryHandler.createInventory(expectedValue);

		Mockito.verify(this.fragileInventoryRepository).save(expectedValue);
		Mockito.verifyZeroInteractions(this.durableInventoryRepository);

		MatcherAssert.assertThat(actualValue, equalTo(expectedValue));
		MatcherAssert.assertThat(actualValue.getCategory(), equalTo(expectedValue.getCategory()));
	}

	@Test(expected = InvalidRequestException.class)
	public void shouldThrowExceptionIfNoCategoryPassedInInventory() {
		Inventory expectedValue = new Inventory(null);

		this.inventoryHandler.createInventory(expectedValue);

		Mockito.verifyZeroInteractions(this.durableInventoryRepository);
		Mockito.verifyZeroInteractions(this.fragileInventoryRepository);
	}

}
