package com.bank.dao;

import com.bank.config.DBConnection;
import com.bank.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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


    public List<Customer> getAllCustomers() {

            List<Customer> customers = new ArrayList<>();

            String sql = "SELECT * FROM customers";

            try {

                Connection conn = DBConnection.getConnection();

                PreparedStatement ps = conn.prepareStatement(sql);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    Customer customer = new Customer();

                    customer.setCustomerId(rs.getInt("customer_id"));
                    customer.setFirstName(rs.getString("first_name"));
                    customer.setLastName(rs.getString("last_name"));
                    customer.setGender(rs.getString("gender"));
                    customer.setDateOfBirth(rs.getDate("date_of_birth"));
                    customer.setPhone(rs.getString("phone"));
                    customer.setEmail(rs.getString("email"));
                    customer.setAddress(rs.getString("address"));
                    customer.setCity(rs.getString("city"));
                    customer.setState(rs.getString("state"));
                    customer.setPincode(rs.getString("pincode"));

                    customers.add(customer);
                }

            } catch (Exception e) {

                e.printStackTrace();
            }

            return customers;
   }
   public Customer getCustomerById(int customerId) {

            String sql = "SELECT * FROM customers WHERE customer_id = ?";

            try {

                Connection conn = DBConnection.getConnection();

                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setInt(1, customerId);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    Customer customer = new Customer();

                    customer.setCustomerId(rs.getInt("customer_id"));
                    customer.setFirstName(rs.getString("first_name"));
                    customer.setLastName(rs.getString("last_name"));
                    customer.setGender(rs.getString("gender"));
                    customer.setDateOfBirth(rs.getDate("date_of_birth"));
                    customer.setPhone(rs.getString("phone"));
                    customer.setEmail(rs.getString("email"));
                    customer.setAddress(rs.getString("address"));
                    customer.setCity(rs.getString("city"));
                    customer.setState(rs.getString("state"));
                    customer.setPincode(rs.getString("pincode"));

                    return customer;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

}

