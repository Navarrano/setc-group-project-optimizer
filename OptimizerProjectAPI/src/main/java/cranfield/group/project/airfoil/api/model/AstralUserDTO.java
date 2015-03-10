package cranfield.group.project.airfoil.api.model;

import java.sql.Timestamp;
import java.util.List;

public class AstralUserDTO {

	private Long id;
	private String login;
	private Timestamp last_connection_date;
	private int nb_connections;

	private List<WorkflowDTO> workflows;

	public AstralUserDTO(Long id, String login, Timestamp last_connection_date, int nb_connections){
		this.id = id;
		this.login = login;
		this.last_connection_date = last_connection_date;
		this.nb_connections = nb_connections;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Timestamp getLast_connection_date() {
		return last_connection_date;
	}

	public void setLast_connection_date(Timestamp last_connection_date) {
		this.last_connection_date = last_connection_date;
	}

	public int getNb_connections() {
		return nb_connections;
	}

	public void setNb_connections(int nb_connections) {
		this.nb_connections = nb_connections;
	}

	public List<WorkflowDTO> getWorkflows() {
		return workflows;
	}

	public void setWorkflows(List<WorkflowDTO> workflows) {
		this.workflows = workflows;
	}
}
