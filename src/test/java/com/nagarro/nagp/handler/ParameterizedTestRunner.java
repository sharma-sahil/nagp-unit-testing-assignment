package com.nagarro.nagp.handler;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class ParameterizedTestRunner {

	public static void main(String[] args) {

		Result result = JUnitCore.runClasses(InventoryHandlerParameterizedTest.class);

		System.out.println(result.getFailureCount());

		for (Failure failure : result.getFailures()) {
			System.out.println(" failure -> " + failure.toString());
		}

		System.out.println(result.wasSuccessful());
	}

}
