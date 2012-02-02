package net.massol.maven;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.maven.model.Model;
import org.xwiki.component.embed.EmbeddableComponentManager;
import org.xwiki.container.ApplicationContext;
import org.xwiki.container.Container;
import org.xwiki.extension.Extension;
import org.xwiki.extension.ExtensionId;
import org.xwiki.extension.repository.ExtensionRepository;
import org.xwiki.extension.repository.ExtensionRepositoryFactory;
import org.xwiki.extension.repository.ExtensionRepositoryId;

public class POMResolver
{
    public Model resolvePOM(String groupId, String artifactId, String version, String remoteRepository)
        throws Exception
    {
        // Initialize XWiki's Component Manager
        EmbeddableComponentManager ecm = new EmbeddableComponentManager();
        ecm.initialize(getClass().getClassLoader());

        // Initialize Container
        // TODO: Modify XWiki Platform to introduce a default configured Container so that we don't have to do this
        // here.
        Container container = ecm.lookup(Container.class);
        container.setApplicationContext(new ApplicationContext()
        {
            public InputStream getResourceAsStream(String resource)
            {
                return null;
            }

            public URL getResource(String resource) throws MalformedURLException
            {
                return null;
            }

            public File getTemporaryDirectory()
            {
                return FileUtils.getTempDirectory();
            }

            public File getPermanentDirectory()
            {
                return getTemporaryDirectory();
            }
        });

        ExtensionRepositoryFactory repositoryFactory = ecm.lookup(ExtensionRepositoryFactory.class, "maven");
        ExtensionRepositoryId repositoryId = new ExtensionRepositoryId("maven", "maven", new URI(remoteRepository));
        ExtensionRepository repository = repositoryFactory.createRepository(repositoryId);
        Extension extension = repository.resolve(
            new ExtensionId(String.format("%s:%s", groupId, artifactId), version));

        return (Model) extension.getProperty("maven.Model");
    }

    public Model resolvePOM(String groupId, String artifactId, String version) throws Exception
    {
        return resolvePOM(groupId, artifactId, version, "http://repo2.maven.org/maven2/");
    }
}
