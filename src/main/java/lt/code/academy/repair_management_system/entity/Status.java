package lt.code.academy.repair_management_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "statuses")
public class Status {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 125, nullable = false, unique = true)
    private String name;
}
