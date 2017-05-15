package Session;

import java.util.ArrayList;

/*
 * Objet permettant la gestion des sessions durant l'l'execution du programme  
 */

public class SessionManager {
	
	private Session currentSession; // session en cours
	private ArrayList <Session>prevSessions; // liste des sessions précédantes
	
	public SessionManager(){
		prevSessions = new ArrayList<Session>();
		newSession();
	}
	
	// termine une session et l'ajoute à la liste des sessions terminées
	public void endCurrentSession(){
		currentSession.setRunning(false);
		prevSessions.add(new Session(currentSession,this));
		
	}
	
	// créé une nouvelle session en terminant la précédante si elle existe.
	public void newSession(){
		if(currentSession!=null){
			endCurrentSession();
		}
		currentSession = new Session(this);
	}

}
