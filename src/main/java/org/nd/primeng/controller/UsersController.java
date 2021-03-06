package org.nd.primeng.controller;

import org.nd.primeng.model.User;
import org.nd.primeng.services.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class UsersController {

	private static Logger logger = LoggerFactory.getLogger(UsersController.class);

	@Autowired
	private UsersService usersService;

	@RequestMapping(value = "/paginate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<User> paginate(@RequestBody String primengRequestJson) {

		logger.info("----------------------------------------------------------------------");
		logger.info(primengRequestJson);
		logger.info("----------------------------------------------------------------------");

		return usersService.processPrimengRequest(primengRequestJson);
	}

}