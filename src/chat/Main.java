package chat;

import java.util.Scanner;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;
public class Main{

	//AbstractXMPPConnection connection;
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {

		XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder();
		configBuilder.setUsernameAndPassword("P0741", "123456");
		configBuilder.setXmppDomain("atomic.sivasdetay.com");
		configBuilder.setHost("127.0.0.1");
		configBuilder.setPort(5222);
		configBuilder.setDebuggerEnabled(true);
		configBuilder.setSecurityMode(SecurityMode.disabled);
		AbstractXMPPConnection connection = new XMPPTCPConnection(configBuilder.build());

		// Connect to the server

		try {

			connection.connect();
			System.out.println("Sunucuya Baðlandý.");
			
			connection.login();
			System.out.println("Login Oldu.");
			
			ChatManager chatManager = ChatManager.getInstanceFor(connection);
			chatManager.addListener(new IncomingChatMessageListener() {
				
				@Override
				public void newIncomingMessage(EntityBareJid from, Message message, Chat chat) {
					System.out.println(message.getBody());
					
				}
			});
			
			EntityBareJid jid = JidCreate.entityBareFrom("p0002@atomic.sivasdetay.com/Spark");
			Chat chat = chatManager.chatWith(jid);
			
			Scanner sc = new Scanner(System.in);
	        System.out.println("Bir þeyler yaz.");
	        while(sc.hasNextLine())
	        	chat.send(sc.nextLine());
	        
	        
			
			/*
			IQ iqRequest = new IQ();
			iqRequest.setType(IQ.Type.get);
			Element child = iqRequest.setChildElement("customIQ", "detayopenfireplugin:iq:customiq");
			child.addElement("name").setText("Yusuf");
			child.addElement("surname").setText("ÇAKAL");
			*/
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + " HATA");
		}
	}
	
}
