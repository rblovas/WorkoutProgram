package program.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "EXERCISES")


@Data //fontos, JPA használja a Gettereket, Settereket
@EqualsAndHashCode  //mégfontosabb, hiszen a JPA ezeken keresztül tud összehasonlítani 2 entitást
@ToString(callSuper = true)
public class ExerciseEntity {

    /**
     * Entitás elsődleges, automatikusan generált kulcsa. (kötelező JPA elem)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)             // az itt zárójelben megadott 'strategy'-nek amúgy ez a default értéke is, csak szemléltetés miatt írtam ide,
    @Column(name = "ID", nullable = false, updatable = false)   // hiszen a JPA tud saját magától is egyedi ID-t generálni
    private Long id;

    /**
     * Gyakorlat neve.
     */
    @Column(name = "NAME")
    private String name;

    /**
     * Gyakorlattal erősített testrész
     */
    @Column(name = "BODYPART")
    private String bodypart;

    /**
     * Leírás
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * Kép
     */
    @Column(name = "IMAGE")
    private String image;


    /**
     * Súly léptetés
     */
    @Column(name = "WEIGHT_STEP")
    private Integer step;
}
