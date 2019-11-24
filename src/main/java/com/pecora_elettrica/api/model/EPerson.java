package com.pecora_elettrica.api.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "t_person")
public class EPerson implements Serializable {

    /** static **/

    private static final long serialVersionUID = 936507L;

    /** instance **/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key", nullable = false, updatable = false)
    private Long key;

    @Column(name = "first_name", length = 100, nullable = false, updatable = true)
    private String firstName;

    @Column(name = "last_name", length = 75, nullable = false, updatable = true)
    private String lastName;

    /** utils */

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof EPerson))
            return false;

        EPerson op = (EPerson) o;

        return op.key.equals(key);
    }

}