package org.suai.laba13.dao;

import org.suai.laba13.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PhoneBookDAO {


    private static final String URL = "jdbc:postgresql://localhost/PhoneBook";

    private static final String USERNAME = "postgres";

    private static final String PASSWORD = "<your_password>";

    private static int PEOPLE_COUNT = 0;

    private static Connection connection;



    public PhoneBookDAO(){
        try{
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized List<Person> index(){
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT Persons.id, Persons.FirstName, Persons.LastName, Telephone.Phone FROM Persons\n" +
                    "INNER JOIN Telephone ON persons.id = telephone.personid";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()){
                Person person = new Person();
                person.setFirstName(resultSet.getString("firstname"));
                person.setLastName(resultSet.getString("lastname"));
                String phone = resultSet.getString("phone");
                int id = resultSet.getInt("id");

                Person personTmp = findPerson(person, people);
                if(personTmp != null) {
                    personTmp.getPhoneList().add(phone); // add one more telephone
                }
                else {
                    person.getPhoneList().add(phone);
                    person.setId(id);
                    people.add(person);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return people;

    }

    public synchronized Person show(int id) {
        List<Person> personList = index();

        for(Person person : personList)
            if(person.getId() == id)
                return person;

        return null;
    }

    public synchronized void addTelephoneNumber(Person person, String telephone){

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Telephone (phone, personId) VALUES (?, ?)");
            preparedStatement.setString(1, telephone);
            preparedStatement.setInt(2, person.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public synchronized Person search(String personName){
        StringTokenizer st = new StringTokenizer(personName);
        String firstName = null;
        String lastName = null;
        if(st.countTokens() == 2){
            firstName = st.nextToken();
            lastName = st.nextToken();
        }

        System.out.println(firstName);
        System.out.println(lastName);

        Person personTmp = new Person(0, firstName, lastName);

        List<Person> personList = index();

        for(Person person : personList)
            if(person.equals(personTmp))
                return person;

        return null;

    }

    public synchronized void addPerson(String firstName, String lastName, String telephone){
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.getPhoneList().add(telephone);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO persons (firstname, lastname) VALUES (?, ?)");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.executeUpdate(); // добавили человека в person table

            // чтобы добавить его телефон нужно получить его id и тогда уже добавить в telephone table
            preparedStatement = connection.prepareStatement("INSERT INTO telephone (phone, personid) VALUES" +
                    "(?, (SELECT id FROM persons WHERE firstName = ? AND lastName = ?))");
            preparedStatement.setString(1, telephone);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private synchronized Person findPerson(Person person, List<Person> people){
        for(Person personTmp : people)
            if(person.equals(personTmp))
                return personTmp;
        return null;
    }




}
