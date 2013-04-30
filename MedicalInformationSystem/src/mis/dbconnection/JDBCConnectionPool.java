package mis.dbconnection;

import java.util.Queue;

import java.sql.*;
import java.util.*;

//import java.io.*;

class ConnectionReaper extends Thread {

	private JDBCConnectionPool pool;
	private final long delay = 30000;

	ConnectionReaper(JDBCConnectionPool pool) {
		this.pool = pool;
	}

	public void run() {
		while (true) {
			try {
				sleep(delay);
			} catch (InterruptedException e) {
			}
			pool.reapConnections();
			pool.normalizeConnections();
		}
	}
}

public class JDBCConnectionPool {

	private Queue<JDBCConnection> poolConnections;
	private Queue<JDBCConnection> inUseConnections;
	private String url, user, password;
	final private long timeout = 6000;
	private ConnectionReaper reaper;
	private int poolSize;

	public JDBCConnectionPool(String url, String user, String password,
			int poolSize) throws SQLException {
		this.url = url;
		this.user = user;
		this.password = password;
		this.poolSize = poolSize;
		poolConnections = new LinkedList<JDBCConnection>();
		inUseConnections = new LinkedList<JDBCConnection>();

		for (int i = 0; i < this.poolSize; i++) {

			JDBCConnection c = null;
			Connection conn = DriverManager.getConnection(url, user, password);
			c = new JDBCConnection(conn, this);
			//System.out.println("i = " + i);
			//c.setAutoCommit(false);
			poolConnections.add(c);

		}

		reaper = new ConnectionReaper(this);
		reaper.start();
	}

	public synchronized void normalizeConnections() {
		// TODO Auto-generated method stub
		int extraConnections = poolConnections.size() - poolSize;
		if(extraConnections > 0) {
			for(int i=0;i<extraConnections;i++) {
				poolConnections.remove();
			}
		}
		
	}

	public synchronized void reapConnections() {

		//System.out.println("Connection Reaped1: " + (inUseConnections.size()));
		Iterator<JDBCConnection> connlist = inUseConnections.iterator();
		//System.out.println("Pool Connections: " + poolConnections.size());
		//System.out.println("Used Connections: " + inUseConnections.size());
		//System.out.println(connlist.toString());
		//System.out.println(connlist.hasNext());
		while ((connlist != null) && (connlist.hasNext())) {
			//int i = 0;
			JDBCConnection conn = (JDBCConnection) connlist.next();

			if (((System.currentTimeMillis() - conn.getLastUse()) > timeout)
					&& (!conn.validate())) {
				removeConnection(conn);
				//System.out.println("Connection Reaped: " + i++);
			}

		}
	}

	public synchronized void closeConnections() {

		Iterator<JDBCConnection> connlist = inUseConnections.iterator();

		while ((connlist != null) && (connlist.hasNext())) {
			JDBCConnection conn = (JDBCConnection) connlist.next();
			removeConnection(conn);
		}
	}

	private synchronized void removeConnection(JDBCConnection conn) {
		inUseConnections.remove(conn);
	}

	public synchronized Connection getConnection() throws SQLException {

		JDBCConnection c = null;
		if ((c = poolConnections.poll()) != null) {
			inUseConnections.add(c);
		} else {
			Connection conn = DriverManager.getConnection(url, user, password);
			c = new JDBCConnection(conn, this);

			inUseConnections.add(c);
		}

		//System.out.println("Pool Connections: " + poolConnections.size());
		//System.out.println("Used Connections: " + inUseConnections.size());
		return c;
	}

	public synchronized void returnConnection(JDBCConnection conn) {
		// conn.expireLease();
		poolConnections.offer(conn);
		inUseConnections.remove(conn);
	}
}