package com.bank.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.bank.util.LoggerManager;




public class ConnectionFactory {
	
	
	private static Connection mCon;
	private static Properties mProps;

	/**
	 * @return the props
	 */
	public static Properties getProperties()
	{
		return mProps;
	}

	/**
	 * @param props
	 *            application properties object
	 */
	public void setProperties(Properties aProps)
	{
		mProps = aProps;
	}

	public static Connection getConnection()
	{
		try
		{
			Properties aProps = getProperties();
			Class.forName(aProps.getProperty("driver"));
			mCon = DriverManager.getConnection(aProps.getProperty("url"), aProps.getProperty("duser"), aProps.getProperty("dpass"));
		}
		catch (ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
			LoggerManager.writeLogWarning(cnfe);
		}
		catch (SQLException se)
		{
			se.printStackTrace();
			LoggerManager.writeLogWarning(se);
		}
		return mCon;
	}


}
