package dev.waamir.hotelaluraapi.adapter.repository.AccountVerification;

public class AccountVerificationQueries {

    public static final String INSERT_ACCOUNT_VERIFICATION_QUERY = """
            INSERT INTO account_verifications (user_id, url) VALUES
            (:userId, :url);
            """;
    public static final String SELECT_ACCOUNT_VERIFICATION_BY_USER_QUERY = """
            SELECT * FROM account_verifications
            WHERE user_id = :userId;
            """;
    public static final String COUNT_ACCOUNT_VERIFICATIONS_BY_URL = """
            SELECT COUNT(*) FROM account_verifications
            WHERE url = :url;
            """;
}
