package cocinaAlRescate.cocina.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "Recomendador")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recomendador {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long idRecomendador;

    @ManyToOne
    @JoinColumn(name = "preferencia_id")
    private Preferencia preferenciaUsuario;

    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;

}
