package cranfield.group.project.airfoil.server.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Entity;
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
    @NamedQuery(name="existingUser", query = "FROM AstralUser u WHERE u.login=:login")
})
public class AstralUser extends AbstractEntityObject<Long, AstralUser> implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private Timestamp last_connection_date;
    private int nb_connections;

    @OneToMany
    private List<Workflow> workflows;
    
    public AstralUser(){}
    
    public AstralUser(String login) {
        this.login = login;
        this.last_connection_date = new Timestamp(new java.util.Date().getTime());
        this.nb_connections = 1;
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
}