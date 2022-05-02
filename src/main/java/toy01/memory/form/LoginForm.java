package toy01.memory.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class LoginForm {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;


}
