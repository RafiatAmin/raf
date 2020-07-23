package com.etz.raf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import org.springframework.web.client.ResourceAccessException;

@RestController
@RequestMapping("/api/rafiat")
public class RafApplicationController {

	@Autowired
	private RafApplicationRepository rafapplicationrepository;

	@GetMapping("/getworkers")
	public List<RafApplicationModel> getAllWorkers() {
		return rafapplicationrepository.findAll();
	}

	@GetMapping("/worker/{id}")
	public ResponseEntity<RafApplicationModel> getWorkersById(@PathVariable(value = "id") Long workerId)

			throws ResourceAccessException {

		RafApplicationModel worker = rafapplicationrepository.findById(workerId)
				.orElseThrow(() -> new ResourceAccessException("User not found on :: " + workerId));

		return ResponseEntity.ok().body(worker);

	}

	@PostMapping(value = "/workers")
	public RafApplicationModel createWorker(@Valid @RequestBody RafApplicationModel worker) {

		return rafapplicationrepository.save(worker);
	}

	@PutMapping("/workers/{id}")
	public ResponseEntity<RafApplicationModel> updateWorker(@PathVariable(value = "id") Long workerId, 
			@Valid @RequestBody RafApplicationModel newRecord) throws ResourceAccessException {
		
		RafApplicationModel oldRecord = rafapplicationrepository.findById(workerId)
				.orElseThrow(() -> new ResourceAccessException("User not found on :: " + workerId));

		oldRecord.setFirstName(newRecord.getFirstName());

		oldRecord.setEmail(newRecord.getEmail());

		oldRecord.setGender(newRecord.getGender());

		oldRecord.setLastName(newRecord.getLastName());

		final RafApplicationModel updatedWorker = rafapplicationrepository.save(oldRecord);
		return ResponseEntity.ok(updatedWorker);

	}

	@DeleteMapping("/worker/{id}")

	public Map<String, Boolean> deleteWorker(@PathVariable(value = "id") Long workerId) throws Exception {

		RafApplicationModel rafapplicationmodel = rafapplicationrepository.findById(workerId)

				.orElseThrow(() -> new ResourceAccessException("Worker not found on :: " + workerId));

		rafapplicationrepository.delete(rafapplicationmodel);

		Map<String, Boolean> response = new HashMap<>();

		response.put("deleted", Boolean.TRUE);

		return response;

	}

}
