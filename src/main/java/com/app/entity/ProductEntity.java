package com.app.entity;



import com.app.model.ProductModel;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "name")
    String name;

    @OneToOne(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @JoinColumn(name = "details_id", referencedColumnName = "productId")
    ProductDetails details;


    public int getDetailsId() {
        return details != null ? details.getProductId() : 12;  // Access foreign key directly
    }


}
