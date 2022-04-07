package com.c4c.vue3ProjectServer.utils;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.c4c.vue3ProjectServer.form.SignInReq;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTUtils {
    /**
     * 楕円曲線暗号の公開鍵
     */
    private static ECPublicKey PUBLIC_KEY;

    /**
     * 楕円曲線暗号の秘密鍵
     */
    private static ECPrivateKey PRIVATE_KEY;

    /**
     * JWTの発行日時
     * @return 現在日時
     */
    private static long jwtIat() {
        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime zdt = ldt.atZone(ZoneOffset.ofHours(+9));
        return zdt.toEpochSecond();
    }

    /**
     * JWTの有効期限
     * @return 現在日時 + 1時間後
     */
    private static long jwtExp() {
        LocalDateTime ldt = LocalDateTime.now().plusHours(1);
        ZonedDateTime zdt = ldt.atZone(ZoneOffset.ofHours(+9));
        return zdt.toEpochSecond();
    }

    /**
     * JWTを生成する。
     * @return jwt
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws SignatureException
     * @throws InvalidKeyException
     * @throws JsonProcessingException
     */
    public static String createJWT(SignInReq reqForm)
            throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, SignatureException,
            InvalidKeyException, JsonProcessingException {
        // 署名鍵生成
        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC"); //楕円曲線暗号の鍵に設定
        keyPairGenerator.initialize(new ECGenParameterSpec("secp256r1")); //鍵を初期化
        final KeyPair keyPair = keyPairGenerator.generateKeyPair(); //公開鍵と秘密鍵を生成
        PUBLIC_KEY = (ECPublicKey) keyPair.getPublic(); //公開鍵を取得
        PRIVATE_KEY = (ECPrivateKey) keyPair.getPrivate(); //秘密鍵を取得
        byte[] publicKeyEncodedBytes = Base64.encodeBase64(PUBLIC_KEY.getEncoded()); //公開鍵をBase64でエンコード
        byte[] privateKeyEncodedBytes = Base64.encodeBase64(PRIVATE_KEY.getEncoded()); //秘密鍵をBase64でエンコード
        System.out.println("ES256 Public Key:" + new String(publicKeyEncodedBytes));
        System.out.println("ES256 Private Key:" + new String(privateKeyEncodedBytes));
        // ヘッダー部設定
        final ObjectMapper objectMapper = new ObjectMapper(); //JSONとJavaオブジェクト相互変換用オブジェクトを生成
        final Map<String, Object> jwtHeader = new LinkedHashMap<>(); //ヘッダーオブジェクトを生成
        jwtHeader.put("alg", "ES256"); //アルゴリズムをES256で設定
        jwtHeader.put("typ", "JWT"); //タイプをJWTで設定
        String jwtHeaderStr = Base64.encodeBase64URLSafeString(objectMapper.writeValueAsBytes(jwtHeader)); //ヘッダーオブジェクトをBase64でエンコード
        // ペイロード部設定
        final Map<String, Object> jwtPayload = new LinkedHashMap<>(); //ペイロードオブジェクトを生成
        jwtPayload.put("sub", reqForm.getEmail()); //JWT発行者のユーザ識別子
        jwtPayload.put("iat", jwtIat()); //JWT発行時刻
        jwtPayload.put("exp", jwtExp()); //JWT有効期限
        String jwtPayloadStr = Base64.encodeBase64URLSafeString(objectMapper.writeValueAsBytes(jwtPayload)); //ペイロードオブジェクトをBase64でエンコード
        // 署名設定
        final Signature jwtSignature = Signature.getInstance("SHA256withECDSAinP1363Format"); //署名アルゴリズムをES256で設定
        jwtSignature.initSign(PRIVATE_KEY); //秘密鍵を指定して署名を初期化
        jwtSignature.update((jwtHeaderStr + "." + jwtPayloadStr).getBytes()); //ヘッダーとペイロードの署名を作成
        byte[] jwtSignatureBytes = jwtSignature.sign(); //署名(ヘッダー、ペイロード)を取得
        final String jwtSignatureStr = Base64.encodeBase64URLSafeString(jwtSignatureBytes); //署名データ(ヘッダー、ペイロード)をBase64でエンコード
        String jwt = jwtHeaderStr + "." + jwtPayloadStr + "." + jwtSignatureStr; //JWT(ヘッダー.ペイロード.署名データ)を作成
        System.out.println("jwt:" + jwt);
        // JWTを検証
        boolean authFlg = verifyJWT(jwt);
        if (authFlg) {
            return jwt;
        } else {
            throw new SignatureException();
        }
    }

    /**
     * JWTを検証する。
     * @param jwt
     * @return verifiFlg true:成功 false:失敗
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static boolean verifyJWT(String jwt) {
        try {
            boolean verifiFlg = false; //検証フラグ
            final String[] splitJwt = jwt.split("\\."); //JWTをヘッダー、ペイロード、署名に分割
            final String jwtHeaderStr = splitJwt[0]; //ヘッダーを取得
            final String jwtPayloadStr = splitJwt[1]; //ペイロードを取得
            final String jwtSignatureStr = splitJwt[2]; //署名を取得
            final Signature jwtSignature = Signature.getInstance("SHA256withECDSAinP1363Format"); //署名アルゴリズムをES256で設定
            jwtSignature.initVerify(PUBLIC_KEY); //公開鍵を指定して署名を初期化
            jwtSignature.update((jwtHeaderStr + "." + jwtPayloadStr).getBytes()); //ヘッダーとペイロードの署名を作成
            // 検証が成功した場合
            if (jwtSignature.verify(Base64.decodeBase64(jwtSignatureStr))) {
                verifiFlg = true;
                System.out.println("認証成功");
            } else {
                System.out.println("認証失敗");
            }
            return verifiFlg;
        } catch (Exception e) {
            System.out.println("認証失敗");
            return false;
        }
    }
}
