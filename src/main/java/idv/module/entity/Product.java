package idv.module.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * * Product. 2020/3/22 1:05 上午
 * *
 * * @author sero
 * * @version 1.0.0
 *
 **/
@Data
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String enName;
    private String zhName;
    private Double price;
    private Timestamp releaseDate;
    private Timestamp editDate;

}
