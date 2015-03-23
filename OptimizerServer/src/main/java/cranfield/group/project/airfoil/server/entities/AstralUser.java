package cranfield.group.project.airfoil.server.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author emi
 */
@Entity
@Table
@NamedQueries({
    @NamedQuery(name="existingUser", query = "FROM AstralUser u WHERE u.login=:login"),
    @NamedQuery(name="getWorkflows", query = "FROM Workflow w WHERE w.creator=:creator")
})
public class AstralUser extends AbstractEntityObject<Long, AstralUser> implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private Timestamp lastConnectionDate;
    private int nbConnections;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creator", fetch = FetchType.EAGER)
    private List<Workflow> workflows;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Logs> logs;
    
    public AstralUser(){}
    
    public AstralUser(String login) {
        this.login = login;
        this.lastConnectionDate = new Timestamp(new java.util.Date().getTime());
        this.nbConnections = 1;
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
        return lastConnectionDate;
    }

    public void setLast_connection_date(Timestamp last_connection_date) {
        this.lastConnectionDate = last_connection_date;
    }
    
    public int getNb_connections() {
        return nbConnections;
    }

    public void setNb_connections(int nb_connections) {
        this.nbConnections = nb_connections;
    }

    public List<Workflow> getWorkflows() {
	return workflows;
    }

    public void setWorkflows(List<Workflow> workflows) {
	this.workflows = workflows;
    }
    
    public void setNextWorkflow(Workflow workflow){
        this.workflows.add(workflow);
    }
  
}