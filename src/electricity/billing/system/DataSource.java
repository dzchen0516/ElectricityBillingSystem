package electricity.billing.system;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataSource {
    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electricity_bill_system", "root", "cdz330011");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static String[] getUserByName(String username) {
        String sqlStmt = "select * from user where username = ?";
        String password = "";
        String usertype = "";
        String meterNum = "";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sqlStmt)) {
            statement.setString(1, username);
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next())
                {
                    password = resultSet.getString("password");
                    usertype = resultSet.getString("usertype");
                    meterNum = resultSet.getString("meter_no");
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        String[] userInfo = {password, usertype, meterNum};
        return userInfo;
    }

    public static void addUser(String meter, String username, String name,
                                   String password, String userType) {
        String sqlStmt = "insert into user values(?, ?, ?, ?, ?)";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sqlStmt)) {
            statement.setString(1, meter);
            statement.setString(2, username);
            statement.setString(3, name);
            statement.setString(4, password);
            statement.setString(5, userType);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void updateUser(String meterNum, String username,
                                  String password, String userType)
    {
        String sqlStmt = "update user set username = ?, password = ?, usertype = ? where meter_no = ?";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sqlStmt)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, userType);
            statement.setString(4, meterNum);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void addCustomer(String name, String meterNumber, String address,
                                   String city, String state, String email, String phone)
    {
        String sqlStmt = "insert into customer values(?, ?, ?, ?, ?, ?, ?)";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sqlStmt)) {
            statement.setString(1, name);
            statement.setString(2, meterNumber);
            statement.setString(3, address);
            statement.setString(4, city);
            statement.setString(5, state);
            statement.setString(6, email);
            statement.setString(7, phone);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void updateCustomer(String address, String city,
                                  String state, String email, String phone, String meterNum)
    {
        String sqlStmt = "update customer set address = ?, city = ?, state = ?, email = ?, phone = ? where meter_no = ?";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sqlStmt)) {
            statement.setString(1, address);
            statement.setString(2, city);
            statement.setString(3, state);
            statement.setString(4, email);
            statement.setString(5, phone);
            statement.setString(6, meterNum);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void addMeter(String meterNumber, String meterLocation, String meterType,
                                   String phaseCode, String billType, String billDays)
    {
        String sqlStmt = "insert into meter values(?, ?, ?, ?, ?, ?)";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sqlStmt)) {
            statement.setString(1, meterNumber);
            statement.setString(2, meterLocation);
            statement.setString(3, meterType);
            statement.setString(4, phaseCode);
            statement.setString(5, billType);
            statement.setString(6, billDays);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static HashMap<String, String> getMeterInfoByMeterNum(String meterNum)
    {
        HashMap<String, String> meterInfo = new HashMap<>();
        String sqlStmt = "select * from meter where meter_no = ?";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sqlStmt)) {
            statement.setString(1, meterNum);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    meterInfo.put("meterLocation", resultSet.getString("meter_location"));
                    meterInfo.put("meterTyoe", resultSet.getString("meter_type"));
                    meterInfo.put("phaseCode", resultSet.getString("phase_code"));
                    meterInfo.put("billType", resultSet.getString("bill_type"));
                    meterInfo.put("billDays", resultSet.getString("bill_days"));
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return meterInfo;
    }

    public static ArrayList<Object[]> getAllCustomerInfo()
    {
        ArrayList<Object[]> allCustomerInfo = new ArrayList<>();
        String sqlStmt = "select * from customer";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sqlStmt)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next())
                {
                    String name = resultSet.getString("name");
                    String meterNum = resultSet.getString("meter_no");
                    String address = resultSet.getString("address");
                    String city = resultSet.getString("city");
                    String state = resultSet.getString("state");
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone");

                    Object[] customer = {name, meterNum, address,
                                            city, state, email, phone};

                    allCustomerInfo.add(customer);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return allCustomerInfo;
    }

    public static ArrayList<Object[]> getCustomerByNameAndMeterNum(String customerName, String customerMeterNum)
    {
        ArrayList<Object[]> allCustomerInfo = new ArrayList<>();
        String sqlStmt = "select * from customer where name = ? and meter_no = ?";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sqlStmt)) {
            statement.setString(1, customerName);
            statement.setString(2, customerMeterNum);
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next())
                {
                    String name = resultSet.getString("name");
                    String meterNum = resultSet.getString("meter_no");
                    String address = resultSet.getString("address");
                    String city = resultSet.getString("city");
                    String state = resultSet.getString("state");
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone");

                    Object[] customer = {name, meterNum, address,
                            city, state, email, phone};

                    allCustomerInfo.add(customer);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return allCustomerInfo;
    }

    public static String[] getMeterNumFromCustomer()
    {
        String sqlStmt = "select * from customer";
        ArrayList<String> tmpResult = new ArrayList<String>();
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sqlStmt)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next())
                {
                    tmpResult.add(resultSet.getString("meter_no"));
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        String[] result = new String[tmpResult.size()];
        for(int i = 0; i < tmpResult.size(); i++)
        {
            result[i] = tmpResult.get(i);
        }

        return result;
    }

    public static String[] getNameFromCustomer()
    {
        String sqlStmt = "select * from customer";
        ArrayList<String> tmpResult = new ArrayList<String>();
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sqlStmt)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next())
                {
                    tmpResult.add(resultSet.getString("name"));
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        String[] result = new String[tmpResult.size()];
        for(int i = 0; i < tmpResult.size(); i++)
        {
            result[i] = tmpResult.get(i);
        }

        return result;
    }

    public static HashMap<String, String> getCustomerByMeterNum(String meterNum)
    {
        String sqlStmt = "select * from customer where meter_no = ?";
        HashMap<String, String> result = new HashMap<>();
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sqlStmt)) {
            statement.setString(1, meterNum);
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next())
                {
                    result.put("name", resultSet.getString("name"));
                    result.put("meterNum", resultSet.getString("meter_no"));
                    result.put("address", resultSet.getString("address"));
                    result.put("city", resultSet.getString("city"));
                    result.put("state", resultSet.getString("state"));
                    result.put("email", resultSet.getString("email"));
                    result.put("phone", resultSet.getString("phone"));
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public static String getUserByMeterNum(String meterNum)
    {
        String sqlStmt = "select * from user where meter_no = ?";
        String result = "";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sqlStmt)) {
            statement.setString(1, meterNum);
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next())
                {
                    result = resultSet.getString("name");
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    public static HashMap<String, Integer> getCostDetails()
    {
        String sqlStmt = "select * from cost";
        HashMap<String, Integer> result = new HashMap<>();
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sqlStmt)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next())
                {
                    result.put("cost_per_unit", Integer.parseInt(resultSet.getString("cost_per_unit")));
                    result.put("meter_rent", Integer.parseInt(resultSet.getString("meter_rent")));
                    result.put("service_charge", Integer.parseInt(resultSet.getString("service_charge")));
                    result.put("service_tax", Integer.parseInt(resultSet.getString("service_tax")));
                    result.put("government_tax", Integer.parseInt(resultSet.getString("government_tax")));
                    result.put("fixed_tax", Integer.parseInt(resultSet.getString("fixed_tax")));
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    public static String[] getMeterNumFromBill()
    {
        String sqlStmt = "select * from bill";
        ArrayList<String> tmpResult = new ArrayList<String>();
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sqlStmt)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next())
                {
                    tmpResult.add(resultSet.getString("meter_no"));
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        String[] result = new String[tmpResult.size()];
        for(int i = 0; i < tmpResult.size(); i++)
        {
            result[i] = tmpResult.get(i);
        }

        return result;
    }

    public static ArrayList<Object[]> getBillInfoByMeterNum(String meterNum)
    {
        String sqlStmt = "select * from bill where meter_no = ?";
        ArrayList<Object[]> allBillInfo = new ArrayList<>();
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sqlStmt)) {
            statement.setString(1, meterNum);
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next())
                {
                    String meterNo = resultSet.getString("meter_no");
                    String month = resultSet.getString("month");
                    String unit = resultSet.getString("unit");
                    String totalBill = resultSet.getString("total_bill");
                    String status = resultSet.getString("status");

                    Object[] bill = {meterNo, month, unit, totalBill, status};

                    allBillInfo.add(bill);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return allBillInfo;
    }



    public static ArrayList<Object[]> getDepositInfoByMonthAndMeterNum(String month, String customerMeterNum)
    {
        ArrayList<Object[]> depositInfo = new ArrayList<>();
        String sqlStmt = "select * from bill where month = ? and meter_no = ?";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sqlStmt)) {
            statement.setString(1, month);
            statement.setString(2, customerMeterNum);
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next())
                {
                    String meterNum = resultSet.getString("meter_no");
                    String billMonth = resultSet.getString("month");
                    String unit = resultSet.getString("unit");
                    String totalBill = resultSet.getString("total_bill");
                    String payStatus = resultSet.getString("status");

                    Object[] customer = {meterNum, billMonth, unit, totalBill, payStatus};

                    depositInfo.add(customer);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return depositInfo;
    }

    public static ArrayList<Object[]> getAllDepositInfo()
    {
        ArrayList<Object[]> depositInfo = new ArrayList<>();
        String sqlStmt = "select * from bill";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sqlStmt)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next())
                {
                    String meterNum = resultSet.getString("meter_no");
                    String billMonth = resultSet.getString("month");
                    String unit = resultSet.getString("unit");
                    String totalBill = resultSet.getString("total_bill");
                    String payStatus = resultSet.getString("status");


                    Object[] bill = {meterNum, billMonth, unit, totalBill, payStatus};

                    depositInfo.add(bill);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return depositInfo;
    }

    public static void addBillForMeterNum(String meterNumber, String month,
                                          String unitConsumed, String totalBill)
    {
        String sqlStmt = "insert into bill values(?, ?, ?, ?, 'Not Paid')";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sqlStmt)) {
            statement.setString(1, meterNumber);
            statement.setString(2, month);
            statement.setString(3, unitConsumed);
            statement.setString(4, totalBill);
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void updateBillForMeterNumAndMonth(String meterNumber, String month)
    {
        String sqlStmt = "update bill set status = 'Paid' where meter_no = ? and month = ?";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sqlStmt)) {
            statement.setString(1, meterNumber);
            statement.setString(2, month);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
