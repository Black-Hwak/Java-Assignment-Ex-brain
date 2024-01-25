package database;

import dto.ActorDTO;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.Map;

public class ActorDAO {
    // should this be here?
    public static final int PAGE_SZ = 10;

    // how would you handle variable number of search params
    public static CachedRowSet searchActor(ActorDTO actor) throws SQLException {
        String sql = "SELECT actor_id, first_name, last_name FROM actor WHERE first_name like ? and last_name like ? ";
        System.out.println(sql);
        CachedRowSet rs = DbUtil.createRowSet();
        rs.setPageSize(PAGE_SZ);
        rs.setCommand(sql);
        rs.setString(1, "".equals(actor.getFirst_name()) ? "%" : "%"+actor.getFirst_name() + "%");
        rs.setString(2, "".equals(actor.getLast_name()) ? "%" : "%"+actor.getLast_name() + "%");
        rs.execute();
        return rs;
    }

    public void updateCustomerInfo(Map<String, String> params) {
        // sql
        // params
        // DbUtil.update(sql, params);
    }

    public static void addNewActor(ActorDTO actor)throws SQLException {
        String first_name = actor.getFirst_name();
        String last_name = actor.getLast_name();
        // sql
        String sql = "INSERT INTO Actor (first_name, last_name) VALUES (?, ?)";
        System.out.println(sql);

        // set params
        Object[] params = {first_name, last_name};

        try {
            int rows = DbUtil.runUpdateSql(sql, params);
            if(rows == 1){
                System.out.println("Actor added successfully");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}

