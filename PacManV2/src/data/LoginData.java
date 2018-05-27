package data;

import java.io.Serializable;

public class LoginData implements Serializable {
    private String inputName;

    public LoginData(String inputName) {
        this.inputName = inputName;
    }


    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }
}
