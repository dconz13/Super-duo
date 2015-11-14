package barqsoft.footballscores.retrofit.model;

/**
 * Generated using jsonschema2pojo
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {

    private String timeFrameStart;
    private String timeFrameEnd;
    private int count;
    private List<Fixture> fixtures = new ArrayList<Fixture>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The timeFrameStart
     */
    public String getTimeFrameStart() {
        return timeFrameStart;
    }

    /**
     *
     * @param timeFrameStart
     * The timeFrameStart
     */
    public void setTimeFrameStart(String timeFrameStart) {
        this.timeFrameStart = timeFrameStart;
    }

    /**
     *
     * @return
     * The timeFrameEnd
     */
    public String getTimeFrameEnd() {
        return timeFrameEnd;
    }

    /**
     *
     * @param timeFrameEnd
     * The timeFrameEnd
     */
    public void setTimeFrameEnd(String timeFrameEnd) {
        this.timeFrameEnd = timeFrameEnd;
    }

    /**
     *
     * @return
     * The count
     */
    public int getCount() {
        return count;
    }

    /**
     *
     * @param count
     * The count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     *
     * @return
     * The fixtures
     */
    public List<Fixture> getFixtures() {
        return fixtures;
    }

    /**
     *
     * @param fixtures
     * The fixtures
     */
    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}