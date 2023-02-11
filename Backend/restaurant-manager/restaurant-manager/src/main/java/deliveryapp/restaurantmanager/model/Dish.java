package deliveryapp.restaurantmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private String dishType;
    private String picture;
    private Integer unitPrice;
    private Boolean available;
    @CreatedDate
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(nullable = false, updatable = false )
    private Date creationDate;
    @LastModifiedDate
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(nullable = false, updatable = false)
    private Date updateDate;
}
