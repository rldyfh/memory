package toy01.memory.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class PostingForm {

    @NotBlank()
    private String title;

    @NotBlank
    @Size(min=1, max=2800)
    private String content;

}
