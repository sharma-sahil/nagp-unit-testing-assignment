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
import com.nagarro.nagp.repository.impl.FragileInventoryRepository;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RepositoryHelper.class)
public class FragileInventoryRepositoryTest {

	@InjectMocks
	private FragileInventoryRepository repository;

	@Test
	public void shouldSaveFragileInventoryInstance() throws Exception {
		Inventory expectedValue = new Inventory(Category.FRAGILE);

		PowerMockito.mockStatic(RepositoryHelper.class);
		when(RepositoryHelper.saveFragile(Mockito.any(Inventory.class))).thenReturn(expectedValue);
		Inventory actualValue = this.repository.save(expectedValue);
		MatcherAssert.assertThat(actualValue, equalTo(expectedValue));
		MatcherAssert.assertThat(actualValue.getCategory(), equalTo(expectedValue.getCategory()));
	}

}
