package com.locadora.jpa.exemplos_isolados.orphanRemoval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/owners")
public class OwnerResource {

	@Autowired
	private OwnerDAO ownerDAO;

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return ResponseEntity.ok(ownerDAO.findById(id));
	}

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(ownerDAO.findAll());
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Owner t) {
		return ResponseEntity.ok(ownerDAO.save(t));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Owner t) {
		return ResponseEntity.ok(ownerDAO.update(id, t));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		ownerDAO.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
