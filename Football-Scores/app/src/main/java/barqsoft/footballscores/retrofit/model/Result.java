package barqsoft.footballscores.retrofit.model;

/**
 * Generated using jsonschema2pojo
 */
import java.util.HashMap;
import java.util.Map;

public class Result {

    private int goalsHomeTeam;
    private int goalsAwayTeam;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The goalsHomeTeam
     */
    public int getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    /**
     *
     * @param goalsHomeTeam
     * The goalsHomeTeam
     */
    public void setGoalsHomeTeam(int goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
    }

    /**
     *
     * @return
     * The goalsAwayTeam
     */
    public int getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    /**
     *
     * @param goalsAwayTeam
     * The goalsAwayTeam
     */
    public void setGoalsAwayTeam(int goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}