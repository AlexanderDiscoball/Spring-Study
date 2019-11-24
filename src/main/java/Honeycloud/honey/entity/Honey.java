package Honeycloud.honey.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Data
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@Entity
public class Honey {

    @Id
    private final String id;

    @NotNull
    private final String name;
    @NotNull
    private final int price;
    @NotNull
    private final Weights weight;

    @NotNull
    public enum Weights {
        POLKILO, KILO, THREEKILO
    }

}
