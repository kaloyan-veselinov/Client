package Session;

import java.util.ArrayList;

import Database.Insert;

/*
 * Objet permettant la gestion des sessions durant l'l'execution du programme  
 */

public class SessionManager {
	
	private Session currentSession; // session en cours
	private ArrayList <Session>prevSessions; // liste des sessions précédantes
	
	public SessionManager(){
		prevSessions = new ArrayList<Session>();
		currentSession = new Session(this);
	}
	
	// termine une session et l'ajoute à la liste des sessions terminées
	public void endCurrentSession(){
		currentSession.setRunning(false);
		//le succès de la session est défini à partir du succès de la dernière tentative
		currentSession.setSuccess(currentSession.getPasswordTries().get(currentSession.getPasswordTries().size()-1).isSuccess());
		Insert.addSession(currentSession,Main.Main.conn);
		prevSessions.add(new Session(currentSession,this));
		
	}
	
	// créé une nouvelle session en terminant la précédante si elle existe.
	public void newSession(){
		if(currentSession!=null){
			endCurrentSession();
		}
		currentSession = new Session(this);
	}
	
	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public ArrayList<Session> getPrevSessions() {
		return prevSessions;
	}

	public void setPrevSessions(ArrayList<Session> prevSessions) {
		this.prevSessions = prevSessions;
	}

}
