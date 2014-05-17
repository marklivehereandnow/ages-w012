/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author mark
 */
public class AgesCardManagerJDBC {

    AgesCard card;
    static Connection conn;
    static Statement st;
    Set effectSet;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        new AgesCardManagerJDBC().queryAndInsert();
        new AgesCardManagerJDBC().query();

    }

    public AgesCardManagerJDBC() {
        effectSet = new HashSet();
    }

    void parseEffect(String str) {
        String[] tokens = str.split(";");
        for (String token : tokens) {
            if (token.trim().length() == 0) {
                continue;
            }
//            System.out.println(""+token);
            String[] pair = token.split(":");
            String key = pair[0];
            int val = Integer.parseInt(pair[1]);
//            System.out.println(key+" "+val);
            effectSet.add(key);
        }
    }

    void parseCardEffect() {
        String[] tokens = card.getEffect().split(";");
        for (String token : tokens) {
            if (token.trim().length() == 0) {
                continue;
            }
//            System.out.println(""+token);
            String[] pair = token.split(":");
            String key = pair[0];
            int val = Integer.parseInt(pair[1]);
//            System.out.println(key + " " + val);

//            Effect keyword: [+黃點, +白點, +紅點, 石頭, 燈泡, 笑臉, 食物, 過期武器, +房屋, 音樂, +藍點, 武器]
            switch (key) {
                case "+黃點":
                    card.setEffectYellow(val);
                    break;
                case "+白點":
                    card.setEffectWhite(val);
                    break;
                case "+紅點":
                    card.setEffectRed(val);
                    break;
                case "+藍點":
                    card.setEffectBlue(val);
                    break;
                case "+房屋":
                    card.setEffectHouse(val);
                    break;
                case "食物":
                    card.setEffectFood(val);
                    break;
                case "音樂":
                    card.setEffectMusic(val);
                    break;
                case "石頭":
                    card.setEffectStone(val);
                    break;
                case "燈泡":
                    card.setEffectIdea(val);
                    break;
                case "笑臉":
                    card.setEffectSmile(val);
                    break;
                case "藍點":
                    card.setEffectBlue(val);
                    break;
                case "武器":
                    card.setEffectWeapon(val);
                    break;
                case "過期武器":
                    card.setEffectWeaponOld(val);
                    break;
                default:
                    System.out.println("***************NEED TO HANDLE => " + key);

            }

            effectSet.add(key);
        }
    }
    /*
     INSERT INTO `AGES`.`CARD_ENTITY` (
     `ID` ,
     `NAME` ,
     `AGE` ,
     `CIVIL_MILITARY` ,
     `TAG` ,
     `ACTION` ,
     `ICON_POINTS` ,
     `EFFECT` ,
     `COST` ,
     `COLOR` ,
     `CNT` ,
     `TOKEN_WHITE` ,
     `TOKEN_RED` ,
     `TOKEN_YELLOW` ,
     `TOKEN_BLUE` ,
     `EFFECT_WHITE` ,
     `EFFECT_FOOD` ,
     `EFFECT_RED` ,
     `EFFECT_MUSIC` ,
     `EFFECT_STONE` ,
     `EFFECT_IDEA` ,
     `EFFECT_SMILE` ,
     `EFFECT_HOUSE` ,
     `EFFECT_BLUE` ,
     `EFFECT_WEAPON`
     )
     VALUES (
     '1002', NULL , NULL , NULL , NULL , '', '', '', NULL , NULL , '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''
     );
     */

    public void runQuery(String sql) {
        conn = getConnection();
        try {
            st = (Statement) conn.createStatement();
            int rec = st.executeUpdate(sql);
            conn.close();
            System.out.println(rec + "records affected, done," + sql);
        } catch (Exception ex) {
            System.out.println("fail to runQuery...");
            System.out.println(ex.toString());
        } finally {
        }

    }

    public void composeSQL() {
        String s1 = "   INSERT INTO `AGES`.`CARD_ENTITY` ("
                + " `ID` ,"
                + "`NAME` ,"
                + "`AGE` ,"
                + "`CIVIL_MILITARY` ,"
                + "`TAG` ,"
                + "`ACTION` ,"
                + "`ICON_POINTS` ,"
                + "`EFFECT`  ,"
                + "`COLOR` ,"
                + "`CNT` ,"
                + "`TOKEN_BLUE` ,"
                + "`TOKEN_RED` ,"
                + "`TOKEN_WHITE` ,"
                + "`TOKEN_YELLOW` ,"
                + "`EFFECT_BLUE` ,"
                + "`EFFECT_FOOD` ,"
                + "`EFFECT_HOUSE` ,"
                + "`EFFECT_IDEA` ,"
                + "`EFFECT_MUSIC` ,"
                + "`EFFECT_RED` ,"
                + "`EFFECT_SMILE` ,"
                + "`EFFECT_STONE` ,"
                + "`EFFECT_WEAPON` ,"
                + "`EFFECT_WHITE`) ";
        StringBuilder sb = new StringBuilder();
        sb.append(s1);
        sb.append("VALUES(");
        sb.append("'").append(card.getId()).append("',");
        sb.append("'").append(card.getName()).append("',");
        sb.append("'").append(card.getAge()).append("',");
        sb.append("'").append(card.getCivilMilitary()).append("',");
        sb.append("'").append(card.getTag()).append("',");
        sb.append("'").append(card.getAction()).append("',");
        sb.append("'").append(card.getIconPoints()).append("',");
        sb.append("'").append(card.getEffect()).append("',");
        sb.append("'").append(card.getColor()).append("',");
        sb.append("'").append(card.getCnt()).append("',");
        sb.append("'").append(card.getTokenBlue()).append("',");
        sb.append("'").append(card.getTokenRed()).append("',");
        sb.append("'").append(card.getTokenWhite()).append("',");
        sb.append("'").append(card.getTokenYellow()).append("',");
        sb.append("'").append(card.getEffectBlue()).append("',");
        sb.append("'").append(card.getEffectFood()).append("',");
        sb.append("'").append(card.getEffectHouse()).append("',");
        sb.append("'").append(card.getEffectIdea()).append("',");
        sb.append("'").append(card.getEffectMusic()).append("',");
        sb.append("'").append(card.getEffectRed()).append("',");
        sb.append("'").append(card.getEffectSmile()).append("',");
        sb.append("'").append(card.getEffectStone()).append("',");
        sb.append("'").append(card.getEffectWeapon()).append("',");
        sb.append("'").append(card.getEffectWhite()).append("'");

        sb.append(");");

        System.out.println(sb.toString());

    }

    public void queryAndInsert() {
        System.out.println("getting Card info...");
        int counter = 0;

        conn = getConnection();
        try {
            String sql = ""
                    + "SELECT "
                    + "  ID,"
                    + "  NAME,"
                    + "  AGE, "
                    + "  CIVIL_MILITARY, "
                    + "  TAG, "
                    + "  ACTION, "
                    + "  ICON_POINTS,"
                    + "  EFFECT,"
                    + "  COLOR,"
                    + "  CNT "
                    + "FROM  CARD "
                    + "ORDER BY ID";
            st = (Statement) conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                counter++;
                card = new AgesCard();
                card.setId(rs.getInt(1));
                card.setName(rs.getString(2));
                card.setAge(rs.getInt(3));
                card.setCivilMilitary(rs.getString(4));
                card.setTag(rs.getString(5));
                card.setAction(rs.getString(6));
                card.setIconPoints(rs.getString(7));
                card.setEffect(rs.getString(8));
                card.setColor(rs.getString(9));
                card.setCnt(rs.getInt(10));

                parseCardEffect();
//                CardEntityManager.insertCardEntity(card);

                System.out.println(card.toString());
//                composeSQL();

// System.out.println("Effect keyword: " + effectSet);
            }
            System.out.println("Effect keyword: " + effectSet);

            conn.close();

        } catch (Exception ex) {
            System.out.println("fail to query...counter=" + counter);
            System.out.println(ex.toString());
        } finally {
        }

    }

    public void query() {

        conn = getConnection();
        try {
            String sql = "SELECT * FROM AGES_CARD ORDER BY SEQ";
            st = (Statement) conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.println("checking result...");
            System.out.println("");
            System.out.println("");
            System.out.println("");

            while (rs.next()) {
                card = new AgesCard();

//                System.out.println("" + rs.getInt("ID"));
                card.setSeq(rs.getInt("SEQ"));
                card.setId(rs.getInt("ID"));
                card.setName(rs.getString("NAME"));
                card.setAge(rs.getInt("AGE"));
                card.setCivilMilitary(rs.getString("CIVIL_MILITARY"));
                card.setTag(rs.getString("TAG"));
                card.setAction(rs.getString("ACTION"));
                card.setIconPoints(rs.getString("ICON_POINTS"));
                card.setEffect(rs.getString("EFFECT"));
                card.setCost(rs.getString("COST"));
                card.setColor(rs.getString("COLOR"));
                card.setCnt(rs.getInt("CNT"));
                card.setTokenWhite(rs.getInt("TOKEN_WHITE"));
                card.setTokenRed(rs.getInt("TOKEN_RED"));
                card.setTokenYellow(rs.getInt("TOKEN_YELLOW"));
                card.setTokenBlue(rs.getInt("TOKEN_BLUE"));
                card.setEffectWhite(rs.getInt("EFFECT_WHITE"));
                card.setEffectFood(rs.getInt("EFFECT_FOOD"));
                card.setEffectRed(rs.getInt("EFFECT_RED"));
                card.setEffectMusic(rs.getInt("EFFECT_MUSIC"));
                card.setEffectStone(rs.getInt("EFFECT_STONE"));
                card.setEffectIdea(rs.getInt("EFFECT_IDEA"));
                card.setEffectSmile(rs.getInt("EFFECT_SMILE"));
                card.setEffectHouse(rs.getInt("EFFECT_HOUSE"));
                card.setEffectBlue(rs.getInt("EFFECT_BLUE"));
                card.setEffectWeapon(rs.getInt("EFFECT_WEAPON"));
                card.setEffectWeaponOld(rs.getInt("EFFECT_WEAPON_OLD"));
                card.setEffectYellow(rs.getInt("EFFECT_YELLOW"));
                card.setCostPeople(rs.getInt("COST_PEOPLE"));
                card.setCostFoot(rs.getInt("COST_FOOT"));
                card.setCostWonder(rs.getInt("COST_WONDER"));
                card.setCostMilitary(rs.getInt("COST_MILITARY"));
                card.setCostHorse(rs.getInt("COST_HORSE"));
                card.setCostCannon(rs.getInt("COST_CANNON"));
                card.setCostPeace(rs.getInt("COST_PEACE"));
                card.setCostRevolution(rs.getInt("COST_REVOLUTION"));
                card.setCostRed(rs.getInt("COST_RED"));
                card.setCostStone(rs.getInt("COST_STONE"));
                card.setCostIdea(rs.getInt("COST_IDEA"));
                card.setCostFood(rs.getInt("COST_FOOD"));
                card.setCostMusic(rs.getInt("COST_MUSIC"));

                parseCardEffect();
                parseIconPoints();
                System.out.println(" " + card.toString(999));
            }

            conn.close();

        } catch (Exception ex) {
            System.out.println("fail to query...");
            System.out.println(ex.toString());
            ex.printStackTrace();
        } finally {
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");

    }

    void parseIconPoints() {
//        int counter=0;
        String[] tokens = card.getIconPoints().split(";");
        for (String token : tokens) {
            if (token.trim().length() == 0) {
                continue;
            }
//            System.out.println(""+token);
            String[] pair = token.split(":");
            String key = pair[0];
            int val = -999;
            try {
                if (key.equals("奇蹟石頭")) {
                    val = 98765;
                } else {

                    val = Integer.parseInt(pair[1]);
                }
            } catch (Exception ex) {
                System.out.println(card.getId() + " IconPoints is " + card.getIconPoints());

                System.out.println("token is ###" + token + "###");
                ex.printStackTrace();
                System.exit(-1);
            }
//            System.out.println(key + " " + val);

//            Effect keyword: [+黃點, +白點, +紅點, 石頭, 燈泡, 笑臉, 食物, 過期武器, +房屋, 音樂, +藍點, 武器]
            switch (key) {
                case "擴充人口":
                    card.setCostPeople(val);
                    break;
                case "步":
                    card.setCostFoot(val);
                    break;
                case "奇蹟石頭":
                    card.setCostWonder(val);
                    break;
                case "軍事牌":
                    card.setCostMilitary(val);
                    break;
                case "馬":
                    card.setCostHorse(val);
                    break;
                case "炮":
                    card.setCostCannon(val);
                    break;
                case "和平燈炮":
                    card.setCostPeace(val);
                    break;
                case "革命燈泡":
                    card.setCostRevolution(val);
                    break;
                case "消耗紅點":
                    card.setCostRed(val);
                    break;
                case "石頭":
                    card.setCostStone(val);
                    break;
                case "燈泡":
                    card.setCostIdea(val);
                    break;
                case "食物":
                    card.setCostFood(val);
                    break;
                case "音樂":
                    card.setCostMusic(val);
                    break;

                default:
                    System.out.println("***************NEED TO HANDLE => " + key);
                    System.exit(-1);
            }
        }
    }

    public void insert(String sql) {

        conn = getConnection(); // 首先要获取连接，即连接到数据库  

        try {
//            String sql = "INSERT INTO staff(name, age, sex,address, depart, worklen,wage)"  
//                    + " VALUES ('Tom1', 32, 'M', 'china','Personnel','3','3000')";  // 插入数据的sql语句  

            st = (Statement) conn.createStatement();    // 创建用于执行静态sql语句的Statement对象  

            int count = st.executeUpdate(sql);  // 执行插入操作的sql语句，并返回插入数据的个数  

            System.out.println("向staff表中插入 " + count + " 条数据"); //输出插入操作的处理结果  

            conn.close();   //关闭数据库连接  

        } catch (SQLException e) {
            System.out.println("插入数据失败" + e.getMessage());
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动  

            conn = DriverManager.getConnection(
                    "jdbc:mysql://2nd2go.org:3306/AGES", "max", "Taipei2014");// 创建数据连接  

        } catch (Exception e) {
            System.out.println("数据库连接失败" + e.getMessage());
        }
        return conn; //返回所建立的数据库连接  
    }

}
