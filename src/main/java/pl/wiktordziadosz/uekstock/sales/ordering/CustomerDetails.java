package pl.wiktordziadosz.uekstock.sales.ordering;


import javax.persistence.Embeddable;

@Embeddable
public class CustomerDetails {
    String firstname;
    String lastname;
    String email;

    CustomerDetails() {}

    public CustomerDetails(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public static CustomerDetails of(String firstname, String lastname, String email) {
        return new CustomerDetails(firstname, lastname, email);
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }
}
