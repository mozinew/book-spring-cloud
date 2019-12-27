package com.tengol.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SortTest
 *
 * @author dongrui
 * @date 2019/12/12 18:38
 */
public class SortTest {

    @Test
    public void test() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("zhangsan", "22"));
        personList.add(new Person("lis", "28"));
        personList.add(new Person("lis", "123"));
        personList.add(new Person("wang5", null));
        System.out.println(JSON.toJSONString(personList));
        System.out.println("-------");

        //personList.sort(Comparator.comparing(Person::getAge,Comparator.nullsLast(Integer::compareTo)));
        personList.sort(Comparator.comparing(p->StringUtils.isEmpty(p.getAgeNew()) ? null : Integer.valueOf(p.getAgeNew()),Comparator.nullsFirst(Integer::compareTo)));

        System.out.println(JSON.toJSONString(personList));
    }


    /**
     * //personList.sort(Comparator.nullsFirst(Comparator.comparing(p->p.getAge())));
     *         //personList.sort(Comparator.nullsFirst(Comparator.comparing(Person::getAge)));
     *         //personList.sort(Comparator.comparing(Person::getAge,Comparator.nullsLast(Integer::compareTo)));
     *         //personList.sort(Comparator.comparing(Person::getAge,Comparator.nullsLast(Comparator.comparing())));
     *         List<Person> sortedList = personList.stream().peek(p -> {
     *             if (!StringUtils.isEmpty(p.getAgeNew())) {
     *                 p.setIntAge(Integer.getInteger(p.getAgeNew()));
     *             }
     *         }).sorted(Comparator.comparing(Person::getIntAge, Comparator.nullsFirst(Integer::compareTo)))
     *                 .collect(Collectors.toList());
     */
}
