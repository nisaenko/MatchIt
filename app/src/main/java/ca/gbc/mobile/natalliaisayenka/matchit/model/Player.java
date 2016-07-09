/**************************************************
 * Natallia Isayenka
 * 100744884
 * created: 29-Sep-2014
 * lastEdit: 5-Oct-2014
 **************************************************/


package ca.gbc.mobile.natalliaisayenka.matchit.model;

public class Player {

    private String name;
    private String time;

    public long getTimeInMills() {
        return timeInMills;
    }

    public void setTimeInMills(long timeInMills) {
        this.timeInMills = timeInMills;
    }

    private long timeInMills;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
