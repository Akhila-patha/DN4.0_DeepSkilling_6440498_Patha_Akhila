package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Employee emp = new Employee("John", "Doe", 5000);
            session.save(emp);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        session = factory.openSession();
        List<Employee> employees = session.createQuery("FROM Employee", Employee.class).list();
        for (Employee e : employees) {
            System.out.println("Employee: " + e.getFirstName() + " " + e.getLastName());
        }
        session.close();
        factory.close();
    }
}