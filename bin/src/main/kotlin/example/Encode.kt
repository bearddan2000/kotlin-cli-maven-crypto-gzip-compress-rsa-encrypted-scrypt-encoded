package example

import org.bouncycastle.crypto.generators.SCrypt;
import java.io.IOException;
import javax.xml.bind.DatatypeConverter;

class Encode {

  @Throws(IOException::class)
  fun compress(hash: String): ByteArray {
    val compress: ByteArray? = GZIPCompression.compress(hash);

    if(compress == null)
    {
      println(String.format("The Encrypted Compressed Text length is: %d", -1));

      return ByteArray(0)
    }

    return compress
  }


  @Throws(Exception::class)
  fun encrypt(rsa: Encryption, hash: String): ByteArray {

    val cipherText: ByteArray = rsa.do_RSAEncryption(hash);

    val newHash: String = DatatypeConverter.printHexBinary(cipherText);

    return compress(newHash);
  }

  fun hashpw(rsa: Encryption, pass: String): String {

    val SALT = "abc123";

    // DifficultyFactor
    // These should be powers of 2
    val cpu = 8;
    val memory = 8;
    val parallelism = 8;
    val outputLength = 32;

    val hash: ByteArray = SCrypt.generate(pass.toByteArray(), SALT.toByteArray(), cpu, memory, parallelism, outputLength);

    val stored: String = DatatypeConverter.printHexBinary(hash);

    try {

      val newHash: ByteArray = encrypt(rsa, stored);

      return DatatypeConverter.printHexBinary(newHash);

    } catch (e: Exception) {

      return "";
    }

  }

  fun verify(rsa: Encryption, pass :String, hash: String): Boolean {
    val newPass: String = hashpw(rsa, pass)
    return newPass.equals(hash)
  }
}
