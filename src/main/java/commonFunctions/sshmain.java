package commonFunctions;

import com.jcraft.jsch.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class sshmain {
	public static Session session = null;
	public static ChannelExec channel = null;
	public static String responseString = null;
	public static String query;
	public static String result;
	public static String propertyFilePath = System.getProperty("user.dir") + File.separator + "ssh_config.properties";
	public static Properties prop = new Properties();

	/*
	 * This method used to connect SSH
	 */
	public static void sshConnect(String username, String password, String host, int port) throws Exception {

		session = new JSch().getSession(username, host, port);
		session.setPassword(password);
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		//config.put("PreferredAuthentications", "password");
		session.setConfig(config);

		session.connect();
		System.out.println("session33");
	}

	/*
	 * THis method used to connect execute DB command
	 */
	public static String sshExecuteCommand(String command) throws JSchException, InterruptedException {
		try {
			channel = (ChannelExec) session.openChannel("exec");
			System.out.println("session33" + channel);
			channel.setCommand(command);

			ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
			channel.setOutputStream(responseStream);
			channel.connect();
			while (channel.isConnected()) {

				Thread.sleep(100);
			}
			System.out.println("session33" + command);
			responseString = responseStream.toString();
			System.out.println("responseString" + responseString);
		} finally {
//			if (session != null) {
//				session.disconnect();
//
//			}
//			if (channel != null) {
//				channel.disconnect();
//
//			}
		}
		return (responseString);
	}

	public static void main(String[] args) throws Exception {

		String vpnusername = "usha.nanthini";
		String vpnpassword = "Gacibhac4";
		String sshhost = "10.224.121.24";
		String db_name = "tadpoleNew";
		String db_user = "root";
		String db_pass = "gupshup";

		int sshport = 22;
		String rdsHostname = "sl-mysql.servicesdbmq.svc.cluster.local";
		int rdsPort = 3306;

		sshConnect(vpnusername, vpnpassword, sshhost, sshport);
		//query = "\"select * from customers where user_id = '206';\"";
		query = "\"update campaigns set status = 3 where id = 2130 and shop_id = 174;\"";
		// sshExecuteCommand("aws eks update-kubeconfig --name
		// EKS-superlemon-qa-Cluster");
		//sshExecuteCommand("kubectl config set-context --current --namespace=servicesdbmq");
		//sshExecuteCommand("kubectl exec -it sl-mysql-0 -- /bin/bash");
		
		int forwardedPort = session.setPortForwardingL(0, rdsHostname, rdsPort);
		System.out.println("SSH Connected. Port forwarding: " + forwardedPort);
		// RDS database connection setup
		String jdbcUrl = "jdbc:mysql://localhost:" + forwardedPort + "/" + db_name;
		Connection connection = DriverManager.getConnection(jdbcUrl, db_user, db_pass);
		result = sshExecuteCommand("mysql -u" + db_user + " -p" + db_pass + " " + db_name + " -e" + query);
//		sshExecuteCommand("mysql -u" + db_user + " -p" + db_pass + " " + db_name);
//		result = sshExecuteCommand(query);
		System.out.println(result);
	}
}