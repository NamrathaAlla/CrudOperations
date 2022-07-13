package com.mss.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class UserService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	// INSERTION USING NAMED PARAMETER TEMPLATE
	public Map<String, Object> insertUser(UserEntity userEntity) {

		String name = userEntity.getName();

		String email = userEntity.getEmail();

		String password = userEntity.getPassword();

		int age = userEntity.getAge();

		// System.out.println("age " + age);

		Map<String, Object> response = new HashMap<>();

		try {

			if (age < 18) {

				response.put("message", "AGE MUST BE GREATER THAN OR EQUAL TO 18!!");

				return response;
			}

			String query = "INSERT INTO user (name,email,password,age)VALUES(:tempName,:tempEmail,:tempPassword,:tempAge)";

			SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("tempName", name)
					.addValue("tempEmail", email).addValue("tempPassword", password).addValue("tempAge", age);

			int result = namedParameterJdbcTemplate.update(query, sqlParameterSource);

			if (result == 1) {

				response.put("message", "successfully inserted");
			} else {
				response.put("message", "SOMETHING WENT WRONG");
			}

		}

		catch (	Exception ex) {
			ex.printStackTrace();
		}

		return response;

	}

	// UPDATION

	public Map<String, Object> updateUser(UserEntity userEntity) {
		int id = userEntity.getId();

		String name = userEntity.getName();

		Map<String, Object> response = new HashMap<>();

		try {
			String query = "UPDATE user SET name =:tempName  WHERE id = :tempId";

			SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("tempName", name)
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

	public Map<String, Object> deleteUser(int id) {

		Map<String, Object> response = new HashMap<String, Object>();

		try {
			String query = "DELETE FROM user WHERE id = :tempId";

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

	public Map<String, Object> retrieveUser() {

		Map<String, Object> response = new HashMap<>();
		List<Map<String, Object>> finalList = new ArrayList<Map<String, Object>>();
		try {

			String query = "SELECT id,name,email,password,age,createatdate,DATE_FORMAT(createatdate, \"%d-%m-%Y\") as formatedDate FROM user ";

			List<Map<String, Object>> userList = jdbcTemplate.queryForList(query);

			for (Map<String, Object> userMap : userList) {

				int id = (Integer) userMap.get("id");
				String name = (String) userMap.get("name");
				String email = (String) userMap.get("email");
				String password = (String) userMap.get("password");
				int age = (Integer) userMap.get("age");
				LocalDateTime createatdate = (LocalDateTime) userMap.get("createatdate");
				// String convertedCreatedDate = convertDate(createatdate);
				String formatedDate = (String) userMap.get("formatedDate");
				System.out.println("formatedDate -- " + formatedDate);

				Map<String, Object> tempMap = new HashMap<>();

				tempMap.put("id", id);
				tempMap.put("name", name);
				tempMap.put("email", email);
				tempMap.put("password", password);
				tempMap.put("age", age);
				tempMap.put("createatdate", createatdate);

				// tempMap.put("createatdate", convertedCreatedDate);

				finalList.add(tempMap);
				System.out.println(id + " " + name + " " + email + " " + password + " " + age + " " + createatdate);

			}

			response.put("userList", finalList);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;

	}

	// RETREVIAL BY ID

	public List<Map<String, Object>> selectUser(int id) {

		String query = "SELECT id,name,email,password,age FROM user WHERE id = :tempId";

		SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("tempId", id);

		return namedParameterJdbcTemplate.queryForList(query, sqlParameterSource);

	}

//	public String convertDate(LocalDateTime createatdate)
//	{
//		String responseDate = null;
//		
//		
//	    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//	   
//	    responseDate = createatdate.format(formatters);
//		
//	    return responseDate;
//		
//	}

}
