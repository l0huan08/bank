package bank.dao;

/**
 * The Utility class. Functions including:
 *   Type convertion
 * @author huang li
 * 2014.9.18
 */
class DaoUtility {
	public static java.sql.Date dateToSqlDate(java.util.Date date) {
	    return new java.sql.Date(date.getTime());
	}
}
