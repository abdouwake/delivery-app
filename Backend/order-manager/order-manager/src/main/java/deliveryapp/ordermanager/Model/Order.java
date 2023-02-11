package deliveryapp.ordermanager.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_table")
public class Order {
    public enum OrderStatus {
        Created, Processing, Paid, Finished, Cancelld
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int idUser;
    private User user;
    private int idRestaurant;
    private double totalPrice;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime orderTime = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.Created;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<ItemQuantity> items;
}
