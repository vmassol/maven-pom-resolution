package net.massol.maven;

import java.util.Collections;
import java.util.List;

import javax.inject.Named;

import org.xwiki.component.annotation.Component;
import org.xwiki.configuration.ConfigurationSource;

@Component
@Named("xwikiproperties")
public class DefaultConfigurationSource implements ConfigurationSource
{
    public boolean containsKey(String key)
    {
        return false;
    }

    public List<String> getKeys()
    {
        return Collections.emptyList();
    }

    public <T> T getProperty(String key)
    {
        return null;
    }

    public <T> T getProperty(String key, T defaultValue)
    {
        return null;
    }

    public <T> T getProperty(String key, Class<T> valueClass)
    {
        return null;
    }

    public boolean isEmpty()
    {
        return true;
    }
}
