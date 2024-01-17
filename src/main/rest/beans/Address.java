package main.rest.beans;

import lombok.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(name = "location")
    private String location;
    @Column(name = "postal_code")
    private int postalCode;
}
