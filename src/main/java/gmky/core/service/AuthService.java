package gmky.core.service;

import gmky.core.api.model.LoginReq;
import gmky.core.api.model.LoginRes;
import gmky.core.api.model.UserSummaryRes;

public interface AuthService {
    LoginRes login(LoginReq req);

    void forgotPassword(String email);

    UserSummaryRes summary();
}
