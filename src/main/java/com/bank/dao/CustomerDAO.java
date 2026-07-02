package com.bank.dao;

import com.bank.config.DBConnection;
import com.bank.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CustomerDAO {

    public boolean addCustomer(Customer customer) {

        String sql = """
                INSERT INTO customers
                (first_name,last_name,gender,date_of_birth,
                 phone,email,address,city,state,pincode)
                VALUES (?,?,?,?,?,?,?,?,?,?)
                """;

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getGender());
            ps.setDate(4, customer.getDateOfBirth());
            ps.setString(5, customer.getPhone());
            ps.setString(6, customer.getEmail());
            ps.setString(7, customer.getAddress());
            ps.setString(8, customer.getCity());
            ps.setString(9, customer.getState());
            ps.setString(10, customer.getPincode());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }

    }

}