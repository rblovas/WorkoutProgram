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
public class ExercisesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)             // az itt zárójelben megadott 'strategy'-nek amúgy ez a default értéke is, csak szemléltetés miatt írtam ide,
    @Column(name = "ID", nullable = false, updatable = false)   // hiszen a JPA tud saját magától is egyedi ID-t generálni
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BODYPART")
    private String bodypart;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "WOMANWEIGHTLOSS")
    private String womanWeightLoss;

    @Column(name = "MANWEIGHTLOSS")
    private String manWeightLoss;

    @Column(name = "WOMANCUTTING")
    private String womanCutting;

    @Column(name = "MANCUTTING")
    private String manCutting;

    @Column(name = "EASY")
    private String easy;

    @Column(name = "TIME")
    private int time;

    @Column(name = "TYPE")
    private String type;


}
