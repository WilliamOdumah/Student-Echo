package comp3350.student_echo.application;

public class Main {
	private static String dbName = "StudentEchoDB";
	public static void setDBPathName(final String name) {
		try {
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		dbName = name;
	}
	public static String getDBPathName() {
		return dbName;
	}
}
