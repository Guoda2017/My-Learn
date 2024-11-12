package com.guo;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : fengbo.guo
 * @date : 2024-11-11 17:11
 * @Description :
 */
@Slf4j
public class TestJdbc {

    public static void main(String[] args) {
        // 数据库1的配置
        String url1 = "jdbc:mysql://rm-3ns8l5ow758010z4x.mysql.rds.aliyuncs.com:3303/za_bank_stock_trade";
        String user1 = "za_bank_stock_trade";
        String password1 = "LR^XzsVgooC5Scdn";

        List<String> userIdList = new ArrayList<>();

        // 分页参数
        int pageNum = 1; // 页码
        int pageSize = 5000; // 每页大小

        // 从数据库1分页拉取数据
        boolean hasMoreData = true;
        while (hasMoreData) {
            hasMoreData = fetchDataFromDb1(url1, user1, password1, pageNum, pageSize, userIdList);
            pageNum++; // 自增页码以获取下一页的数据
            userIdList = userIdList.stream().distinct().collect(Collectors.toList());
            System.out.println("需要处理的userIdList:" + userIdList);
        }

    }

    private static boolean fetchDataFromDb1(String url1, String user1, String password1, int pageNum, int pageSize, List<String> userIdList) {

        // 数据库2的配置
        String url2 = "jdbc:mysql://rm-3ns8l5ow758010z4x.mysql.rds.aliyuncs.com:3303/za_bank_fund_account";
        String user2 = "za_bank_ftc_uat";
        String password2 = "QYhKw0r^6FXP7N7L";
        Map<String, String> holdingMap = new HashMap();

        String sql1 = "SELECT user_id,account_id FROM stk_holding where is_deleted ='N' LIMIT ?, ?";

        try (Connection conn1 = DriverManager.getConnection(url1, user1, password1);
             PreparedStatement pstmt1 = conn1.prepareStatement(sql1)) {

            pstmt1.setInt(1, (pageNum - 1) * pageSize);
            pstmt1.setInt(2, pageSize);
            ResultSet rs = pstmt1.executeQuery();

            while (rs.next()) {
                String userId = rs.getString("user_id");
                String accountId = rs.getString("account_id");
                holdingMap.put(userId, accountId);
            }

            System.out.println("pageSize:" + pageSize + "pageNum:" + pageNum);

            // 对于数据库1中的每条数据，检查数据库2中是否存在
            Map<String, String> userMap = checkDataInDb2(url2, user2, password2, holdingMap);

            List<String> notInPageList = holdingMap.keySet().stream().filter(key -> !userMap.get(key).equals(holdingMap.get(key))).collect(Collectors.toList());

//            System.out.println("holdingMap:" + holdingMap);
//            System.out.println("userMap:" + userMap);
            System.out.println("notInPageList:" + notInPageList);

//            if (CollectionUtils.isNotEmpty(notInPageList)) {
//                System.out.println("Data: " + notInPageList + " does not exist in DB2.");
//                String sql2 = "update stk_holding set is_deleted ='Y' where  user_id = ?";
//                for (String userId : notInPageList) {
//
//                    PreparedStatement pstmt2 = conn1.prepareStatement(sql2);
//
//                    pstmt2.setString(1, userId);
//                    pstmt2.executeUpdate();
//                    System.out.println("删除了数据" + userId);
//                }
//            }

            // 数据不存在时的操作
            userIdList.addAll(notInPageList);
            return !rs.isLast();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static Map<String, String> checkDataInDb2(String url2, String user2, String password2, Map<String, String> holdingMap) {
        Map<String, String> userMap = new HashMap<>();
        String sql2 = "SELECT bank_user_id,broker_account_id FROM user_invest_account WHERE account_type = 'stock' and bank_user_id " +
                "IN (" + holdingMap.keySet().stream().map(v -> "?").collect(Collectors.joining(",")) + ")";
        try (Connection conn2 = DriverManager.getConnection(url2, user2, password2);
             PreparedStatement pstmt2 = conn2.prepareStatement(sql2)) {

            int parameterIndex = 1;
            for (String value : holdingMap.keySet()) {
                pstmt2.setString(parameterIndex++, value);
            }
            ResultSet rs = pstmt2.executeQuery();

            while (rs.next()) {
                userMap.put(rs.getString("bank_user_id"), rs.getString("broker_account_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userMap;
    }
}
