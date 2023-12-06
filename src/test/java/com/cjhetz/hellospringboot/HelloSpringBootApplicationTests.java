package com.cjhetz.hellospringboot;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.cjhetz.hellospringboot.repository.AssetRepository;

// https://stackoverflow.com/questions/66600210/illegalstate-failed-to-load-applicationcon
//@Testcontainers
@DataJpaTest
public class HelloSpringBootApplicationTests {

	@Autowired private AssetRepository assetRepository;

	@Test
	public void contextLoads() {

	}

	@Test
	public void pass() {
		assertTrue(true);
		assertNotNull(assetRepository);
	}
}
