package net.massol.maven;

import org.apache.maven.model.Model;
import org.junit.Assert;
import org.junit.Test;

public class POMResolverTest
{
    @Test
    public void testResolvePOM() throws Exception
    {
        POMResolver resolver = new POMResolver();
        Model model = resolver.resolvePOM("org.xwiki.commons", "xwiki-commons-component-default", "3.4",
            "http://nexus.xwiki.org/nexus/content/groups/public");
        Assert.assertEquals("XWiki Commons - Component - Default Implementation", model.getName());
    }
}
