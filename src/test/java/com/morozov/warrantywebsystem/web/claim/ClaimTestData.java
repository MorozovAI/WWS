package com.morozov.warrantywebsystem.web.claim;

import com.morozov.warrantywebsystem.MatcherFactory;
import com.morozov.warrantywebsystem.model.Claim;
import com.morozov.warrantywebsystem.to.ClaimTo;

import java.time.LocalDate;

public class ClaimTestData {

    public static final MatcherFactory.Matcher<Claim> CLAIM_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Claim.class,
            "dealer", "oem", "mileage", "mileageType", "applicationType", "engineModel",
            "failureDate", "claimReceivedDate", "claimQuestionDate", "claimSubmitDate", "claimApproveDate",
            "parts", "claimAmount", "approvedAmount", "author", "adviser", "narrative", "history", "status");
    public static MatcherFactory.Matcher<ClaimTo> CLAIM_TO_MATCHER = MatcherFactory.usingEqualsComparator(ClaimTo.class);

    public static final int USER_ID = 18;
    public static final int ADMIN_ID = 4;
    public static final int ADVISER_ID = 5;
    public static final int NOT_FOUND = 100;
    public static final String USER_MAIL = "voronina@gmail.ru";
    public static final String ADMIN_MAIL = "morozov@gmail.ru";
    public static final String ADVISER_MAIL = "perepechina@gmail.ru";

    public static final int CLAIM1_ID = 141;
    public static final int CLAIM2_ID = 140;

    public static final Claim claim1 = new Claim(CLAIM1_ID, "1112/2", "33215805", LocalDate.parse("2019-12-11"));

    public static Claim getNew() {
        return new Claim(null, "2112/2", "33215806", LocalDate.parse("2022-12-11"));
    }
}
