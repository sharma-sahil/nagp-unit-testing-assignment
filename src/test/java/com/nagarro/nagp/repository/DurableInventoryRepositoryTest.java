package com.nagarro.nagp.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.nagarro.nagp.Inventory;
import com.nagarro.nagp.domain.Category;
import com.nagarro.nagp.repository.impl.DurableInventoryRepository;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RepositoryHelper.class)
public class DurableInventoryRepositoryTest {

	@InjectMocks
	private DurableInventoryRepository repository;

	@Test
	public void shouldSaveDurableInventoryInstance() throws Exception {
		Inventory expectedValue = new Inventory(Category.DURABLE);

		PowerMockito.mockStatic(RepositoryHelper.class);
		when(RepositoryHelper.saveDurable(Mockito.any(Inventory.class))).thenReturn(expectedValue);
		Inventory actualValue = this.repository.save(expectedValue);

		PowerMockito.verifyStatic();
		RepositoryHelper.saveDurable(expectedValue);

		MatcherAssert.assertThat(actualValue, equalTo(expectedValue));
		MatcherAssert.assertThat(actualValue.getCategory(), equalTo(expectedValue.getCategory()));
	}

}
