package Honeycloud.honey.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
//JPA
@Entity
@Table(name="Taco_Order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    //JPA
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Date placedAt;

    @NotBlank(message = "Укажите свое имя")
    private String deliveryName;

    @NotBlank(message = "Укажите Вашу улицу")
    private String deliveryStreet;

    @NotBlank(message = "Укажите Ваш город")
    private String deliveryCity;

    @CreditCardNumber(message = "Некорректный номер карты")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Напишите в формате MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Некорректный CVV")
    private String ccCVV;

    @ManyToMany(targetEntity=HoneyPack.class)
    private List<HoneyPack> honeysPack = new ArrayList<>();

    @ManyToOne
    private Honeycloud.honey.entity.User user;

    public void addDesign(HoneyPack design) {
        this.honeysPack.add(design);
    }

    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }

}
