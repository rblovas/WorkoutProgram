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
     * 0: nő
     * 1: férfi
     */
    @Column(name = "GENDER")
    private int gender;

    /**
     * Életkor
     */
    @Column(name = "AGE")
    private int age;

    /**
     * Edzés típusa
     * 0: Fogyás
     * 1: Szálkásítás
     * 2: Tömegelés
     */
    @Column(name = "TYPE")
    private int type;

    /**
     * Kezdő súly
     */
    @Column(name = "START_WEIGHT")
    private int startWeight;

    /**
     * Aktuális súly
     */

    @Column(name = "ACTUAL_WEIGHT")
    private int actualWeight;


    /**
     * Cél súly
     */
    @Column(name = "GOAL_WEIGHT")
    private int goalWeight;

}
