package jp.co.oracle.ee7samples.model;

import java.util.ArrayList;

public class Person {
    private String name;
    private Integer age;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public static ArrayList createDummyData() {
        ArrayList<Person> data = new ArrayList<>();
        Person person;
        for (int i = 0; i < 100; i++) {
            person = new Person();
            person.setAge(Integer.valueOf(i));
            if (i % 2 == 1) {
                person.setName("山田　太郎" + i);
                person.setSex("男性");
            } else {
                person.setName("山田　花子" + i);
                person.setSex("女性");
            }
            data.add(person);
        }
        return data;
    }
}
