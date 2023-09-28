package by.belstu.bay.springbooks.forms;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonForm {
    private String name;
    private String age;
    private String editField;
}
