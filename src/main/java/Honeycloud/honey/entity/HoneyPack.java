package Honeycloud.honey.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class HoneyPack {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Date createdAt;

    @ManyToMany(targetEntity=Honey.class)
    @NotNull(message = "Чтобы создать заказ нужно выбрать Мёд")
    private List<Honey> honeys;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

    public void addHoney(Honey honey){
        honeys.add(honey);
    }
}
