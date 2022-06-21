package com.spring_final.tag;


import com.spring_final.model.Authority;
import com.spring_final.model.User;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;

public class AuthorityTag extends TagSupport {
    private String auth = "";

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getAuth(){ return auth; }

    @Override
    public int doStartTag() {

        User authUser = (User) pageContext.getSession().getAttribute("authUser");

        if(authUser == null)
            return SKIP_BODY;
        for(Authority currentAuthority : authUser.getAuthorities()) {
            if (currentAuthority.getAuthority() == Authority.AuthorityEnum.valueOf(auth)) {
                return EVAL_BODY_INCLUDE;
            }
        }
        return SKIP_BODY;
    }
}



