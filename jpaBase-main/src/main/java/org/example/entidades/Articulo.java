package org.example.entidades;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Entity
@Table(name = "articulo")
@Audited
public class Articulo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;
    private String denominacion;
    private int precio;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.PERSIST)
    @Builder.Default
    private Set<DetalleFactura> detalle = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}) //se persisten las categorias o que se actualicen
    @JoinTable(name = "articulo_categoria",
               joinColumns = @JoinColumn(name = "articulo_id"),
               inverseJoinColumns = @JoinColumn(name = "categoria_id"))

    @Builder.Default
    private Set<Categoria> categorias = new HashSet<>();
}
