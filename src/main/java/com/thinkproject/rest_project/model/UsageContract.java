//UsageContract.java
package com.thinkproject.rest_project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "usage_contracts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsageContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client; // Relacionamento Many-to-One com Client

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private CloudService service; // Relacionamento Many-to-One com CloudService

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "status", nullable = false)
    private String status; // Ex.: "ACTIVE", "CANCELLED"
}

