package pl.wiktordziadosz.uekstock.sales.datatransferobject;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerData {
    private String email;
    private String firstname;
    private String lastname;

}
