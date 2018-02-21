package com.altmedia.billboard.service;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
@Ignore
public class CredentialServiceTest {

    @Test
    public void testAdminIds() {
        Assert.assertNotNull(CredentialService.getInstance().getAdminEmailIds());
    }

}
