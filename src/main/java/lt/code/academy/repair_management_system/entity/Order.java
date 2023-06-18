package lt.code.academy.repair_management_system.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.math.BigDecimal;

import jakarta.validation.constraints.Email;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String createdOn;

    @Column(length = 50, nullable = false, unique = true)
    private String orderNumber;

    @Column(length = 70, nullable = false)
    private String name;

    @Column(length = 70, nullable = false)
    private String surname;

    @Column(length = 150, nullable = false)
    @Email
    private String email;

    @Column(length = 50, nullable = false)
    private String phone;

    @Column(length = 50, nullable = false)
    private String model;

    @Column(length = 75, nullable = false)
    private String serialNumber;

    @Column(length = 50, nullable = false)
    @Positive
    private BigDecimal price;

    @Column(length = 250, nullable = false)
    private String comment;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "serviceOption_id"))
    private Set<ServiceOption> serviceOptions = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "deviceType_id"))
    private Set<DeviceType> deviceTypes = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "manufacturer_id"))
    private Set<Manufacturer> manufacturers = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "status_id"))
    private Set<Status> statuses = new HashSet<>();
}
