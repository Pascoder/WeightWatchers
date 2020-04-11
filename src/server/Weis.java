package server;

public enum Weis {
Drei_Blatt,
Vier_Blatt,
Fuenf_Blatt,
Vier_Gleiche,
Vier_Nell,
Vier_Puure,
Stoecke;
	
	
public String toString() {
		String weis = "";
	    switch (this) {
	    case Drei_Blatt: weis = "Drei Blatt"; break;
	    case Vier_Blatt: weis = "Vier Blatt"; break;
	    case Fuenf_Blatt: weis = "Fuenf Blatt"; break;
	    case Vier_Gleiche: weis = "Vier Gleiche"; break;
	    case Vier_Nell: weis = "Vier Nell"; break;
	    case Vier_Puure: weis = "Vier Puure"; break;
	    case Stoecke: weis = "Stoecke"; break;
		}
	    return weis;
	}
}
