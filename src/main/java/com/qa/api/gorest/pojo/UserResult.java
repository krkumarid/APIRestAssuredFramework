package com.qa.api.gorest.pojo;

import org.testng.annotations.Test;

import com.qa.api.gorest.util.TestUtil;

public class UserResult
{
    @Test
    public void createUserWithFullJson()
    {
        Self sf = new Self("http://www.sf.com");
        Edit ed = new Edit("http://www.ed.com");
        Avatar av = new Avatar("http://www.av.com");

        Links ln = new Links(sf, ed, av);

        UserInfo userInfo = new UserInfo("Asha", "asha16@gmail.com", "female", "active", ln);
        String finalJson = TestUtil.getSerializedJSON(userInfo);
        System.out.println(finalJson);
    }
}
