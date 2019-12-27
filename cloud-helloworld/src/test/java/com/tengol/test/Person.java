package com.tengol.test;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Person
 *
 * @author dongrui
 * @date 2019/12/12 18:40
 */
@Data
//@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name;
    private Integer age;
    private String ageNew;
    private Integer intAge;

    public Person(String name,Integer age,String ageNew){
        this.name = name;
        this.age = age;
        this.ageNew = ageNew;
    }

    public Person(String name,String ageNew){
        this.name = name;
        this.ageNew = ageNew;
    }
}
