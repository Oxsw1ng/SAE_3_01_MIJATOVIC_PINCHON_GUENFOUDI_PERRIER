package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract;

public interface Sujet {
	public void enregistrerObservateur(Observateur o);
	public void supprimerObservateur(Observateur o);
	public void notifierObservateurs();
}
