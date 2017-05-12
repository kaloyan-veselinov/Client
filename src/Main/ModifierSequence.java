public class ModifierSequence{
	/*
	maj
	ctrl
	alt
	capslock
	maj+ctrl
	maj+alt
	maj+capslock
	ctrl+maj
	ctrl+alt
	ctrl+capslock
	alt+maj
	alt+ctrl
	alt+capslock
	capslock+maj
	capslock+ctrl
	capslock+alt
	maj+ctrl+alt
	maj+ctrl+capslock
	maj+alt+ctrl
	maj+alt+capslock
	maj+capslock+ctrl
	maj+capslock+alt
	ctrl+maj+alt
	ctrl+maj+cpaslock
	ctrl+alt+maj
	ctrl+alt+capslock
	ctrl+capslock+maj
	ctrl+capslock+alt
	alt+maj+ctrl
	alt+maj+cpaslock
	alt+ctrl+maj
	alt+ctrl+capslock
	alt+capslock+maj
	alt+capslock+ctrl
	capslock+maj+ctrl
	capslock+maj+alt
	capslock+ctrl+maj
	capslock+ctrl+alt
	capslock+alt+maj
	cpaslock+alt+ctrl
	maj+ctrl+alt+capslock
	maj+ctrl+capslock+alt
	maj+alt+ctrl+capslock
	maj+alt+capslock+ctrl
	maj+capslock+ctrl+alt
	maj+capslock+alt+ctrl
	ctrl+maj+alt+capslock
	ctrl+maj+capslock+alt
	ctrl+alt+maj+capslock
	ctrl+alt+capslock+maj
	ctrl+capslock+maj+alt
	ctrl+cpaslock+alt+maj
	alt+maj+ctrl+capslock
	alt+maj+capslock+ctrl
	alt+ctrl+maj+capslock
	alt+ctrl+capslock+maj
	alt+capslock+maj+ctrl
	alt+capslock+ctrl+maj
	capslock+maj+ctrl+alt
	capslock+maj+alt+ctrl
	capslock+ctrl+maj+alt
	capslock+ctrl+alt+maj
	capslock+alt+maj+ctrl
	capslock+alt+ctrl+maj
	*/
	
	private final int MAJ = 1;
	private final int CTRL = 2;
	private final int ALT = 3;
	private final int CAPSLOCK = 4;
	private final int NULL = 0;
	
	public static int getSequence(int[]modifiers){
		
		switch (modifieurs[0]){
		case NULL:
			return 0;
			break;
			
		case MAJ :
			switch (modifieurs[1]){
			case NULL:
				return 1;
				break;
				
			case CTRL:
				switch (modifieurs[2]){
				case NULL:
					return 5;
					break;
					
				case ALT :
					switch (modifieurs[3]){
					case NULL :
						return 17;
						break;
					
					case CAPSLOCK :
						return 41;
						break;
					}
					
				case CAPSLOCK :
					switch modifieur[3]{
					case NULL:
						return 18;
						break;
					
					case ALT :
						return 42;
						break;
					}
					
				}
			case ALT :
				switch (modifieurs[2]){
				case NULL:
					return 6;
					break;
					
				case CTRL :
					switch (modifieurs[3]){
					case NULL:
						return 19;
						break;
						
					case CAPSLOCK :
						return 43;
					}
					
					
				case CAPSLOCK :
					switch (modifieurs[3]){
					case NULL :
						return 20;
						break;
						
					case CTRL :
						return 44;
						break;
					}
					
				}
			case CAPSLOCK :
				switch (modifieurs[2]){
				case NULL:
					return 7;
					break;
					
				case CTRL :
					switch (modifieurs[3]){
					case NULL :
						return 21;
						break;
						
					case ALT :
						return 45;
						break;
					}
					
					
					
				}
			}
		}
	}
	
	
	
}


