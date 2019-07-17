package com.nagarro.nagp.handler;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.nagarro.nagp.Inventory;
import com.nagarro.nagp.domain.Category;
import com.nagarro.nagp.repository.impl.DurableInventoryRepository;
import com.nagarro.nagp.repository.impl.FragileInventoryRepository;

@RunWith(Parameterized.class)
public class InventoryHandlerParameterizedTest {

	private Category input;

	private Category expectedOutput;

	private InventoryHandler handler;

	@Mock
	private FragileInventoryRepository fragileInventoryRepository;

	@Mock
	private DurableInventoryRepository durableInventoryRepository;

	public InventoryHandlerParameterizedTest(Category input, Category expectedOutput) {
		this.input = input;
		this.expectedOutput = expectedOutput;
	}

	@Before
	public void setUp() {
		initMocks(this);
		this.handler = new InventoryHandler(fragileInventoryRepository, durableInventoryRepository);
	}

	@Parameterized.Parameters
	public static Collection Categories() {
		return Arrays.asList(
				new Object[][] { { Category.DURABLE, Category.DURABLE }, { Category.FRAGILE, Category.FRAGILE } });
	}

	@Test
	public void shouldReturnInventoryObjectWithSameCategory() {

		Inventory expected = new Inventory(input);
		Mockito.when(this.durableInventoryRepository.save(expected)).thenReturn(expected);
		Mockito.when(this.fragileInventoryRepository.save(expected)).thenReturn(expected);

		Inventory actual = this.handler.createInventory(expected);
		assertThat(actual.getCategory(), equalTo(expectedOutput));
	}

}
