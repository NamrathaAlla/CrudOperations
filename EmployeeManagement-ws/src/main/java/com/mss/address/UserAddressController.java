package com.mss.address;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserAddressController {

	@Autowired 
	UserAddressService addressService;
   
	// INSERT
		@PostMapping("/address")
		public Map<String, Object> insertingAddress(@RequestBody UserAddressEntity userAddressEntity) {

			return addressService.insertAddress(userAddressEntity);

		}

		// UPDATE
		@PutMapping("/address")
		public Map<String, Object> updateAddresss(@RequestBody UserAddressEntity userAddressEntity) {

			return addressService.updateAddress(userAddressEntity);

		}
		// DELETE
		@DeleteMapping("/address/{id}")
		public Map<String, Object> deletingaddress(@PathVariable int id) {

			return addressService.deleteAddress(id);

		}
		// SELECT ALL
		@GetMapping("/address")
		public Map<String, Object> retrievingAddress() {

			return addressService.retrieveAddress();

		}
		// SELECT BY ID
		@GetMapping("/address/{id}")
		public List<Map<String, Object>> selectingAddress(@PathVariable int id) {

			return addressService.selectAddress (id);

		}

	}


