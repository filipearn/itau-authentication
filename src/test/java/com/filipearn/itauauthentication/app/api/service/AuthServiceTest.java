package com.filipearn.itauauthentication.app.api.service;

import com.filipearn.itauauthentication.app.service.impl.AuthServiceImpl;
import com.filipearn.itauauthentication.infra.config.JwtSecretConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    private AuthServiceImpl service;

    @Mock
    private JwtSecretConfig jwtSecretConfig;

    public static final String VERDADEIRO = "verdadeiro";
    public static final String FALSO = "falso";
    public static final String secret = "xf/bNOeCaeqCttavvdd+JNdJ68s5Azjh16+T+l8X3AA=";

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShouldReturnTrueWhenJWTIsValid() {
        String jwtValid = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.4Zo20qmmagvUvo_Ft0Wjz8crrrtm8NFOeSHpknYN-eQ";

        when(jwtSecretConfig.getSigningKey()).thenReturn(secret);

        String ret = service.checkJwt(jwtValid);

        assertEquals(VERDADEIRO, ret, "Must be equals");
    }

    @Test
    public void testShouldReturnFalseWhenJWTisNull() {
        String jwt = null;

        when(jwtSecretConfig.getSigningKey()).thenReturn(secret);

        String ret = service.checkJwt(jwt);

        assertEquals(FALSO, ret, "Must be equals");
    }

    @Test
    public void testShouldReturnFalseWhenJWTisInvalid() {
        String jwtInvalid = "eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";

        when(jwtSecretConfig.getSigningKey()).thenReturn(secret);

        String ret = service.checkJwt(jwtInvalid);

        assertEquals(FALSO, ret, "Must be equals");
    }

    @Test
    public void testShouldReturnFalseWhenJWTClaimNameHasNumeric() {
        String jwtHasNameClaimNumeric = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiNzIzNDEiLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.sS-beTM4Jz2teToF-ru0GkxuSzUIzceiUNSbeZ-bek4";

        when(jwtSecretConfig.getSigningKey()).thenReturn(secret);

        String ret = service.checkJwt(jwtHasNameClaimNumeric);

        assertEquals(FALSO, ret, "Must be equals");
    }

    @Test
    public void testShouldReturnFalseWhenJWTHasMoreThan3Claims() {
        String jwtHasMoreThan3Claims = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.nShXVl4fbCGd-hTANDje6Ixr_cT4Atw1-GGuprrzZTc";

        when(jwtSecretConfig.getSigningKey()).thenReturn(secret);

        String ret = service.checkJwt(jwtHasMoreThan3Claims);

        assertEquals(FALSO, ret, "Must be equals");
    }

    @Test
    public void testShouldReturnFalseWhenJWTHasClaimsLowerCase() {
        String jwtHasClaimsLowerCase = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIm5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.EIgVDCuV0o4gnyYBVO_xw7DbAvhF_SebhjiNr99RH28";

        when(jwtSecretConfig.getSigningKey()).thenReturn(secret);

        String ret = service.checkJwt(jwtHasClaimsLowerCase);

        assertEquals(FALSO, ret, "Must be equals");
    }

    @Test
    public void testShouldReturnFalseWhenJWTHasClaimsNameGreaterThan256() {
        String jwtHasClaimsLowerCase = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJ2YWxvciBkZSBjbGFpbSBwb3NzdWkgY2FyYWN0ZXIgZGUgbsO6bWVyb3N2YWxvciBkZSBjbGFpbSBwb3NzdWkgY2FyYWN0ZXIgZGUgbsO6bWVyb3N2YWxvciBkZSBjbGFpbSBwb3NzdWkgY2FyYWN0ZXIgZGUgbsO6bWVyb3N2YWxvciBkZSBjbGFpbSBwb3NzdWkgY2FyYWN0ZXIgZGUgbsO6bWVyb3N2YWxvdmFsb3IgZGUgY2xhaW0gcG9zc3VpIGNhcmFjdGVyIGRlIG7Dum1lcm9zdmFsb3IgZGUgY2xhaW0gcG9zc3VpIGNhcmFjdGVyIGRlIG7Dum1lcm9zdmFsb3IgZGUgY2xhaW0gcG9zc3VpIGNhcmFjdGVyIGRlIG7Dum1lcm9zdmFsb3IgZGUgY2xhaW0gcG9zc3VpIGNhcmFjdGVyIGRlIG7Dum1lcm9zdmFsb3IgZGUgY2xhaW0gcG9zc3VpIGNhcmFjdGVyIGRlIG7Dum1lcm9zdmFsb3IgZGUgY2xhaW0gcG9zc3VpIGNhcmFjdGVyIGRlIG7Dum1lcm9zciBkZSBjbGFpbSBwb3NzdWkgY2FyYWN0ZXIgZGUgbsO6bWVyb3N2YWxvciBkZSBjbGFpbSBwb3NzdWkgY2FyYWN0ZXIgZGUgbsO6bWVyb3N2YWxvciBkZSBjbGFpbSBwb3NzdWkgY2FyYWN0ZXIgZGUgbsO6bWVyb3N2YWxvciBkZSBjbGFpbSBwb3NzdWkgY2FyYWN0ZXIgZGUgbsO6bWVyb3MifQ.YVFGhiPDVaKIHIZBSk8qYOipDoXD0b7S9Ruld_Le0_o";

        when(jwtSecretConfig.getSigningKey()).thenReturn(secret);

        String ret = service.checkJwt(jwtHasClaimsLowerCase);

        assertEquals(FALSO, ret, "Must be equals");
    }


}
