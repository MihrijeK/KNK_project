package sample.Controllers;

import javafx.fxml.Initializable;
import sample.utils.SessionManager;

import java.util.ResourceBundle;

public abstract class LanguageController implements Initializable {

    protected ResourceBundle getLangBundle() {
        return ResourceBundle.getBundle("sample.Languages.LangBundle", SessionManager.getLocale());
    }
    public abstract void loadLangTexts(ResourceBundle langBundle);
}
