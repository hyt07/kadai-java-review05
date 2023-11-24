package lesson9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Review05 {

    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // DBに接続
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/kadaidb?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "JfY7R430Pldj"
                    );

            // DBとのやり取り
            String sql = "SELECT * FROM person WHERE id = ?";
            pstmt = con.prepareStatement(sql);
            System.out.println("検索キーワードを入力してください > ");
            int input = keyInNum();
            pstmt.setInt(1, input);
            rs = pstmt.executeQuery();

            // 結果の表示
            while(rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println(name);
                System.out.println(age);
            }

            // 例外処理
        } catch (ClassNotFoundException e) {
            System.err.println("JDBCドライバのロードに失敗しました");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("データベースに以上が発生しました");
            e.printStackTrace();
        } finally {
            if( rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("PreparedStatementを閉じるときにエラーが発生しました");
                    e.printStackTrace();
                }
        }
            if( pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.err.println("ResultSetを閉じるときにエラーが発生しました");
                    e.printStackTrace();
                }
            if( con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("データベース切断時にエラーが発生しました");
                    e.printStackTrace();
                }
            }
        }
    }
}
    private static int keyInNum() {
        String line;
        int result = 0;
        try {
            BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
            line= key.readLine();
            result = Integer.parseInt(line);
        } catch (IOException e) {
        } catch (NumberFormatException e) {
        }
        return result;
    }
}
