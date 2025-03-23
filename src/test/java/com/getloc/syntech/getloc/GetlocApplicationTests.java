package com.getloc.syntech.getloc;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import com.getloc.syntech.getloc.exceptions.InvalidArgumentException;
// import com.getloc.syntech.getloc.exceptions.AlreadyExistsException;
// import com.getloc.syntech.getloc.exceptions.InvalidArgumentException;
import com.getloc.syntech.getloc.exceptions.NotFoundException;
import com.getloc.syntech.getloc.helper.UserHelper;
import com.getloc.syntech.getloc.requests.devices.NewDevice;
import com.getloc.syntech.getloc.requests.devices.UpdateDeviceName;
import com.getloc.syntech.getloc.requests.users.LoginRequest;
import com.getloc.syntech.getloc.responsesDTO.AllUsersDTO;
import com.getloc.syntech.getloc.responsesDTO.LoginDTO;
import com.getloc.syntech.getloc.responsesDTO.devicesDTO.RegisterDeviceDTO;
import com.getloc.syntech.getloc.services.DeviceService;
// import com.getloc.syntech.getloc.requests.users.SignupBody;
// import com.getloc.syntech.getloc.exceptions.AlreadyExistsException;
// import com.getloc.syntech.getloc.requests.users.SignupBody;
// import com.getloc.syntech.getloc.requests.users.UpdateBody;
// import com.getloc.syntech.getloc.responsesDTO.UserDTO;
import com.getloc.syntech.getloc.services.UserService;

@SpringBootTest
class GetlocApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private UserHelper userHelper;

	@Autowired
	private DeviceService deviceService;

	// @Test
	// void testPostUser() {
	// 	SignupBody signupBody = new SignupBody("Wesley Teste 1", "meuemail@gmail.com", "12345678");

	// 	assertTrue(userService.postUser(signupBody) instanceof UserDTO, "Tipo retornado não é o esperado");
	// }

	// @Test
	// void testUpdateUserName() {
	// 	UpdateBody updateBody = new UpdateBody("Wesley alterado", null, null);

	// 	assertTrue(userService.updateUserName(updateBody, "67d62925f606f375e10a992e") instanceof UserDTO, "Tipo retornado não é o esperado");
	// }

	// @Test
	// void testUserEmail() {
	// 	UpdateBody updateBody = new UpdateBody(null, "novoemailgmail.com", null);

	// 	assertThrows(InvalidArgumentException.class, () -> {
	// 		userService.updateUserEmail(updateBody, "67d62925f606f375e10a992e");
	// 	});
	// }

	// @Test
	// void testUpdateUserPassword() {
	// 	UpdateBody updateBody = new UpdateBody(null, null, "1234567");

	// 	assertThrows(InvalidArgumentException.class, () -> {
	// 		userService.updateUserPassword(updateBody, "67d62925f606f375e10a992e");
	// 	});
	// }


	// Sempre que usar esse teste desativar os outros...
	// @Test
	// void testDeleteUserById() {
	// 	assertEquals("Sucesso!!!", userService.deleteUserById("67d654a6d893cb02df7d690a"), "Erro ao deletar usuário");
	// }

	// @Test
	// void testGetUserById() {
	// 	assertThrows(NotFoundException.class, () -> {
	// 		userService.getUserById("67d62925f606f375e10a992e");
	// 	});
	// }

	// @Test
	// void testGetAllUsers() {
	// 	assertTrue(userService.getAllUsers() instanceof List<AllUsersDTO>, "Tipo retornado não é o esperado");
	// }

	// @Test
	// void testIsValidUser() {
	// 	assertTrue(userHelper.isValidUser("67d71dcf233a98028e19d463", "67d71dcf233a98028e19d463"), "Tipo retornado não é o esperado");
	// }

	// @Test
	// void testLogin() {
	// 	LoginRequest loginRequest = new LoginRequest("suportesyntech@gmail.com", "12345678");
	// 	assertTrue(userService.loginService(loginRequest) instanceof LoginDTO, "Tipo retornado não é o esperado!!!");
	// }

	// @Test
	// void testLoginException() {
	// 	LoginRequest loginRequest = new LoginRequest("suportesyntech@gmail.com", "12345678");
	// 	assertThrows(InvalidArgumentException.class, () -> {
	// 		userService.loginService(loginRequest);
	// 	});
	// }

	// @Test
	// void testPostDevice() {
	// 	NewDevice newDevice = new NewDevice("Notebook-HP-1000", "Rua cientista albert sabin", new GeoJsonPoint(-34.6037, -58.3816));

	// 	assertEquals("Sucesso!", deviceService.postDevice(newDevice), "Algo não deu certo...");
	// }

	@Test
	void testUpdateDeviceName() {
		UpdateDeviceName deviceName = new UpdateDeviceName("HP-1000-2025");

		assertEquals("Sucesso!", deviceService.updateDeviceName("67e0707d15521a7c9f534e51", deviceName), "Algo não deu certo...");
	}
}
