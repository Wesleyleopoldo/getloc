package com.getloc.syntech.getloc;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.getloc.syntech.getloc.utils.Utils;

@SpringBootTest
class GetlocApplicationTests {

	@Test
	void testGenerateRandomBinaryByUUID(){
		byte[] binaryCreated = Utils.create().generateRandomUUID().generateRandomBinaryByUUID();

		assertTrue(binaryCreated instanceof byte[], "O valor retornado não é um byte[]" + binaryCreated);
	}

	@Test
	void testConvertBytesForUUID(){
		byte[] binaryCreated = Utils.create().generateRandomUUID().generateRandomBinaryByUUID();
		UUID uuidCreated = Utils.create().convertBytesForUUID(binaryCreated);

		assertTrue(uuidCreated instanceof UUID, "O valor retornado não é um UUID");
	}

}
