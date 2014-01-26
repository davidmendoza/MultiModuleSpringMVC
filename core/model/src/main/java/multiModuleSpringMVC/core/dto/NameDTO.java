package multiModuleSpringMVC.core.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by david on 1/25/14.
 */
public class NameDTO {

    @Size(min=2, max=30, message="First name must be between 2-30 characters only")
    @NotNull
    private String firstName;

    @Size(min=2, max=30, message="First name must be between 2-30 characters only")
    @NotNull
    private String lastName;

    public NameDTO() {

    }

    public NameDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
