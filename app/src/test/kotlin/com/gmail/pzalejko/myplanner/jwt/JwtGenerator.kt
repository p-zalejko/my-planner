package com.gmail.pzalejko.myplanner.jwt

import java.security.PrivateKey
import java.nio.charset.Charset
import org.jose4j.jws.AlgorithmIdentifiers
import org.jose4j.jws.JsonWebSignature
import org.jose4j.jwt.NumericDate
import org.eclipse.microprofile.jwt.Claims
import org.jose4j.jwt.JwtClaims
import java.util.stream.Collectors
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.security.KeyFactory
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*

/**
 * Code taken from https://quarkus.io/guides/jwt-guide
 */
class JwtGenerator {

    @Throws(Exception::class)
    fun generateTokenString(jsonResName: String, timeClaims: Map<String, Long>): String {
        // Use the test private key associated with the test public key for a valid signature
        val pk = readPrivateKey("/privateKey.pem")
        return generateTokenString(pk, "/privateKey.pem", jsonResName, timeClaims)
    }

    @Throws(Exception::class)
    fun generateTokenString(privateKey: PrivateKey, kid: String,
                            jsonResName: String, timeClaims: Map<String, Long>?): String {

        val claims = JwtClaims.parse(readTokenContent(jsonResName))
        val currentTimeInSecs = currentTimeInSecs().toLong()
        val exp = if (timeClaims != null && timeClaims.containsKey(Claims.exp.name))
            timeClaims[Claims.exp.name]
        else
            currentTimeInSecs + 300

        claims.issuedAt = NumericDate.fromSeconds(currentTimeInSecs)
        claims.setClaim(Claims.auth_time.name, NumericDate.fromSeconds(currentTimeInSecs))
        claims.expirationTime = NumericDate.fromSeconds(exp!!)

        val jws = JsonWebSignature()
        jws.payload = claims.toJson()
        jws.key = privateKey
        jws.keyIdHeaderValue = kid
        jws.setHeader("typ", "JWT")
        jws.algorithmHeaderValue = AlgorithmIdentifiers.RSA_USING_SHA256

        return jws.compactSerialization
    }

    @Throws(IOException::class)
    private fun readTokenContent(jsonResName: String): String {
        val contentIS = JwtGenerator::class.java!!.getResourceAsStream(jsonResName)
        BufferedReader(InputStreamReader(contentIS)).use { buffer -> return buffer.lines().collect(Collectors.joining("\n")) }
    }

    @Throws(Exception::class)
    fun readPrivateKey(pemResName: String): PrivateKey {
        val contentIS = JwtGenerator::class.java!!.getResourceAsStream(pemResName)
        val tmp = ByteArray(4096)
        val length = contentIS.read(tmp)
        return decodePrivateKey(String(tmp, 0, length, Charset.forName("UTF-8")))
    }

    @Throws(Exception::class)
    fun decodePrivateKey(pemEncoded: String): PrivateKey {
        val encodedBytes = toEncodedBytes(pemEncoded)

        val keySpec = PKCS8EncodedKeySpec(encodedBytes)
        val kf = KeyFactory.getInstance("RSA")
        return kf.generatePrivate(keySpec)
    }

    private fun toEncodedBytes(pemEncoded: String): ByteArray {
        val normalizedPem = removeBeginEnd(pemEncoded)
        return Base64.getDecoder().decode(normalizedPem)
    }

    private fun removeBeginEnd(pem: String): String {
        var pem = pem
        pem = pem.replace("-----BEGIN (.*)-----".toRegex(), "")
        pem = pem.replace("-----END (.*)----".toRegex(), "")
        pem = pem.replace("\r\n".toRegex(), "")
        pem = pem.replace("\n".toRegex(), "")
        return pem.trim { it <= ' ' }
    }

    fun currentTimeInSecs(): Int {
        val currentTimeMS = System.currentTimeMillis()
        return (currentTimeMS / 1000).toInt()
    }
}