package example;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

object GZIPCompression {

  @Throws(IOException::class)
  fun compress(str: String?): ByteArray? {
    if ((str == null) || (str.length == 0)) {
      return null;
    }
    val obj: ByteArrayOutputStream = ByteArrayOutputStream();
    val gzip: GZIPOutputStream = GZIPOutputStream(obj);
    gzip.write(str.toByteArray());
    gzip.flush();
    gzip.close();
    val compress: ByteArray? = obj.toByteArray();

    if(compress == null)
    {
      println(String.format("The Encrypted Compressed Text length is: %d", -1));

      return ByteArray(0)

    } else {
      println(String.format("The Encrypted Compressed Text length is: %d", compress.size));

    }

    return compress;
  }
}
