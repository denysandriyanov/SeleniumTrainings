package seleniumLessons.task3;

import ru.qatools.properties.Property;
import ru.qatools.properties.Resource;

@Resource.Classpath("litecart.properties")
public interface Config 
{
    @Property("baseUrl")
    String getBaseUrl();
}
