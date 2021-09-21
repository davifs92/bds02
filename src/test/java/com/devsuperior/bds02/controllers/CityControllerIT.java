package com.devsuperior.bds02.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.devsuperior.bds02.services.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CityControllerIT {

	@Autowired
	private MockMvc mockMvc;

	private Long independentId;
	private Long nonExistingId;

	@BeforeEach
	void setUp () throws Exception {
		 independentId = 5L;
		 nonExistingId = 50L;

	}
	@Test
	public void deleteShouldReturnNoContentWhenIndependentId() throws Exception {		
		

		ResultActions result =
				mockMvc.perform(delete("/cities/{id}", independentId));

		result.andExpect(status().isNoContent());
	}

	@Test
	public void deleteShouldReturnNotFoundWhenNonExistingId() throws Exception {		


		ResultActions result =
				mockMvc.perform(delete("/cities/{id}", nonExistingId));

		result.andExpect(status().isNotFound());
	}

	@Test
	public void findAllShouldReturnAllResourcesSortedByName() throws Exception {
		
		ResultActions result =
				mockMvc.perform(get("/cities")
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$[0].name").value("Belo Horizonte"));
		result.andExpect(jsonPath("$[1].name").value("Belém"));
		result.andExpect(jsonPath("$[2].name").value("Brasília"));
	}
}
