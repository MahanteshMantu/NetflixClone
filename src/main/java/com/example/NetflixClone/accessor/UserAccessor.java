package com.example.NetflixClone.accessor;
import com.example.NetflixClone.accessor.model.UserDTO;
import com.example.NetflixClone.accessor.model.UserRole;
import com.example.NetflixClone.accessor.model.UserState;
import com.example.NetflixClone.exceptions.DependencyFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import javax.security.auth.DestroyFailedException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*To fetch user based on email*/
@Repository
public class UserAccessor {

    @Autowired
    static
    DataSource dataSource;
    /*this is datasource in dbconfig, bean created out from that we are autowiring*/

    /*gets user based on  his email
    if user available returns uerDTO objects else null*/
    public static UserDTO getUserByEmail(final String email) {
        String query = "SELECT userID, name, email, password, phoneNo, state, role form user where email=?";
            try (Connection connection = dataSource.getConnection()) {
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, email);
                ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                UserDTO userDTO = UserDTO.builder()
                        .userId(resultSet.getString(1))
                        .name(resultSet.getString(2))
                        .email(resultSet.getString(3))
                        .password(resultSet.getString(4))
                        .phoneNo(resultSet.getString(5))
                        .state(UserState.valueOf(resultSet.getString(6)))
                        .role(UserRole.valueOf(resultSet.getString(7)))
                        .build();
                return userDTO;
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
