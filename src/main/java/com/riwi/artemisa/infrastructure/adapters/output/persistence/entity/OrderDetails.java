    package com.riwi.artemisa.infrastructure.adapters.output.persistence.entity;
    
    
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    
    import java.util.List;
    
    @Entity
    @Table(name = "order_details")
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class OrderDetails {
    
        @Id
        @GeneratedValue( strategy = GenerationType.IDENTITY)
        private Long id;
    
        @Column(name = "quantity", nullable = false)
        private int quantity;
    
        @Column(name = "unit_price", nullable = false)
        private float unitPrice;
    
        @Column(name = "total_price_product", nullable = false)
        private float totalPriceProduct;
//
//        private List<Product> products;
//
//        private List<Medication> medications;
    
    }