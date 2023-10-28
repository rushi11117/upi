package com.upi.transactionmanager.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The type Test.
 */
@Entity
@Data
public class Test {

    @Id
    private Long id;
    private String Name;
}
