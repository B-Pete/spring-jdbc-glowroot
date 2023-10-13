/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mkyong;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mkyong.customer.Customer;
import com.mkyong.customer.CustomerRepository;

@Controller
class WelcomeController {

	private static final Logger log = LoggerFactory.getLogger(StartApplication.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	CustomerRepository customerRepository;

	@GetMapping("/")
	public String welcome() {
		run();
		return "welcome";
	}

	public void run(String... args) {

		log.info("StartApplication...");

		startCustomerApp();

	}

	// Tested with H2 database
	void startCustomerApp() {

		jdbcTemplate.execute("DROP TABLE customer IF EXISTS");
		jdbcTemplate.execute(
				"CREATE TABLE customer(" + "id SERIAL, name VARCHAR(255), age NUMERIC(2), created_date timestamp)");

		List<Customer> list = Arrays.asList(new Customer("Customer A", 19), new Customer("Customer B", 20),
				new Customer("Customer C", 21), new Customer("Customer D", 22));

		list.forEach(x -> {
			log.info("Saving...{}", x.getName());
			customerRepository.save(x);
		});

		log.info("[FIND_BY_ID]");
		log.info("{}", customerRepository.findByCustomerId(1L));
		log.info("{}", customerRepository.findByCustomerId2(2L));
		log.info("{}", customerRepository.findByCustomerId3(3L));

		log.info("[FIND_ALL]");
		log.info("{}", customerRepository.findAll());
		log.info("{}", customerRepository.findAll2());
		log.info("{}", customerRepository.findAll3());
		log.info("{}", customerRepository.findAll4());

		log.info("[FIND_NAME_BY_ID]");
		log.info("{}", customerRepository.findCustomerNameById(4L));

		log.info("[COUNT]");
		log.info("{}", customerRepository.count());

	}
}
