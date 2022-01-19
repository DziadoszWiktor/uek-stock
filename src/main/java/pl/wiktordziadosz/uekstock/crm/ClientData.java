package pl.wiktordziadosz.uekstock.crm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientData {
    @Id
    @GeneratedValue
    private Integer id;
    private String firstname;
    private String lastname;
    @Embedded
    private Address address;
}
