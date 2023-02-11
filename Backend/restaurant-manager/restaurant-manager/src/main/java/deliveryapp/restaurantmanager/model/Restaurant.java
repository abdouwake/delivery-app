package deliveryapp.restaurantmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Integer idOwner;
    private String name;
    private String description;
    private String adress;
    private String picture;
    @Email
    private String email;
    @NumberFormat
    private Integer number;
    private Boolean suspended;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date creationDate;
    @LastModifiedDate
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(nullable = false, updatable = false)
    private Date lastUpdate;
    private String foodType;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Dish> Dish;
}
