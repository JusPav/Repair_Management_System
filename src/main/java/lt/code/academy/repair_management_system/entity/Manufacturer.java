package lt.code.academy.repair_management_system.entity;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "manufacturers")
public class Manufacturer {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 125, nullable = false, unique = true)
    private String name;
}
