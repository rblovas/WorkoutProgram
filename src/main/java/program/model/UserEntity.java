package program.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * JPA entitás, azaz az órán is tárgyalt POJO.
 *
 * Egy ilyen POJO szemléltet a JPA-ban egy Táblát. Minden egyes táblán
 * lévő oszlop, egy-egy java-beli változó, melyet a JPA @Column annotációjával
 * jelölünk és benne adjuk meg a táblabeli nevét, hogy nullable-e, max hosszát
 * is megadhatjuk, stb...
 *
 * Több tábla létrehozásához több osztályt kell létrehozni, és ezekben az
 * entitásokban a @OneToOne vagy @OneToMany annotációt használva kapcsolhatjuk
 * (join) össze őket.
 *
 * A NamedQuery-k (adatbázis lekérdezések) kifejtése a manager-ben töténik.
 * Fontos, hogy tudd, mivel tér majd vissza a lekérdezés. A JPA
 * lekérdezések nagyon hasonlóak a tanult SQL-hez, azzal a különbséggel, hogy
 * itt mindig objektumokban kell gondolkozni. A Querykben lévő 'e' egy
 * adatbázisban lévő, már oda elmentett Entitás (objektum), melynek tagjaira ugyan úgy ponttal
 * lehet hivatkozni.
 *
 * plusz info Jeszy diáin
 *
 * @author balint
 */
@Entity
@Table(name = "USERS")

@Data //fontos, JPA használja a Gettereket, Settereket
@EqualsAndHashCode  //mégfontosabb, hiszen a JPA ezeken keresztül tud összehasonlítani 2 entitást
@ToString(callSuper = true)
public class UserEntity implements Serializable {

    /**
     * Entitás elsődleges, automatikusan generált kulcsa. (kötelező JPA elem)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)             // az itt zárójelben megadott 'strategy'-nek amúgy ez a default értéke is, csak szemléltetés miatt írtam ide,
    @Column(name = "ID", nullable = false, updatable = false)   // hiszen a JPA tud saját magától is egyedi ID-t generálni
    private Long id;

    /**
     * Felhasználó neve.
     */
    @Column(name = "NAME")
    private String name;

    /**
     * Felhasználóhoz tartozó jelszó
     */
    @Column(name = "PASSWORD")
    private String password;

    /**
     * Nem
     * female, male
     */
    @Column(name = "GENDER")
    private String gender;

    /**
     * How many days user did
     */
    @Column(name = "DAYS")
    private int days;


    /**
     * Edzés típusa
     * weightloss, cutting
     */
    @Column(name = "TYPE")
    private String type;

    //Kezdő súly
    @Column(name = "START_WEIGHT")
    private int startWeight;


    //Cél súly
    @Column(name = "GOAL_WEIGHT")
    private int goalWeight;


    @Column(name = "CARDIO_LEG")
    private String cardioLeg;

    @Column(name = "GLUTES")
    private String glutes;

    @Column(name = "QUADS")
    private String quads;

    @Column(name = "HAMSTRING")
    private String hamstring;

    @Column(name = "CALVES")
    private String calves;

    @Column(name = "ABS_LEG")
    private String absLeg;


    @Column(name = "CARDIO_UP")
    private String cardioUp;

    @Column(name = "SHOULDERS")
    private String shoulders;

    @Column(name = "CHEST")
    private String chest;

    @Column(name = "BICEPS")
    private String biceps;

    @Column(name = "BACK")
    private String back;

    @Column(name = "ABS_UP")
    private String absUp;


    @Column(name = "CARDIO_LEGW")
    private int cardioLegw;

    @Column(name = "GLUTESW")
    private int glutesw;

    @Column(name = "QUADSW")
    private int quadsw;

    @Column(name = "HAMSTRINGW")
    private int hamstringw;

    @Column(name = "CALVESW")
    private int calvesw;

    @Column(name = "ABS_LEGW")
    private int absLegw;


    @Column(name = "CARDIO_UPW")
    private int cardioUpw;

    @Column(name = "SHOULDERSW")
    private int shouldersw;

    @Column(name = "CHESTW")
    private int chestw;

    @Column(name = "BICEPSW")
    private int bicepsw;

    @Column(name = "BACKW")
    private int backw;

    @Column(name = "ABS_UPW")
    private int absUpw;
}
