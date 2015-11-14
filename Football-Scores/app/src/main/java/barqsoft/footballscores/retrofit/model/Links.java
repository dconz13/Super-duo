package barqsoft.footballscores.retrofit.model;

/**
 * Generated using jsonschema2pojo
 */

import java.util.HashMap;
import java.util.Map;

public class Links {

    private Self self;
    private Soccerseason soccerseason;
    private HomeTeam homeTeam;
    private AwayTeam awayTeam;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The self
     */
    public Self getSelf() {
        return self;
    }

    /**
     *
     * @param self
     * The self
     */
    public void setSelf(Self self) {
        this.self = self;
    }

    /**
     *
     * @return
     * The soccerseason
     */
    public Soccerseason getSoccerseason() {
        return soccerseason;
    }

    /**
     *
     * @param soccerseason
     * The soccerseason
     */
    public void setSoccerseason(Soccerseason soccerseason) {
        this.soccerseason = soccerseason;
    }

    /**
     *
     * @return
     * The homeTeam
     */
    public HomeTeam getHomeTeam() {
        return homeTeam;
    }

    /**
     *
     * @param homeTeam
     * The homeTeam
     */
    public void setHomeTeam(HomeTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    /**
     *
     * @return
     * The awayTeam
     */
    public AwayTeam getAwayTeam() {
        return awayTeam;
    }

    /**
     *
     * @param awayTeam
     * The awayTeam
     */
    public void setAwayTeam(AwayTeam awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}