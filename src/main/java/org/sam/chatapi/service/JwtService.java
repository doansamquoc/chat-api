package org.sam.chatapi.service;

import org.sam.chatapi.entity.User;
import org.sam.chatapi.enums.ErrorCode;
import org.sam.chatapi.exception.BusinessException;
import org.sam.chatapi.property.AppProperties;
import org.sam.chatapi.util.UUIDUtils;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtService {
	SecretKey secretKey;
	AppProperties props;

	public String create(User user) {
		JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
		Payload payload = generatePayload(user);
		JWSObject jwsObject = new JWSObject(header, payload);

		try {
			jwsObject.sign(new MACSigner(secretKey));
			return jwsObject.serialize();
		} catch (JOSEException e) {
			log.error("Create Access Token Failed: {}", e.getMessage());
			throw new BusinessException(ErrorCode.TOKEN_GENERATE_FAILED);
		}
	}

	public void revoke(String jti) {
		log.info("Revoke JWT ID: {}", jti);
		// Push it into Redis
	}

	private Payload generatePayload(User user) {
		JWTClaimsSet claims = generateClaims(user.getId());
		return new Payload(claims.toJSONObject());
	}

	private JWTClaimsSet generateClaims(Long userId) {
		Date issue = new Date();
		Date expires = new Date(System.currentTimeMillis() + props.getRefreshTokenExpiration());
		String jti = UUIDUtils.rand();
		return new JWTClaimsSet.Builder().jwtID(jti).issueTime(issue).expirationTime(expires).subject(userId.toString()).build();
	}
}

