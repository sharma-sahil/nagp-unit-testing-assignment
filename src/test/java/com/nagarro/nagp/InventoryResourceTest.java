package com.nagarro.nagp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.nagarro.nagp.domain.Category;
import com.nagarro.nagp.exception.InvalidRequestException;
import com.nagarro.nagp.handler.InventoryHandler;

@RunWith(MockitoJUnitRunner.class)
public class InventoryResourceTest {

	@InjectMocks
	private InventoryResource inventoryResource;

	@Mock
	private InventoryHandler handler;

	@Test(expected = InvalidRequestException.class)
	public void shouldThrowInvalidRequestExceptionIfNullObjectPassed() {
		this.inventoryResource.createInventory(null);
		Mockito.verifyZeroInteractions(this.handler);
	}

	@Test
	public void shouldCreateInventoryObject() {

		Inventory expectedValue = new Inventory(Category.DURABLE);
		when(this.handler.createInventory(expectedValue)).thenReturn(expectedValue);

		Inventory actualValue = this.inventoryResource.createInventory(expectedValue);

		Mockito.verify(this.handler).createInventory(expectedValue);

		MatcherAssert.assertThat(actualValue, equalTo(expectedValue));
		MatcherAssert.assertThat(actualValue.getCategory(), equalTo(expectedValue.getCategory()));

	}

}
