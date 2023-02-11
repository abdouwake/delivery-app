package deliveryapp.restaurantmanager.dto;

import lombok.Data;

@Data
public class SuspendRequestDto {
    private Integer idRestaurant;
    private Boolean suspended;
}
