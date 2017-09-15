package myspotify.security;

class SecurityConstants {
    static final String SECRET = "SecretKeyToGenJWTs";
    static final long EXPIRATION_TIME = 864_000_000; // 10 days
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER = "Authorization";
    static final String SIGN_UP_URL = "/sign-up";
    static final String TOKEN_PARAMETER = "access_token";
}
