//package com.guo;
//
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeUnit;
//import java.util.stream.Collectors;
//
///**
// * @author : fengbo.guo
// * @date : 2024-11-11 17:11
// * @Description :
// */
//@Slf4j
//public class TestJdbc {
//
//    public static void main(String[] args) {
//        // 数据库1的配置
//        String url1 = "jdbc:mysql://rm-3ns8l5ow758010z4x.mysql.rds.aliyuncs.com:3303/za_bank_stock_trade";
//        String user1 = "za_bank_stock_trade";
//        String password1 = "LR^XzsVgooC5Scdn";
//
//
//        // 分页参数
//        int pageNum = 1; // 页码
//        int pageSize = 100; // 每页大小
//
//        ExecutorService executor = Executors.newFixedThreadPool(20);
//
//        // 从数据库1分页拉取数据
//        boolean hasMoreData = true;
//        List<Future<List<String>>> futures = new ArrayList<>();
//        while (hasMoreData) {
//            hasMoreData = fetchDataFromDb1(futures,url1, user1, password1, pageNum, pageSize);
//            pageNum++; // 自增页码以获取下一页的数据
//        }
//        // 关闭线程池
//        executor.shutdown();
//        try {
//            executor.awaitTermination(60, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @SneakyThrows
//    private static boolean fetchDataFromDb1(List<Future<List<String>>> futures, String url1, String user1, String password1, int pageNum, int pageSize) {
//        // 数据库2的配置
//        String url2 = "jdbc:mysql://rm-3ns8l5ow758010z4x.mysql.rds.aliyuncs.com:3303/za_bank_stock_trade";
//        String user2 = "za_bank_stock_trade";
//        String password2 = "LR^XzsVgooC5Scdn";
//        List<String> list = new ArrayList<>();
//        String sql1 = "SELECT account_id FROM stk_holding where is_deleted ='N' and hold_qty >0 group by account_id LIMIT ?, ?";
//
//        int total = 0;
//
//        try (Connection conn1 = DriverManager.getConnection(url1, user1, password1);
//             PreparedStatement pstmt1 = conn1.prepareStatement(sql1)) {
//
//            pstmt1.setInt(1, (pageNum - 1) * pageSize);
//            pstmt1.setInt(2, pageSize);
//            ResultSet rs = pstmt1.executeQuery();
//
//            while (rs.next()) {
//                String accountId = rs.getString("account_id");
//                total = total + 1;
//                // 提交任务到线程池
//                futures.a
//                Future<List<String>> submit = executor.submit(() -> checkDataInDb2(url2, user2, password2, accountId));
//
//                log.info("account_id" + accountId);
//                log.info("total" + total);
//                log.info("list" + list.addAll(submit.get()));
//            }
//
//            System.out.println("pageSize:" + pageSize + "pageNum:" + pageNum);
//
//            // 数据不存在时的操作
//            return !rs.isLast();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    private static List<String> checkDataInDb2(String url2, String user2, String password2, String accountId) {
//        List<String> historyList = new ArrayList<>();
//        List<String> errorList = new ArrayList<>();
//        String sql2 = "SELECT stock_code from stk_holding_history where trade_date = '2024-11-26' and is_deleted = 'N' and account_id = ?";
//        String sql3 = "SELECT stock_code from stk_holding where hold_qty > 0 and is_deleted = 'N' and account_id = ?";
//
//        try (Connection conn2 = DriverManager.getConnection(url2, user2, password2);
//             PreparedStatement pstmt2 = conn2.prepareStatement(sql2)) {
//
//            pstmt2.setString(1, accountId);
//            ResultSet rs = pstmt2.executeQuery();
//
//            while (rs.next()) {
//                String stockCode = rs.getString("stock_code");
//                historyList.add(stockCode);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        log.info("accountId:{} historyList:{}", accountId, historyList);
//
//        try (Connection conn3 = DriverManager.getConnection(url2, user2, password2);
//             PreparedStatement pstmt3 = conn3.prepareStatement(sql3)) {
//
//            pstmt3.setString(1, accountId);
//            ResultSet rs = pstmt3.executeQuery();
//
//            while (rs.next()) {
//                String stockCode = rs.getString("stock_code");
//                if (!historyList.contains(stockCode)) {
//                    errorList.add(accountId + stockCode);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        // 处理 errorList，例如打印或存储
//        log.info("Error List for {}: {}", accountId, errorList);
//        return errorList;
//    }
//}
