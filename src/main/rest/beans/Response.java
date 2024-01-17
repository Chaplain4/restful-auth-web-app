package main.rest.beans;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "responses")
@ToString
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "status")
    private boolean status;
    @Column(name = "msg")
    private String msg;
}
