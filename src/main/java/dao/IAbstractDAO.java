package dao;
import java.sql.ResultSet;
import java.sql.Statement;

public interface IAbstractDAO  {
    void close(ResultSet resultSet, Statement statement);
}
