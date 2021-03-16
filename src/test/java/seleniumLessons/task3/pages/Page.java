package seleniumLessons.task3.pages;

import static com.codeborne.selenide.Selenide.open;
import seleniumLessons.task3.application.ApplicationContext;

abstract class Page 
{
    protected ApplicationContext appContext;

    public Page(ApplicationContext appContext) 
    {
        this.appContext = appContext;
    }
}
