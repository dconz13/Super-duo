package barqsoft.footballscores.retrofit.model;

/**
 * Generated using jsonschema2pojo
 */
import java.util.HashMap;
import java.util.Map;

public class Fixture {

    private Links _links;
    private String date;
    private String status;
    private int matchday;
    private String homeTeamName;
    private String awayTeamName;
    private Result result;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The Links
     */
    public Links get_links() {
        return _links;
    }

    /**
     *
     * @param _links
     * The _links
     */
    public void set_links(Links _links) {
        this._links = _links;
    }

    /**
     *
     * @return
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The matchday
     */
    public int getMatchday() {
        return matchday;
    }

    /**
     *
     * @param matchday
     * The matchday
     */
    public void setMatchday(int matchday) {
        this.matchday = matchday;
    }

    /**
     *
     * @return
     * The homeTeamName
     */
    public String getHomeTeamName() {
        return homeTeamName;
    }

    /**
     *
     * @param homeTeamName
     * The homeTeamName
     */
    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    /**
     *
     * @return
     * The awayTeamName
     */
    public String getAwayTeamName() {
        return awayTeamName;
    }

    /**
     *
     * @param awayTeamName
     * The awayTeamName
     */
    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    /**
     *
     * @return
     * The result
     */
    public Result getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(Result result) {
        this.result = result;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}