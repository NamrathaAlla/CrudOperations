package com.mss.address;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;


@Service
public class UserAddressService {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	JdbcTemplate jdbcTemplate;

	// INSERTION USING NAMED PARAMETER TEMPLATE
	public Map<String, Object> insertAddress(UserAddressEntity userAddressEntity) {
         int result=0;
		String userid = userAddressEntity.getUserid();

		String address = userAddressEntity.getAddress();

		String state = userAddressEntity.getState();

		int pincode = userAddressEntity.getPincode();

		Map<String, Object> response = new HashMap<>();

		try {
			String selectQuery = "SELECT address from address";

			List<Map<String, Object>> addresslist = jdbcTemplate.queryForList(selectQuery);

			for (Map<String, Object> addressMap : addresslist) {
				String city = (String) addressMap.get("address");

				if (city.equals(address)) {
					response.put("message", "ADDRESS ALREADY EXISTED");

					//return response;
				}
			
			}
			

			String query = "INSERT INTO address (userid,address,state,pin)VALUES(:tempUserid,:tempAddress,:tempState,:tempPincode)";

			SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("tempUserid", userid)
					.addValue("tempAddress", address).addValue("tempState", state).addValue("tempPincode", pincode);

			 result = namedParameterJdbcTemplate.update(query, sqlParameterSource);
				

			if (result == 1) {

				response.put("message", "successfully inserted");
			} else {
				response.put("message", "SOMETHING WENT WRONG");
			}
			}
		

		catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;

	}
	// UPDATION

	public Map<String, Object> updateAddress(UserAddressEntity userAddressEntity) {
		int id = userAddressEntity.getId();

		String address = userAddressEntity.getAddress();

		Map<String, Object> response = new HashMap<>();

		try {

			String query = "UPDATE address SET address=:tempaddress WHERE id = :tempId";

			SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("tempaddress", address)
					.addValue("tempId", id);

			int result = namedParameterJdbcTemplate.update(query, sqlParameterSource);

			if (result > 0) {
				response.put("message", "Updation success");
			}

			else {
				response.put("message", "SOMETHING WENT WRONG");
			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;

	}
	// DELETION

	public Map<String, Object> deleteAddress(int id) {

		Map<String, Object> response = new HashMap<String, Object>();

		try {
			String query = "DELETE FROM address WHERE id = :tempId";

			SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("tempId", id);

			int result = namedParameterJdbcTemplate.update(query, sqlParameterSource);

			if (result > 0) {
				response.put("message", "Deletion success");
			} else {
				response.put("message", "SOMETHING WENT WRONG");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;

	}

	// RETRIEVING ALL BY FOR EACH,

	public Map<String, Object> retrieveAddress() {

		Map<String, Object> response = new HashMap<>();

		try {
			List<Map<String, Object>> finalList = new ArrayList<Map<String, Object>>();

			String query = "SELECT userid,address,state,pin,createatdate FROM address ";

			List<Map<String, Object>> addressList = jdbcTemplate.queryForList(query);

			for (Map<String, Object> userMap : addressList) {

				String userid = (String) userMap.get("userid");
				String address = (String) userMap.get("address");
				String state = (String) userMap.get("state");
				int pin = (Integer) userMap.get("pin");
				LocalDateTime createatdate = (LocalDateTime) userMap.get("createatdate");

				Map<String, Object> tempMap = new HashMap<>();

				tempMap.put("userid", userid);
				tempMap.put("address", address);
				tempMap.put("state", state);
				tempMap.put("pin", pin);
				tempMap.put("createatdate", createatdate);

				finalList.add(tempMap);

				System.out.println(userid + " " + address + " " + state + " " + pin + " " + createatdate);

			}

			response.put("userAddressList", finalList);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;

	}
	// RETREVIAL BY ID

	public List<Map<String, Object>> selectAddress(int id) {

		String query = "SELECT userid,address,state,pin,createatdate FROM address  WHERE id = :tempId";

		SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("tempId", id);

		return namedParameterJdbcTemplate.queryForList(query, sqlParameterSource);

	}

}
