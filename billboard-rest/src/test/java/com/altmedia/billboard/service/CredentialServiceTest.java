package com.altmedia.billboard.service;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
@Ignore
public class CredentialServiceTest {

    @Test
    public void testAdminIds() {
        System.setProperty("aws.accessKeyId", "AKIAIQ5FMLYHBFEIAZRQ");
        System.setProperty("aws.secretKey", "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");
        Assert.assertNotNull(CredentialService.getInstance().getAdminEmailIds());
    }

}
