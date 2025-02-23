//BaseDTO.java
package com.thinkproject.rest_project.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public abstract class BaseDTO implements Serializable {
    private Long id;
}

